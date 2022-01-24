package subsys.control.Usecase1_CheckSecurityInformation;

import java.io.IOException;
import java.nio.file.*;
import java.sql.Timestamp;
import java.util.Hashtable;
import java.util.Random;


public class SecurityInformation {
    public Integer getInformationId() {
        return informationId;
    }

    private static Integer informationId = 0;
    private String titleSecurity;

    public String getTitleSecurity() {
        return titleSecurity;
    }

    public subsys.control.Usecase1_CheckSecurityInformation.SubsystemCategory getSubsystemCategory() {
        return subsystemCategory;
    }

    private String descriptionSecurity;
    private subsys.control.Usecase1_CheckSecurityInformation.SubsystemCategory subsystemCategory;
    private RiskLevel riskLevel;   //4 different risklevels

    private Timestamp timestamp;    // needed for the security protocol
    private static Hashtable<Integer, String> securityProtocol;
    private static boolean alarmOn;
    private final String securityProtocolFile = "securityProtocolFile.txt";


    public SecurityInformation(subsys.control.Usecase1_CheckSecurityInformation.SubsystemCategory subsystemCategory, Worker worker) throws IOException {
        this.subsystemCategory = subsystemCategory;
        this.informationId++;
        getSecurityInformation(subsystemCategory, worker, this);
        logToSecurityProtocol(informationId);
    }


    public SecurityInformation getSecurityInformation(subsys.control.Usecase1_CheckSecurityInformation.SubsystemCategory subsystemCategory, Worker worker, SecurityInformation securityInformation) throws AccessDeniedException {
        if (!(worker instanceof subsys.control.Usecase1_CheckSecurityInformation.SecurityWorker || worker instanceof ControlWorker) || subsystemCategory == subsys.control.Usecase1_CheckSecurityInformation.SubsystemCategory.NONE) {
            throw new AccessDeniedException("You have not the rights to access the security information!");
        }

        securityInformation.subsystemCategory = subsystemCategory;

        securityInformation.titleSecurity = "Security information with the ID " + informationId
                + "\nfor " + worker.name + " with the workerID: " + worker.workerId
                + "\nregarding the subsystem category " + subsystemCategory;
        securityInformation.descriptionSecurity = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
        securityInformation.riskLevel = createRiskLevel();
        if (securityInformation.riskLevel == RiskLevel.CRITICAL) {
            alarmOn = true;
        } else {
            alarmOn = false;
        }

        securityInformation.timestamp = createTimestamp();
        String securityProtocolString = titleSecurity + "\nRiskLevel: " + riskLevel + "\nTimestamp" + securityInformation.timestamp;
        securityProtocol = new Hashtable<>();
        securityInformation.securityProtocol.put(informationId, securityProtocolString);
        return securityInformation;

    }

    public void printSecurityInformation() {
        if (informationId != null) {
            System.out.println("TITLE: " + titleSecurity);
            System.out.println("DESCRIPTION: " + descriptionSecurity);
            System.out.println("RISKLEVEL: " + riskLevel);
            System.out.println("SECURITY PROTOCOL:" + securityProtocol);
        } else {
            throw new IllegalArgumentException("The security information is empty");
        }
    }

    public Timestamp createTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    public RiskLevel getRiskLevel() {
        return riskLevel;
    }

    public RiskLevel createRiskLevel() {
        Random random = new Random();
        RiskLevel level;
        int num = random.nextInt(4);
        if (num == 0) {
            level = RiskLevel.LOW;
        } else if (num == 1) {
            level = RiskLevel.NORMAL;
        } else if (num == 2) {
            level = RiskLevel.HIGH;
        } else {
            level = RiskLevel.CRITICAL;
        }
        return level;
    }


    // after the check, the security check is logged in a security protocol
    public void logToSecurityProtocol(Integer key) throws IOException {

        StringBuilder sb = new StringBuilder();
        //if is null
        sb.append(securityProtocol.get(key));
        sb.append(timestamp.toString());
        writeToFile(sb.toString());
    }

    public void setRiskLevel(RiskLevel riskLevel) {
        if (riskLevel == RiskLevel.CRITICAL) {
            setAlarmOn();
        }
        this.riskLevel = riskLevel;
    }

    public void setAlarmOn() {
        alarmOn = true;
        System.err.println("ATTENTION! \nThe current risklevel is CRITICAL \nALARM IS SET TO ON");
    }

    public void setAlarmOff() {
        alarmOn = false;
    }

    public void writeToFile(String str) throws IOException {
        try {
            Path path = Paths.get(securityProtocolFile);
            byte[] strToBytes = str.getBytes();
            Files.write(path, strToBytes, StandardOpenOption.APPEND);

        } catch (IOException e) {
            System.err.print("Error occured trying to write to the file. Please try again");
        }
    }

    public static boolean isAlarmOn() {
        return alarmOn;
    }


    public String getDescriptionSecurity() {
        return descriptionSecurity;
    }


    public Timestamp getTimestamp() {
        return timestamp;
    }

    public static Hashtable<Integer, String> getSecurityProtocol() {
        return securityProtocol;
    }

    public String getSecurityProtocolFile() {
        return securityProtocolFile;
    }
}