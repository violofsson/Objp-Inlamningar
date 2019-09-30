package se.nackademin.objp.in1;

import javax.swing.*;

public class Main {
    // Till skillnad från de andra klasserna ska HotelGuest aldrig
    // användas utanför Main, ens i teorin - den är ett testverktyg,
    // inte en databas
    enum HotelGuest {
        SIGGE(new Dog("Sigge", 5.0)),
        DOGGE(new Dog("Dogge", 10.0)),
        VENUS(new Cat("Venus", 5.0)),
        OVE(new Cat("Ove", 3.0)),
        HYPNO(new Snake("Hypno", 1.0));

        private Pet storedPet;

        HotelGuest(Pet p) {
            storedPet = p;
        }

        public static Pet getHotelGuestByName(String name) {
            for (HotelGuest hg : HotelGuest.values()) {
                String guestName = hg.storedPet.getName();
                if (name.equalsIgnoreCase(guestName)) {
                    return hg.storedPet;
                }
            }
            return null;
        }
    }

    private static String getPortionRecommendation(Pet p) {
        if (p == null) throw new NullPointerException();
        int portion = p.getPortionInGrammes();
        String foodType = p.getFoodType();
        return p.getName() + " behöver " + portion + " gram " +
                foodType + " per portion.";
    }

    public static void main(String[] args) {
        while (true) {
            // Visa inmatning
            try {
                String lookup = JOptionPane.showInputDialog(
                        "Vilket djur ska få mat?\n" +
                                "(Klicka på Avbryt eller lämna en " +
                                "tom rad för att avsluta.)");

                // TODO Städa upp metoden
                if (lookup == null || lookup.isBlank()) {
                    break;
                }
                lookup = lookup.strip();

                Pet toBeFed = HotelGuest.getHotelGuestByName(lookup);
                if (toBeFed == null) {
                    // Djuret finns inte
                    JOptionPane.showMessageDialog(null,
                            "Hittade inget djur med namnet '" +
                                    lookup + "'.");
                } else {
                    JOptionPane.showMessageDialog(null,
                            getPortionRecommendation(toBeFed));
                }
            } catch (Exception e) {
                String errMsg =
                        "Ett oväntat fel inträffade under körning:\n" +
                                e.getMessage();
                JOptionPane.showMessageDialog(null, errMsg);
            }
        }
    }
}
