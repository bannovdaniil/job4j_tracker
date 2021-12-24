package ru.job4j.inheritance;

public class Surgeon extends Doctor {
    private int count;

    public Surgeon(String name, String surname, String education,
                   String birthday, int price, int count) {
        super(name, surname, education, birthday, price);
        this.count = count;
    }

    public void sayCount() {
        System.out.println("Провел успешных упераций: " + this.count);
    }
}
