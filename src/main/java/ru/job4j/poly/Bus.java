package ru.job4j.poly;

public class Bus implements Transport {

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
}
