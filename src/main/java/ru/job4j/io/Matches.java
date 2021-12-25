package ru.job4j.io;

import java.util.Random;
import java.util.Scanner;

public class Matches {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean turn = true;
        int count = 51;
        System.out.println("Игра 51.");
        while (count > 0) {
            String player = turn ? "Первый игрок" : "Второй игрок";
            System.out.println(player + " введите число от 1 до 3:");
            int matches = Integer.parseInt(input.nextLine());
            if (check(matches, count)) {
                turn = !turn;
                count -= matches;
                sayCount(count);
                if (count > 0) {
                    count = turnComputer(count);
                    turn = !turn;
                }
            }
        }
        sayWin(turn);
    }

    private static boolean check(int matches, int count) {
        boolean result = false;
        if (matches > count) {
            System.out.println("Нет такого количества спичек, повторите ввод...");
        } else if (matches < 1 || matches > 3) {
            System.out.println("Ошибка ввода, повторите ввод...");
        } else {
            result = true;
        }
        return result;
    }

    private static void sayWin(boolean turn) {
        if (!turn) {
            System.out.println("Выиграл первый игрок");
        } else {
            System.out.println("Выиграл второй игрок");
        }
    }

    private static int turnComputer(int count) {
        int num;
        if (count <= 3) {
            num = count;
        } else {
            num = new Random().nextInt(2) + 1;
        }
        count -= num;
        System.out.println("Компьютер взял " + num + sayRight(num));
        sayCount(count);
        if (count > 0) {
            System.out.println(System.lineSeparator() + "Ваш ход...");
        }
        return count;
    }

    private static void sayCount(int count) {
        System.out.println("Осталось " + count + sayRight(count));
    }

    private static String sayRight(int x) {
        String s = " спички.";
        int lastNum = x % 10;
        if (lastNum == 1 && (x < 10 || x > 20)) {
            s = " спичка.";
        } else if ((lastNum == 0 || lastNum >= 5) || (x >= 10 && x <= 20)) {
            s = " спичек.";
        }
        return s;
    }

}
