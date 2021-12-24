package ru.job4j.poly;

public class Bus implements Transport, Vehicle {
    private int speed = 50;

    @Override
    public void drive() {
        System.out.println("Дыр, Дыр, дыр... Би...Бип.... поехали...");
    }

    @Override
    public void passenger(int count) {
        System.out.println("Внутри находится: " + count + " пасажиров.");
    }

    @Override
    public double refuiling(int count) {
        double price = 50.60;
        return count * price;
    }

    @Override
    public void move() {
        System.out.println("Следущая остановка, понарошкину.");
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }
}
