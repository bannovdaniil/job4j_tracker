package ru.job4j.pojo;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LicenseTest {

    @Test
    public void eqName() {
        License first = new License();
        first.setCode("audio");
        License second = new License();
        second.setCode("audio");
        assertThat(first, is(second));
    }
}