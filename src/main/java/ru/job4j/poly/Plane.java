package ru.job4j.poly;

public class Plane implements Vehicle {
    private int speed = 100;

    @Override
    public void move() {
        System.out.println("I can fly...");
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }
}
