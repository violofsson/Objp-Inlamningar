package se.nackademin.objp.in1;

public class Cat extends Pet {
    public Cat(String name, double weightInKilos) {
        super(name, weightInKilos);
    }

    @Override
    public String getFoodType() {
        return "kattfoder";
    }

    @Override
    public int getPortionInGrammes() {
        // (vikt i g) / 150 = 1000 * (vikt i kg) / 150
        return (int) (100 * getWeightInKilos() / 15);
    }
}
