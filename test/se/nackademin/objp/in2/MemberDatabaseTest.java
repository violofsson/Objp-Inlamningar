package se.nackademin.objp.in2;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MemberDatabaseTest {
    private MemberDatabase db = MemberDatabase.getInstance();
    private Person alice = new Person("Alice Albert", "960413");
    private Person bob = new Person("Bob Bobson", "841230");
    private String inFile = "test/se/nackademin/objp/in2/customers.txt";
    private String outFile = "test/se/nackademin/objp/in2/pass.txt";
    LocalDate today = LocalDate.now();

    @Test
    void testReading() {
        db.dataList.clear();
        assertDoesNotThrow(() -> db.readMembersFromFile(inFile));
        assertThrows(FileNotFoundException.class,
                () -> db.readMembersFromFile("adjasjcosj"));
        assertTrue(db.dataList.size() > 0);
        Member alhambra = db.dataList.get(0);
        assertEquals("7603021234", alhambra.getPersonalID());
        assertEquals("Alhambra Aromes", alhambra.getName());
        assertEquals("2018-07-01",
                alhambra.getLastPaymentDate().toString());
    }

    @Test
    void testAddingAndSearching() throws NoSuchMemberException {
        db.dataList.clear();
        assertDoesNotThrow(() -> {
            db.addMember(alice);
            db.addMember(bob);
        });
        assertThrows(DuplicateMemberException.class,
                () -> db.addMember(alice));
        assertTrue(db.hasMember(alice));
        assertFalse(db.hasMember(new Person("a", "b")));
        assertTrue(db.getMember(bob).samePersonAs(bob));
        assertFalse(db.getMember(alice).samePersonAs(bob));
        assertThrows(NoSuchMemberException.class, () ->
                db.getMember(new Person("", "")));
    }

    @Test
    void testWriting() throws DuplicateMemberException,
            NoSuchMemberException, SessionWriteException, IOException {
        db.dataList.clear();
        Files.deleteIfExists(Paths.get(outFile));
        db.addMember(alice);
        db.addMember(bob);
        db.getMember(bob).registerSession();
        db.writeSessionsToFile(outFile);
        String output = Files.readString(Paths.get(outFile));
        assertEquals("841230 Bob Bobson tränade " + today + "\n",
                output);
    }
}