package ru.job4j.oop;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;

public class TriangleTest {

    @Test
    public void area() {
        double expected = 8;
        Point a = new Point(0, 0);
        Point b = new Point(4, 0);
        Point c = new Point(0, 4);
        Triangle triangle = new Triangle(a, b, c);
        double rsl = triangle.area();
        assertThat(expected, closeTo(rsl, 0.001));
    }

    @Test
    public void whenTriangleNotExistThenMinusOne() {
        double expected = -1;
        Point a = new Point(0, 0);
        Point b = new Point(0, 5);
        Point c = new Point(0, 10);
        Triangle triangle = new Triangle(a, b, c);
        double rsl = triangle.area();
        assertThat(expected, closeTo(rsl, 0.001));
    }
}