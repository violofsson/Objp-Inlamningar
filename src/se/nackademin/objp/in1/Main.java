package se.nackademin.objp.in1;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Animal[] guests = {
                new Dog("Sigge", 5000),
                new Dog("Dogge", 10_000),
                new Cat("Venus", 5000),
                new Cat("Ove", 3000),
                new Snake("Hypno", 1000)
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
                // TODO Ändra till mer relevanta meddelanden efter test
                JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
            }
        }
    }
}
