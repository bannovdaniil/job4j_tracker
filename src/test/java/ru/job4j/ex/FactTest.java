package ru.job4j.ex;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class FactTest {

    @Test(expected = IllegalArgumentException.class)
    public void whenCalc0ThenExeption() {
        new Fact().calc(-1);
    }

    @Test
    public void whenCalc5Then120() {
        int in = 5;
        int expected = 120;
        Fact fact = new Fact();
        int result = fact.calc(in);
        assertThat(expected, is(result));
    }
}