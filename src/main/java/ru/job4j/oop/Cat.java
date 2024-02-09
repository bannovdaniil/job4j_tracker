package ru.job4j.oop;

public class Cat {
    private String food;
    private String name;

    public static void main(String[] args) {
        System.out.println("There are gav's food.");
        Cat gav = new Cat();
        gav.eat("kotleta");
        gav.giveNick("Gav");
        gav.show();
        System.out.println("There are black's food.");
        Cat black = new Cat();
        black.giveNick("Black");
        black.eat("fish");
        black.show();
    }

    public void giveNick(String nick) {
        this.name = nick;
    }

    public void show() {
        System.out.println("Name of cat is " + this.name + " it eat " + this.food);
    }

    public void eat(String meat) {
        this.food = meat;
    }
}
