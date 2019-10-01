package se.nackademin.objp.in1;

public class Snake extends Pet {
    public Snake(String name, double weightInKilos) {
        super(name, weightInKilos);
    }

    @Override
    public String getFoodType() {
        return "ormpellets";
    }

    @Override
    public int getPortionInGrammes() {
        return 20;
    }
}
