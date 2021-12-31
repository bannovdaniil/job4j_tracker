package ru.job4j.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 1. создается рандомный список от -4 до 5
 * 2. показываем то что получилось на консоль
 * 3. оставляем только >0
 * 4. показываем то что осталось
 * toList() vs collect(Collectors.toList()) появилось только в 16+ версии Java
 */
public class FilterNegativeNumbers {

    public static void main(String[] args) {
        List<Integer> numbers = Stream
                .generate(() -> 6 - new Random().nextInt(10))
                .limit(10).toList();
        System.out.println("In:");
        numbers.forEach((n) -> System.out.print(n + ", "));
        System.out.println(System.lineSeparator() + "Out:");
        List<Integer> positive = numbers.stream().filter(n -> n > 0).collect(Collectors.toList());
        positive.forEach(System.out::println);
    }
}