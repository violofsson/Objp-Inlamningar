package se.nackademin.objp.in1;

import javax.swing.*;

public class Main {
    /*
     HotelGuest används enbart för att testa main-metoden; en
     riktig implementering skulle a) definieras någon annanstans
     och b) implementeras med en mer flexibel datastruktur.
     Därför finns ingen anledning att låta HotelGuest nås utifrån.
    */
    private enum HotelGuest {
        SIGGE(new Dog("Sigge", 5.0)),
        DOGGE(new Dog("Dogge", 10.0)),
        VENUS(new Cat("Venus", 5.0)),
        OVE(new Cat("Ove", 3.0)),
        HYPNO(new Snake("Hypno", 1.0));

        private final Pet storedPet;

        HotelGuest(Pet p) {
            this.storedPet = p;
        }

        // Returnerar null om det sökta djuret saknas
        public static Pet getHotelGuestByName(String name) {
            for (HotelGuest hg : values()) {
                String guestName = hg.storedPet.getName();
                if (name.equalsIgnoreCase(guestName)) {
                    return hg.storedPet;
                }
            }
            return null;
        }
    }

    // Övriga meddelanden är enkla nog att skrivas direkt i
    // main-metoden, men portionsberäkningen är lite mer invecklad
    private static String getPortionRecommendation(Pet p) {
        if (p == null) {
            throw new NullPointerException();
        }
        int portion = p.getPortionInGrammes();
        String foodType = p.getFoodType();
        return p.getName() + " behöver " + portion + " gram " +
                foodType + " per portion.";
    }

    // Ett par metoder som för att göra main-metoden lite mer lättläst
    // (och valet av gränssnitt lite mer flexibelt)
    private static String getUserInput(String s) {
        return JOptionPane.showInputDialog(s);
    }

    private static void showMessage(String s) {
        JOptionPane.showMessageDialog(null, s);
    }

    public static void main(String[] args) {
        while (true) {
            try {
                String lookup = getUserInput(
                        "Vilket djur ska få mat?\n" +
                                "(Klicka på Avbryt eller lämna en " +
                                "blank rad för att avsluta.)");
                if (lookup == null || lookup.isBlank()) {
                    break;
                }
                // Eliminera en möjlig felkälla (oönskade mellanslag)
                // utan någon realistisk nackdel
                lookup = lookup.strip();

                Pet toBeFed = HotelGuest.getHotelGuestByName(lookup);
                if (toBeFed == null) {
                    showMessage("Hittade inget djur med namnet '" +
                            lookup + "'.");
                } else {
                    showMessage(getPortionRecommendation(toBeFed));
                }
            } catch (Exception e) {
                showMessage("Oväntat fel under körning:\n" +
                        e.getMessage());
                // I princip skulle vi kunna låta programmet köra
                // vidare, men om felet är allvarligt nog är det
                // bäst att ta det säkra före det osäkra
                break;
            }
        }
    }
}
