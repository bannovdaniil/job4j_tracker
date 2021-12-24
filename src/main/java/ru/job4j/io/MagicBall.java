package ru.job4j.io;

import java.util.Random;
import java.util.Scanner;

public class MagicBall {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Я великий Оракул. Что ты хочешь узнать? ");
            System.out.print(">");
            String question = scanner.nextLine();
            if (question.indexOf('?') >= 0) {
                break;
            }
            System.out.println("Вопрос \"?\" не обнаружен, попробуй еще раз!");
        }
        int answer = new Random().nextInt(3);
        if (answer == 0) {
            System.out.println("Да");
        } else if (answer == 1) {
            System.out.println("Нет");
        } else {
            System.out.println("may be...");
        }
    }
}
