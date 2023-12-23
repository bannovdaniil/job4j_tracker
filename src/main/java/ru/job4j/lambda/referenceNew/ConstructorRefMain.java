package ru.job4j.lambda.referenceNew;

public class ConstructorRefMain {
    public static void main(String[] args) {
        FuncInterface modelConstructor = Model::new;
        Model model = modelConstructor.function("Example");
        System.out.println("model = " + model);
    }
}
