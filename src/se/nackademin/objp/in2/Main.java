package se.nackademin.objp.in2;

import javax.swing.*;

public class Main {
    private static MemberDatabase db = MemberDatabase.getInstance();
    private static String inFile = "src/se/nackademin/objp/in2/customers.txt";
    private static String outFile = "src/se/nackademin/objp/in2/sessions.txt";

    private static String getInput(String prompt) {
        return JOptionPane.showInputDialog(null, prompt);
    }

    private static void showMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    private static int getConfirmation(String msg) {
        return JOptionPane.showConfirmDialog(null, msg,
                "", JOptionPane.YES_NO_OPTION);
    }

    private static Person getPersonalDetails() {
        String name = getInput("Ange namn.");
        if (name == null) System.exit(0);
        String personalID = getInput("Ange personnummer.");
        if (personalID == null) System.exit(0);
        return new Person(name, personalID);
    }

    public static void main(String[] args) {
        try {
            db.readMembersFromFile(inFile);
            int checkMore;
            do {
                Person customer = getPersonalDetails();
                if (db.hasCurrentMember(customer)) {
                    showMessage(customer.getName() +
                            " är medlem. Välkommen!");
                    db.getMember(customer).registerSession();
                } else if (db.hasMember(customer)) {
                    showMessage(customer.getName() +
                            " är före detta medlem.\n" +
                            "Hen har inte betalat sin årsavgift!");
                } else {
                    showMessage(customer.getName() +
                            " är obehörig.");
                }
                checkMore = getConfirmation(
                        "Vill du kontrollera fler kunder?");
            } while (checkMore == JOptionPane.YES_OPTION);
            int checkPrinting = getConfirmation(
                    "Vill du spara alla inskrivna träningspass?");
            if (checkPrinting == JOptionPane.YES_OPTION) {
                db.writeSessionsToFile(outFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
