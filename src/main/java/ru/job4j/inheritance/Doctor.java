package ru.job4j.inheritance;

public class Doctor extends Profession {
    int price;

    public Doctor(String name, String surname, String education, String birthday, int price) {
        super(name, surname, education, birthday);
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
