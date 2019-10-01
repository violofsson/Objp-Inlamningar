package se.nackademin.objp.in1;

/*
 Eftersom diet varierar så mycket mellan djur låter vi bli att
 implementera IFeedable direkt i Pet - det går bra att lämna den
 uppgiften till de enskilda djurklasserna.
 Java förstår ändå att alla djur har de relevanta metoderna och
 sköter resten med dynamisk bindning.
*/
public abstract class Pet implements IFeedable {
    /*
     Vi inkapslar namn och vikt så att de inte kan ändras hur som
     helst. I verkligheten händer det att de ändras (inom vissa
     ramar), så konstanter är olämpliga - men huvudprogrammet
     hanterar inte dessa fall, så setters är överflödiga.
     Om Pet skulle återanvändas i ett annat program borde setters
     definieras på något passande sätt.
    */
    private String name;
    private double weightInKilos;

    public Pet(String name, double weightInKilos) {
        if (name != null && !name.isBlank()) {
            this.name = name;
        } else {
            throw new IllegalArgumentException(
                    "Djur måste ha ett namn.");
        }
        if (weightInKilos >= 0) {
            this.weightInKilos = weightInKilos;
        } else {
            throw new IllegalArgumentException(
                    "Djur får inte ha negativ vikt.");
        }
    }

    public String getName() {
        return name;
    }

    public double getWeightInKilos() {
        return weightInKilos;
    }
}
