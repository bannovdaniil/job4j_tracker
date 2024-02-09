package ru.job4j.collection;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;

public class PassportOfficeTest {

    @Test
    public void whenOneAdd() {
        Citizen citizen = new Citizen("2f44a", "Petr Arsentev");
        PassportOffice office = new PassportOffice();
        office.add(citizen);
        assertThat(office.get(citizen.getPassport()), is(citizen));
    }

    @Test
    public void whenTwoAdd() {
        Citizen citizen1 = new Citizen("2f44a", "Petr Arsentev");
        Citizen citizen2 = new Citizen("3f44a", "Petr Arsentev");
        PassportOffice office = new PassportOffice();
        office.add(citizen1);
        office.add(citizen2);
        assertThat(office.get(citizen1.getPassport()), is(citizen1));
        assertThat(office.get(citizen2.getPassport()), is(citizen2));
    }

    @Test
    public void whenEqualsTwoAddThenFalse() {
        Citizen citizen1 = new Citizen("2f44a", "Petr Arsentev");
        Citizen citizen2 = new Citizen("2f44a", "Petr Arsentev");
        PassportOffice office = new PassportOffice();
        office.add(citizen1);
        assertThat(office.get(citizen1.getPassport()), is(citizen1));
        assertFalse(office.add(citizen2));
    }
}