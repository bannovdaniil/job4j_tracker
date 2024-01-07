package ru.job4j.school;

import ru.job4j.school.model.Label;
import ru.job4j.school.model.Pupil;
import ru.job4j.school.model.Subject;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * В этом задании необходимо реализовать класс для подсчета статистики по аттестатам учеников.
 */
public class AnalyzeByMap {
    /**
     * Метод averageScore() - вычисляет общий средний балл.
     *
     * @param pupils - список учеников.
     * @return - средний балл.
     */
    public static double averageScore(List<Pupil> pupils) {
        return pupils.stream()
                .flatMap(pupil -> pupil.subjects().stream())
                .mapToInt(Subject::score)
                .average().orElse(0.0d);
    }

    /**
     * Метод averageScoreByPupil() - вычисляет средний балл по каждому ученику.
     * То есть берем одного ученика и считаем все его баллы за все предметы и делим на количество предметов.
     * Возвращает список из объекта Label (имя ученика и средний балл).
     *
     * @param pupils - список учеников.
     * @return -  список из объекта Label (имя ученика и средний балл)
     */
    public static List<Label> averageScoreByPupil(List<Pupil> pupils) {
        return pupils.stream()
                .map(pupil -> {
                            double score = pupil.subjects().stream()
                                    .mapToInt(Subject::score)
                                    .average().orElse(0.0d);
                            return new Label(pupil.name(), score);
                        }
                ).toList();
    }

    /**
     * 3. Метод averageScoreBySubject() - вычисляет средний балл по каждому предмету.
     * Например, собираем все баллы учеников по предмету география и делим на количество учеников.
     * Возвращает список из объектов Label (название предмета и средний балл).
     *
     * @param pupils - список учеников.
     * @return - список из объектов Label (название предмета и средний балл).
     */
    public static List<Label> averageScoreBySubject(List<Pupil> pupils) {
        return pupils.stream()
                .flatMap(pupil -> pupil.subjects().stream())
                .collect(
                        Collectors.groupingBy(Subject::name, LinkedHashMap::new,
                                Collectors.averagingDouble(Subject::score)))
                .entrySet()
                .stream()
                .map(subjects -> new Label(subjects.getKey(), subjects.getValue()))
                .toList();
    }

    /**
     * 4. Метод bestStudent() - возвращает лучшего ученика. Лучшим считается ученик с наибольшим суммарным баллом по
     * всем предметам. Возвращает объект Label (имя ученика и суммарный балл).
     *
     * @param pupils - список учеников.
     * @return - Label (имя ученика и суммарный балл).
     */
    public static Label bestStudent(List<Pupil> pupils) {
        return pupils.stream()
                .map(pupil -> {
                    double score = pupil.subjects()
                            .stream()
                            .mapToInt(Subject::score)
                            .sum();
                    return new Label(pupil.name(), score);
                })
                .max(Comparator.comparing(Label::score))
                .orElse(null);
    }

    /**
     * Метод bestSubject() - возвращает предмет с наибольшим баллом для всех студентов. Возвращает объект
     * Label (имя предмета, сумма баллов каждого ученика по этому предмету).
     *
     * @param pupils - список учеников.
     * @return - Label (имя предмета, сумма баллов каждого ученика по этому предмету).
     */
    public static Label bestSubject(List<Pupil> pupils) {
        return pupils.stream()
                .flatMap(pupil -> pupil.subjects().stream())
                .collect(
                        Collectors.groupingBy(Subject::name,
                                Collectors.summingDouble(Subject::score)))
                .entrySet()
                .stream()
                .map(subjects -> new Label(subjects.getKey(), subjects.getValue()))
                .max(Comparator.comparing(Label::score))
                .orElse(null);
    }
}
