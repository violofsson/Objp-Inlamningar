package se.nackademin.objp.in1;

import javax.swing.*;

public class Main {

    enum HotelGuests {
        SIGGE(new Dog("Sigge", 5.0)),
        DOGGE(new Dog("Dogge", 10.0)),
        VENUS(new Cat("Venus", 5.0)),
        OVE(new Cat("Ove", 3.0)),
        HYPNO(new Snake("Hypno", 1.0));

        private Pet namedPet;

        private HotelGuests(Pet p) {
            namedPet = p;
        }

        public Pet getPet() {
            return namedPet;
        }

        public static Pet getPetByName(String name) {
            for (HotelGuests p : HotelGuests.values()) {
                if (name.equalsIgnoreCase(p.getPet().getName())) {
                    return p.getPet();
                }
            }
            return null;
        }
    }

    public static void main(String[] args) {
        while (true) {
            // Visa inmatning
            try {
                String lookup = JOptionPane.showInputDialog(
                        "Vilket djur ska få mat?");
                // TODO Städa upp metoden
                if (lookup == null) {
                    break;
                }
                Pet toFeed = HotelGuests.getPetByName(lookup);
                if (toFeed == null) {
                    // Djuret finns inte
                    JOptionPane.showMessageDialog(null,
                            "Hittade inget djur med namnet '" +
                                    lookup + "'.");
                } else {
                    double portion = toFeed.getOnePortion();
                    String foodType = toFeed.getFoodType();
                    // Skriv ut rekommendation
                    JOptionPane.showMessageDialog(null,
                            toFeed.getName() + " behöver " + portion
                            + " gram " + foodType + " per portion.");
                }
            } catch (Exception e) {
                String errMsg =
                        "Ett oväntat fel inträffade under körning:\n" +
                                e.getLocalizedMessage();
                JOptionPane.showMessageDialog(null, errMsg);
            }
        }
    }
}
