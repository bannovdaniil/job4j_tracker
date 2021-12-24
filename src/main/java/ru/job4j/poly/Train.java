package ru.job4j.poly;

public class Train implements Vehicle {
    private int speed = 70;

    @Override
    public void move() {
        System.out.println("Поровозик новогодний, чух... чух...");
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }
}
