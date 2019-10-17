package se.nackademin.objp.in2;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MemberDatabase {
    protected List<Member> dataList;
    static private MemberDatabase instance = new MemberDatabase();

    private MemberDatabase() {
        this.dataList = new ArrayList<>();
    }

    public static MemberDatabase getInstance() {
        return instance;
    }

    public void readMembersFromFile(String filename) throws
            FileNotFoundException, DuplicateMemberException,
            MalformedMemberDatabaseException {
        try (Scanner sc = new Scanner(new FileReader(filename))) {
            // Finns icke-tom rad?
            while (sc.hasNextLine() && sc.hasNext()) {
                String personalId = sc.next().replace(",", "").trim();
                String name = sc.nextLine().trim();
                LocalDate lastPayment = LocalDate.parse(sc.nextLine());
                addMember(new Person(name, personalId), lastPayment);
            }
        } catch (NoSuchElementException nse) {
            throw new MalformedMemberDatabaseException(
                    "Fel vid inläsning av " + filename + ".");
        }
    }

    public void writeSessionsToFile(String filename)
            throws SessionWriteException {
        try (PrintWriter out = new PrintWriter(
                new FileWriter(filename, true))) { // append = true
            for (Member m : dataList) {
                if (m.getLastSession() != null) {
                    out.printf("%s %s tränade %s\n",
                            m.getPersonalID(),
                            m.getName(),
                            m.getLastSession());
                }
            }
        } catch (IOException ioe) {
            throw new SessionWriteException(
                    "Fel vid utskrift till " + filename + ".");
        }
    }

    public boolean hasMember(Person p) {
        for (Member m : dataList) {
            if (p.samePersonAs(m)) return true;
        }
        return false;
    }

    public boolean hasCurrentMember(Person p) {
        try {
            if (!hasMember(p)) return false;
            else {
                LocalDate lastPayment =
                        getMember(p).getLastPaymentDate();
                return (!LocalDate.now().isAfter(
                        lastPayment.plusYears(1)));
            }
        } catch (NoSuchMemberException nme) {
            return false;
        }
    }

    public Member getMember(Person p) throws NoSuchMemberException {
        for (Member m : dataList) {
            if (p.samePersonAs(m)) return m;
        }
        throw new NoSuchMemberException(
                "Hittar inte den angivna medlemmen " + p + ".");
    }

    public void addMember(Person p, LocalDate lastPayment) throws DuplicateMemberException {
        if (hasMember(p)) {
            throw new DuplicateMemberException(
                    "En medlem identifierad som '" + p +
                            " finns redan."
            );
        } else {
            dataList.add(new Member(p.getName(),
                    p.getPersonalID(), lastPayment));
        }
    }

    public void addMember(Person p) throws DuplicateMemberException {
        addMember(p, LocalDate.now());
    }
}
