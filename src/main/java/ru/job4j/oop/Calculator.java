package ru.job4j.oop;

public class Calculator {
    private static int x = 5;

    public static int sum(int y) {
        return x + y;
    }

    public static int minus(int y) {
        return y - x;
    }

    public static void main(String[] args) {
        System.out.println(Calculator.sum(1));
        System.out.println(Calculator.minus(1));
        Calculator calculator = new Calculator();
        System.out.println(calculator.divide(15));
        System.out.println(calculator.multiply(2));
        System.out.println(calculator.sumAllOperationSumAllOperation(5));
    }

    public int multiply(int a) {
        return x * a;
    }

    public int divide(int y) {
        return y / x;
    }

    public int sumAllOperationSumAllOperation(int y) {
        return sum(y) + this.multiply(y) + minus(y) + this.divide(y);
    }

}
