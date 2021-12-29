package ru.job4j.collection;

import java.util.Comparator;

/**
 * Обратная сортировка депортаментов, для извращенцев:
 * 1. Нам нужно сравнить
 * первые элементы по убыванию,
 * 2. если они равны, то сравнить последующие
 * элементы, но в возрастающем порядке.
 * (c) Bannov Daniil
 */
public class DepDescComp implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        String s1 = o1.split("/")[0];
        String s2 = o2.split("/")[0];
        int result = s2.compareTo(s1);
        return (result == 0) ? o1.compareTo(o2) : result;
    }
}