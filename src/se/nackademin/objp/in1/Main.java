package se.nackademin.objp.in1;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Pet[] guests = {
                new Dog("Sigge", 5.0),
                new Dog("Dogge", 10.0),
                new Cat("Venus", 5.0),
                new Cat("Ove", 3.0),
                new Snake("Hypno", 1.0)
        };

        while (true) {
            // Visa inmatning
            try {
                String lookup = JOptionPane.showInputDialog("Vilket djur ska få mat?");
                // Om djur finns:
                //   beräkna och visa
                // Om djur inte finns:
                //   klaga
                // Om felaktig inmatning:
                //   klaga mer
                // Om avslutning (skilj från fel):
                //   avsluta
                if (lookup == null)
                    break;
            } catch (Exception e) {
                String errMsg = "Ett oväntat fel inträffade under körning.\n" +
                        "Rapportera följande meddelande till IT-supporten:\n" +
                        e.getLocalizedMessage();
                JOptionPane.showMessageDialog(null, errMsg);
            }
        }
    }
}
