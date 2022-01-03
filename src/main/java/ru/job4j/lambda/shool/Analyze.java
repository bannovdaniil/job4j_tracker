package ru.job4j.lambda.shool;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Класс Analyze получает статистику по аттестатам.
 */
public class Analyze {
    /**
     * Метод averageScore вычисляет общий средний балл.
     * - flatMap() для преобразования в поток объектов Subject;
     * - mapToInt() для последующего преобразования в потом оценок по каждому предмету;
     * - average() для расчета среднего бала по предмету;
     * - orElse() для того чтобы вернуть значение по умолчанию.
     *
     * @param stream - список учеников
     * @return - среднее
     */
    public static double averageScore(Stream<Pupil> stream) {
        return stream
                .flatMap(n -> n.getSubjects().stream())
                .mapToInt(Subject::getScore)
                .average()
                .orElse(0D);
    }

    /**
     * Метод averageScoreBySubject вычисляет средний балл ученика по его предметам.
     * - метод map() для преобразования в поток объектов класса Tuple,
     * внутри метода мы будем создавать эти объекты - там будет
     * фигурировать строка new Tuple();
     * - при этом в конструктор первым параметром будет передаваться имя текущего
     * объекта Pupil - используем соответствующий геттер;
     * - вторым параметром рассчитанный средний балл - расчет можно произвести по
     * той же последовательности, что описана для метода averageScore;
     * <p>
     * - последним методом будет collect(), с помощью которого
     * мы все соберем в коллекцию List.
     *
     * @param stream - список учеников
     * @return Возвращает список из объекта Tuple (имя ученика и средний балл).
     */
    public static List<Tuple> averageScoreBySubject(Stream<Pupil> stream) {
        return stream
                .map((a) -> {
                    double score = a.getSubjects()
                            .stream()
                            .mapToInt(Subject::getScore)
                            .average()
                            .orElse(0D);
                    return new Tuple(a.getName(), score);
                })
                .collect(Collectors.toList());

    }

    /**
     * Метод averageScoreByPupil вычисляет средний балл по всем предметам для каждого ученика.
     * - flatMap() для преобразования в поток объектов Subject;
     * - метод collect() в который мы передаем метод groupingBy()
     * (минимум с двумя параметрами) класса Collectors.
     * При этом карта собирается следующим образом:
     * ключ - это имя предмета,
     * значение - средний балл по этому предмету для каждого ученика.
     * Для расчета среднего балла используйте метод averagingDouble() класса Collectors;
     * <p>
     * - после этого собранную карту мы разбираем с помощью entrySet() и
     * открываем поток с помощью stream();
     * - полученный поток с помощью map() преобразуем в поток объектов класса Tuple,
     * внутри метода мы будем создавать эти объекты
     * - там будет фигурировать строка new Tuple();
     * - в конструктор мы будем передавать параметры с помощью методов
     * getKey() и getValue() интерфейса Entry;
     * - последним методом будет collect(), с помощью которого мы все соберем в коллекцию List.
     *
     * @param stream - список учеников
     * @return - Возвращает список из объекта Tuple (название предмета и средний балл).
     */
    public static List<Tuple> averageScoreByPupil(Stream<Pupil> stream) {
        return stream
                .flatMap((n) -> n.getSubjects().stream())
                .collect(
                        Collectors.groupingBy(Subject::getName, LinkedHashMap::new,
                                Collectors.averagingDouble(Subject::getScore)))
                .entrySet()
                .stream()
                .map((n) -> new Tuple(n.getKey(), n.getValue()))
                .collect(Collectors.toList());
    }

    /**
     * Метод bestStudent - возвращает лучшего ученика. Лучшим считается ученик
     * с наибольшим баллом по всем предметам.
     * - в данном методе мы рассчитываем суммарный балл,
     * а не средний - поэтому вместо average() нужно использовать
     * метод sum();
     * - терминальной операцией будет не метод collect(),
     * а использование метода max(), в который будем передавать компаратор.
     * При этом компаратор определит объект Tuple,
     * у которого значение Score будет максимальным;
     * - orElse() для того чтобы вернуть значение по умолчанию.
     *
     * @param stream - список учеников
     * @return -
     */
    public static Tuple bestStudent(Stream<Pupil> stream) {
        return stream
                .map((a) -> {
                    double score = a.getSubjects()
                            .stream()
                            .mapToInt(Subject::getScore)
                            .sum();
                    return new Tuple(a.getName(), score);
                })
                .max(Comparator.comparing(Tuple::getScore))
                .orElse(null);
    }

    /**
     * Метод bestSubject - возвращает предмет с наибольшим баллом для всех студентов.
     * - в данном методе мы рассчитываем суммарный балл, а не средний -
     * поэтому вместо averagingDouble()
     * нужно использовать метод summingDouble();
     * - терминальной операцией будет не метод collect(), а использование метода max(),
     * в который будем передавать компаратор. При этом компаратор определит
     * объект Tuple, у которого значение Score будет максимальным;
     * - orElse() для того чтобы вернуть значение по умолчанию.
     * Все методы нужно реализовывать в одном потоке, т.е. пишете return и
     * последовательно вызываете все методы, о которых было указано выше.
     *
     * @param stream - список учеников
     * @return - Возвращает объект Tuple (имя предмета, сумма баллов каждого ученика по этому предмету)
     */
    public static Tuple bestSubject(Stream<Pupil> stream) {
        return stream
                .flatMap((n) -> n.getSubjects().stream())
                .collect(
                        Collectors.groupingBy(Subject::getName, LinkedHashMap::new,
                                Collectors.summingDouble(Subject::getScore)))
                .entrySet()
                .stream()
                .map((n) -> new Tuple(n.getKey(), n.getValue()))
                .max(Comparator.comparing(Tuple::getScore))
                .orElse(null);
    }
}
