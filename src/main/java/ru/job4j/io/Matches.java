package ru.job4j.io;

import java.util.Random;
import java.util.Scanner;

public class Matches {
    private static Scanner input = new Scanner(System.in);
    private static boolean turn = true;
    private static int count = 11;

    public static void main(String[] args) {
        System.out.println("Игра 11.");
        while (count > 0) {
            String player = turn ? "Первый игрок" : "Второй игрок";
            System.out.println(player + " введите число от 1 до 3:");
            int matches = Integer.parseInt(input.nextLine());
            if (check(matches, count)) {
                continue;
            }
            turn = !turn;
            count -= matches;
            sayCount(count);
            if (count > 0) {
                count = turnComputer(count);
                turn = !turn;
            }
        }
        sayWin(turn);
    }

    private static boolean check(int matches, int count) {
        if (matches > count) {
            System.out.println("Нет такого количества спичек, повторите ввод...");
            return true;
        }
        if (matches < 1 || matches > 3) {
            System.out.println("Ошибка ввода, повторите ввод...");
            return true;
        }
        return false;
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
            System.out.println("\nВаш ход...");
        }
        return count;
    }

    private static void sayCount(int count) {
        System.out.println("Осталось " + count + sayRight(count));
    }

    private static String sayRight(int x) {
        String s = " спички.";
        if (x == 1) {
            s = " спичка.";
        } else if (x == 0 || x >= 5) {
            s = " спичек.";
        }
        return s;
    }

}
