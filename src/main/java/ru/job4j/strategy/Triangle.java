package ru.job4j.strategy;

public class Triangle implements Shape {

    public static void main(String[] args) {
        System.out.println(new Triangle().draw());
    }

    @Override
    public String draw() {
        String ln = System.lineSeparator();
        return "   ^    " + ln
                + " /   \\ " + ln
                + "/_____\\" + ln;
    }
}

