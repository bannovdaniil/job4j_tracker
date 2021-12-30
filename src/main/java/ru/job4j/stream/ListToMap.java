package ru.job4j.stream;

import java.util.*;
import java.util.stream.Collectors;

public class ListToMap {
    /**
     * вход принимает список студентов List<Student> и вернет результат
     * его преобразования в Map<String, Student>, где:
     * ключ - это фамилия студента;
     * значение - объект ученика.
     * Тема следующая: так как имея повтор, мы не имеем уникальынй объект
     * поэтому distinct работать не будет, пришла в голову светлая мысль
     * про heshSet, но если сразу добавить, то неувидим не одного дубля
     * а нужен хотябы одни, составную lambda приделать не удалось
     * и суппер add возращает, добавился элемент или нет!
     * @param list - Студенты
     * @return - коллекция студентов
     */
    public static Map<String, Student> convert(List<Student> list) {
        Set<String> uniq = new HashSet<>();
        return list.stream()
                .filter(x -> uniq.add(x.getSurname()))
                .collect(Collectors
                        .toConcurrentMap(student -> student.getSurname(), student -> student));
    }
}
