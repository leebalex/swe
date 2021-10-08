package server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import static utils.Constants.*;

public class AsyncServer {

    private static final int TIMEOUT = 5000;
    private static final Charset CSET = StandardCharsets.UTF_8;

    private volatile boolean terminate = false;
    private final ServerSocketChannel server;

    private final Selector selector;

    public AsyncServer() throws IOException {
        server = ServerSocketChannel.open();
        server.socket().bind(new InetSocketAddress(PORT));
        selector = Selector.open();
    }

    public void start() throws IOException {
        System.out.println("AsyncServer started on port " + PORT);

        // selector
        Thread selectorThread = new Thread(new SelectorRunnable());
        selectorThread.start();

        // accept client loop
        while (!terminate) {
            SocketChannel clientChannel;
            try {
                System.out.println("Server waiting to accept client");
                // -- blocking --------------------------------
                clientChannel = server.accept();
                // --------------------------------------------
                System.out.println("Server accepted client");
                clientChannel.configureBlocking(false);
                ClientHandler handler = new ClientHandler(clientChannel);
                SelectionKey key = clientChannel.register(selector, SelectionKey.OP_READ);
                key.attach(handler);
                selector.wakeup();
                handler.sayHello();
            } catch (AsynchronousCloseException ace) {
                terminate = true;
            } catch (IOException e) {
                System.out.println("ERROR in accepting client " + e.toString());
            }
        }
    }

    public void terminate() throws IOException {
        terminate = true;
        server.close();
        selector.close();
    }

    private class SelectorRunnable implements Runnable {

        @Override
        public void run() {
            while(! terminate) {
                try {
                    // -----------------------------
                    int n = selector.select(TIMEOUT);
                    // -----------------------------
                    Set<SelectionKey> selectedKeys = selector.selectedKeys();
                    Iterator<SelectionKey> keysIt = selectedKeys.iterator();
                    while (keysIt.hasNext()) {
                        SelectionKey key = keysIt.next();
                        if (key.isReadable()) {
                            ClientHandler handler = (ClientHandler)key.attachment();
                            handler.handleMessage(key);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Exception in select " + e.toString());
                }
            }
        }
    }

    // -- client handler ------------

    enum State {
        START, LOGGED_IN, START_SYNC, NO_COMMANDS, END_SYNC
    }

    private class ClientHandler {

        private final SocketChannel channel;
        private final ByteBuffer buffer;
        private State state;

        ClientHandler(SocketChannel channel) {
            super();
            this.channel = channel;
            this.buffer = ByteBuffer.allocate(1024);
            this.state = State.START;
        }

        private void sayHello() throws IOException {
            writeMessage(HELO_FROM + server.toString());
        }

        private void handleMessage(SelectionKey key) throws IOException {
            ArrayList<String> fileList = new ArrayList<>();
            BufferedWriter writer;
            String msg; // = readMessage();
            switch(state) {
                case START:
                    msg = readMessage();
                    if (!msg.startsWith(LOGIN)) {
                        System.out.println(LOGIN + "expected but received " + msg);
                        return;
                    }
                    String clientName = msg.substring(6, msg.indexOf(LINE_SEP));
                    writeMessage(OK_LOGIN + clientName);
                    state = State.LOGGED_IN;
                    break;
                case LOGGED_IN:
                    msg = readMessage();
                    if (!msg.startsWith(START_SYNC)) {
                        System.out.println(START_SYNC + " expected but received " + msg);
                        return;
                    }
                    writeMessage(OK_START_SYNC);
                    state = State.START_SYNC;
                    break;
                case START_SYNC:
                    msg = readMessage();
                    if (!msg.startsWith(NO_COMMANDS)) {
                        while (!msg.startsWith(DONE_COMMANDS)) {
                           msg = readMessage();
                           fileList.add(msg);
                        }
                    }
                    fileList.forEach(x -> System.out.println(x));
                    fileList.clear();

                    //TODO: handle file write dialogue

                    writeMessage(OK_DONE_COMMANDS);
                    state = State.NO_COMMANDS;
                    break;

                case NO_COMMANDS:
                    msg = readMessage();
                    if (!msg.startsWith(END_SYNC)) {
                        System.out.println(END_SYNC + " expected but received " + msg);
                        return;
                    }
                    writeMessage(OK_END_SYNC);
                    state = State.END_SYNC;
                    break;

                case END_SYNC:
                    msg = readMessage();
                    if (!msg.startsWith(DONE)) {
                        System.out.println(DONE + " expected but received " + msg);
                        return;
                    }
                    writeMessage(BYE);
                    key.cancel();
                    channel.close();
                    break;
            }
        }

        private String readMessage() throws IOException {
            buffer.clear();
            @SuppressWarnings("unused")
            int r = channel.read(buffer);
            buffer.flip();
            String data = CSET.decode(buffer).toString();
            System.out.print(" <-- " + data);
            return data;
        }

        private void writeMessage(String msg) throws IOException {
            ByteBuffer bbuf = CSET.encode(msg + LINE_SEP);
            buffer.clear();
            buffer.put(bbuf);
            buffer.flip();
            channel.write(buffer);
            System.out.println(" --> " + msg);
        }

    }
}
