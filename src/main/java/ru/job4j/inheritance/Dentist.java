package ru.job4j.inheritance;

public class Dentist extends Doctor {
    private String adr;

    public Dentist(String name, String surname, String education,
                   String birthday, int price, String adr) {
        super(name, surname, education, birthday, price);
        this.adr = adr;
    }

    public void treat() {
        System.out.println("Вырву зуб по адресу: " + this.adr);
    }
}
