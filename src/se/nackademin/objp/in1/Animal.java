package se.nackademin.objp.in1;

public abstract class Animal {
    private String name;
    private int weight;

    public Animal(String name, int weightInGrams) {
        this.name = name;
        this.weight = weightInGrams;
    }

    public String getName() {
        return name;
    }

    // Förtydligar att vi mäter i gram, inte kg
    public int getWeightInGrams() {
        return weight;
    }

    public abstract int getOnePortion();
}
