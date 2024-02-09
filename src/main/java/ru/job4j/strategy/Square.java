package ru.job4j.strategy;

public class Square implements Shape {

    public static void main(String[] args) {
        System.out.println(new Square().draw());
    }

    @Override
    public String draw() {
        String ln = System.lineSeparator();
        return "________" + ln
                + "|       |" + ln
                + "|       |" + ln
                + "|_______|";
    }
}
