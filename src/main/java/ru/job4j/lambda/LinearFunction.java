package ru.job4j.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class LinearFunction {
    List<Double> diapason(int start, int end, Function<Double, Double> func) {
        List<Double> list = new ArrayList<>();
        for (int x = start; x < end; x++) {
            list.add(func.apply((double) x));
        }
        return list;
    }

}
