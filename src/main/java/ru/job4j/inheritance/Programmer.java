package ru.job4j.inheritance;

public class Programmer extends Engineer {
    private String lang;

    public Programmer(String name, String surname, String education,
                      String birthday, int location, String lang) {
        super(name, surname, education, birthday, location);
        this.lang = lang;
    }

    public void showMyLanguage() {
        System.out.println("Я программирую на " + this.lang);
    }
}
