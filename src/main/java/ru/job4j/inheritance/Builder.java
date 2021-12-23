package ru.job4j.inheritance;

public class Builder extends Engineer {
    private int age;

    public Builder(String name, String surname, String education,
                   String birthday, int location, int age) {
        super(name, surname, education, birthday, location);
        this.age = age;
    }

    public void doBuilding() {
        System.out.println("Строю дом. Мне " + this.age + " лет");
    }
}
