package ru.job4j.oop;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;

public class PointTest {
    @Test
    public void when101to023then3() {
        double expected = 3;
        Point a = new Point(1, 0, 1);
        Point b = new Point(0, 2, 3);
        double dist = a.distance3d(b);
        assertThat(expected, closeTo(dist, 0.01));
    }

    @Test
    public void when00to20then2() {
        double expected = 2;
        Point a = new Point(0, 0);
        Point b = new Point(0, 2);
        double dist = a.distance(b);
        assertThat(expected, closeTo(dist, 0.01));
    }

    @Test
    public void when78to44then5() {
        double expected = 5;
        Point a = new Point(7, 8);
        Point b = new Point(4, 4);
        double dist = a.distance(b);
        assertThat(expected, closeTo(dist, 0.01));
    }

    @Test
    public void when10and17to2and0then18dot78() {
        double expected = 18.78;
        Point a = new Point(10, 17);
        Point b = new Point(2, 0);
        double dist = a.distance(b);
        assertThat(expected, closeTo(dist, 0.01));
    }
}