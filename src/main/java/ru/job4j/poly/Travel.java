package ru.job4j.poly;

public class Travel {
    public static void main(String[] args) {
        Vehicle bus = new Bus();
        Vehicle plane = new Plane();
        Vehicle train = new Train();
        Vehicle[] transports = new Vehicle[]{
                bus, train, plane
        };
        for (Vehicle i : transports) {
            i.move();
            System.out.println("Скорость: " + i.getSpeed());
        }
    }
}
