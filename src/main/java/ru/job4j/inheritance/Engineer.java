package ru.job4j.inheritance;

public class Engineer extends Profession {
    private int location;

    public Engineer(String name, String surname, String education,
                    String birthday, int location) {
        super(name, surname, education, birthday);
        this.location = location;
    }

    public int getLocation() {
        return location;
    }
}
