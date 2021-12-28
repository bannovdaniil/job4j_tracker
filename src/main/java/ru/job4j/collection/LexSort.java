package ru.job4j.collection;

import java.util.Comparator;

public class LexSort implements Comparator<String> {
    private int getInt(String s) {
        return Integer.parseInt(s.split("\\.")[0]);
    }

    @Override
    public int compare(String left, String right) {
        return Integer.compare(getInt(left), getInt(right));
    }
}
