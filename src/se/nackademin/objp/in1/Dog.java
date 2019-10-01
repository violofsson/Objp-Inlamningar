package se.nackademin.objp.in1;

public class Dog extends Pet {
    public Dog(String name, double weightInKilos) {
        super(name, weightInKilos);
    }

    @Override
    public String getFoodType() {
        return "hundfoder";
    }

    @Override
    public int getPortionInGrammes() {
        // (vikt i g) / 100 = 1000 * (vikt i kg) / 100
        return (int) (10 * getWeightInKilos());
    }
}
