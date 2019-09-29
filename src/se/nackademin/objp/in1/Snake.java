package se.nackademin.objp.in1;

public class Snake extends Pet {
    public Snake(String name, double weight) {
        super(name, weight);
    }

    @Override
    public String getFoodType() {
        return "ormpellets";
    }

    @Override
    public int getOnePortion() {
        return 20;
    }
}
