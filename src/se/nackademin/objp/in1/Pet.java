package se.nackademin.objp.in1;

public abstract class Pet implements IFeedable {
    private String name;
    private double weight;

    public Pet(String name, double weight) {
        if (name != null && !name.isBlank()) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Djur måste ha ett namn.");
        }
        if (weight >= 0) {
            this.weight = weight;
        } else {
            throw new IllegalArgumentException("Djur får inte ha negativ vikt.");
        }
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }
}
