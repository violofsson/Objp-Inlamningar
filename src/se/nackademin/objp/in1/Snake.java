package se.nackademin.objp.in1;

public class Snake extends Animal {
    public Snake(String name, int weightInGrams) {
        super(name, weightInGrams);
    }

    @Override
    public int getOnePortion() {
        return 20;
    }
}
