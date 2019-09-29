package se.nackademin.objp.in1;

import javax.swing.*;

public class Main {

    public static Pet getPetByName(Pet[] searchArray, String name) {
        Pet found = null;
        for (Pet p : searchArray) {
            if (name.equals(p.getName())) {
                found = p;
                break;
            }
        }
        // Exception om djuret inte hittas?
        return found;
    }

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
                // TODO Städa upp if-satserna
                if (lookup == null) {
                    break;
                }
                // Exception?
                Pet toFeed = getPetByName(guests, lookup);
                if (toFeed == null) {
                    // Djuret finns inte
                } else {
                    double portion = toFeed.getOnePortion();
                    String foodType = toFeed.getFoodType();
                    // Skriv ut rekommendation
                }
            } catch (Exception e) {
                String errMsg = "Ett oväntat fel inträffade under körning:\n" +
                        e.getLocalizedMessage();
                JOptionPane.showMessageDialog(null, errMsg);
            }
        }
    }
}
