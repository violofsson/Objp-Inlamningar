package se.nackademin.objp.in1;

public class Dog extends Animal {
    public Dog(String name, int weightInGrams) {
        super(name, weightInGrams);
    }

    @Override
    public int getOnePortion() {
        return getWeightInGrams()/100;
    }
}
