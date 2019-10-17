package se.nackademin.objp.in2;

import java.time.LocalDate;

public class Member extends Person {
    private LocalDate lastPayment;
    private LocalDate lastSession;

    public Member(String name, String personalID, LocalDate lastPayment) {
        super(name, personalID);
        this.lastPayment = lastPayment;
    }

    public Member(String name, String personalID) {
        this(name, personalID, LocalDate.now());
    }

    public Member(Person p) {
        this(p.getName(), p.getPersonalID());
    }

    public LocalDate getLastPaymentDate() {
        return this.lastPayment;
    }

    public void registerPayment(LocalDate d) {
        this.lastPayment = d;
    }

    public void registerPayment() {
        registerPayment(LocalDate.now());
    }

    public LocalDate getLastSession() {
        return this.lastSession;
    }

    public void registerSession(LocalDate d) {
        this.lastSession = d;
    }

    public void registerSession() {
        registerSession(LocalDate.now());
    }
}
