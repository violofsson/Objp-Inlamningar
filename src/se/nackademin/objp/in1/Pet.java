package se.nackademin.objp.in1;

public abstract class Pet implements IFeedable {
    private String name;
    private double weight;

    public Pet(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }
}
