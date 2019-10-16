package se.nackademin.objp.in2;

public class Person {
    private String name;
    private String personalID;

    public Person(String name, String personalID) {
        this.name = name;
        // Kontrollera personnummer?
        this.personalID = personalID;
    }

    @Override
    public String toString() {
        return getPersonalID() + " " + getName();
    }

    public String getName() {
        return name;
    }

    public String getPersonalID() {
        return personalID;
    }

    public boolean samePersonAs(Person p) {
        return (this.getName().equals(p.getName()) &&
                this.getPersonalID().equals(p.getPersonalID()));
    }
}
