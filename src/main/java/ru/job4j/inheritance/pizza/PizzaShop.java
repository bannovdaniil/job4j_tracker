package ru.job4j.inheritance.pizza;

/**
 * 6. Вызов переопределенного метода родителя - super.method ... [#504871]
 * В этом уроке мы поговорим о том, как вызвать переопределенный метод родителя - super.method
 * Задание.
 * 1. В этом задании нужно доработать иерархию пиццы: Pizza -> PizzaExtraCheese -> PizzaExtraCheeseExtraTomato.
 */
public class PizzaShop {
    public static void main(String[] args) {
        Pizza pizza = new Pizza();
        PizzaExtraCheese pizzaExtraCheese = new PizzaExtraCheese();
        PizzaExtraCheeseExtraTomato pizzaExtraCheeseExtraTomato = new PizzaExtraCheeseExtraTomato();
        System.out.println(pizza.name());
        System.out.println(pizzaExtraCheese.name());
        System.out.println(pizzaExtraCheeseExtraTomato.name());
    }
}
