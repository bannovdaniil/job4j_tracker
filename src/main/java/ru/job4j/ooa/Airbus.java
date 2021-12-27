package ru.job4j.ooa;

public final class Airbus extends Aircraft {
    private static final int[] COUNT_ENGINE = new int[]{2};

    private String name;

    public Airbus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void printModel() {
        System.out.println("Модель самолета: " + name);
    }

    public void printCountEngine() {
        if ("A380".equals(this.name)) {
            COUNT_ENGINE[0] = 4;
        }
        System.out.println("Количество двигателей равно: " + COUNT_ENGINE[0]);
    }

    @Override
    public String toString() {
        return "Airbus{"
                + "name='" + name + '\''
                + '}';
    }
}
