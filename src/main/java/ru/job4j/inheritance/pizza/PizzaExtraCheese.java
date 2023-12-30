package ru.job4j.inheritance.pizza;

/**
 * Пицца с сыром.
 */
public class PizzaExtraCheese extends Pizza {
    @Override
    public String name() {
        return super.name() + " + " + "extra cheese";
    }
}
