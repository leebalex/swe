package subsys.control.Usecase1_CheckSecurityInformation;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

public class Main {
    public static void main(String[] args) throws IOException {

        subsys.control.Usecase1_CheckSecurityInformation.SecurityWorker worker1 = new subsys.control.Usecase1_CheckSecurityInformation.SecurityWorker("Hans", "Maier", "Officer", "All", "all");
        SecurityInformation secInfo= new SecurityInformation(subsys.control.Usecase1_CheckSecurityInformation.SubsystemCategory.CONTROLSYSTEM, worker1);
    }
}
