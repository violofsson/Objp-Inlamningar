package se.nackademin.objp.in2;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    @Test
    void testIdentification() {
        Person alice = new Person("Alice Albert", "960413");
        Person fakeAlice = new Person("Alice Albert", "802208");
        Person bob = new Person("Bob Bobson", "841230");
        Member alsoBob = new Member("Bob Bobson", "841230");
        Member bobAgain = new Member(bob); // Onödig?

        assertTrue(alice.samePersonAs(alice));
        assertFalse(alice.samePersonAs(bob));
        assertFalse(alice.samePersonAs(fakeAlice));
        assertTrue(bob.samePersonAs(alsoBob));
        assertTrue(alsoBob.samePersonAs(bob));
        assertTrue(bob.samePersonAs(bobAgain));
        assertTrue(bobAgain.samePersonAs(bob));
        assertTrue(alsoBob.samePersonAs(bobAgain));
    }

    @Test
    void testMembershipData() {
        Member alice = new Member("Alice Albert", "960413",
                LocalDate.of(2010, 8, 30));
        Member bob = new Member("Bob Bobson", "841230");

        assertEquals(alice.getName(), "Alice Albert");
        assertEquals(alice.getPersonalID(), "960413");
        assertNotEquals(alice.getName(), bob.getName());
        assertEquals(bob.getLastPaymentDate(), LocalDate.now());
        assertNotEquals(alice.getLastPaymentDate(),
                bob.getLastPaymentDate());
        alice.registerPayment();
        assertEquals(alice.getLastPaymentDate(),
                bob.getLastPaymentDate());
    }
}
