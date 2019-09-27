package se.nackademin.objp.in1;

public class Cat extends Animal {
    public Cat(String name, int weightInGrams) {
        super(name, weightInGrams);
    }

    @Override
    public int getOnePortion() {
        return getWeightInGrams()/150;
    }
}
