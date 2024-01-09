package ru.job4j.school;

import ru.job4j.school.model.Label;
import ru.job4j.school.model.Pupil;
import ru.job4j.school.model.Subject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * В этом задании необходимо реализовать класс для подсчета статистики по аттестатам учеников.
 * - Дополнительное задание, в методах averageScoreBySubject и bestSubject для реализации
 * необходимо было сформировать промежуточную Map. Подходы по ее сбору могут быть разными.
 * Вашей задачей будет переделать все на использование метода merge();
 */
public class AnalyzeByMap {
    /**
     * Метод averageScore() - вычисляет общий средний балл.
     *
     * @param pupils - список учеников.
     * @return - средний балл.
     */
    public static double averageScore(List<Pupil> pupils) {
        double score = 0.0d;
        int count = 0;

        for (Pupil pupil : pupils) {
            count += pupil.subjects().size();
            for (Subject subject : pupil.subjects()) {
                score += subject.score();
            }
        }
        return score / count;
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
        List<Label> labels = new ArrayList<>();

        for (Pupil pupil : pupils) {
            double score = 0.0d;
            for (Subject subject : pupil.subjects()) {
                score += subject.score();
            }
            labels.add(new Label(pupil.name(), score / pupil.subjects().size()));
        }

        return labels;
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
        Map<String, Long> subjects = new HashMap<>();
        List<Label> labels = new ArrayList<>();

        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                subjects.merge(subject.name(),
                        (long) subject.score(),
                        (oldValue, newValue) -> oldValue + newValue);
            }
        }
        for (Map.Entry<String, Long> subject : subjects.entrySet()) {
            labels.add(new Label(subject.getKey(), (double) subject.getValue() / pupils.size()));
        }

        return labels;
    }

    /**
     * 4. Метод bestStudent() - возвращает лучшего ученика. Лучшим считается ученик с наибольшим суммарным баллом по
     * всем предметам. Возвращает объект Label (имя ученика и суммарный балл).
     *
     * @param pupils - список учеников.
     * @return - Label (имя ученика и суммарный балл).
     */
    public static Label bestStudent(List<Pupil> pupils) {
        Label bestPupil = null;

        for (Pupil pupil : pupils) {
            int score = 0;
            for (Subject subject : pupil.subjects()) {
                score += subject.score();
            }

            if (bestPupil == null || bestPupil.score() < score) {
                bestPupil = new Label(pupil.name(), score);
            }
        }

        return bestPupil;
    }

    /**
     * Метод bestSubject() - возвращает предмет с наибольшим баллом для всех студентов. Возвращает объект
     * Label (имя предмета, сумма баллов каждого ученика по этому предмету).
     *
     * @param pupils - список учеников.
     * @return - Label (имя предмета, сумма баллов каждого ученика по этому предмету).
     */
    public static Label bestSubject(List<Pupil> pupils) {
        Map<String, Long> subjects = new HashMap<>();
        Label bestSubject = null;

        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                subjects.merge(subject.name(),
                        (long) subject.score(),
                        (oldValue, newValue) -> oldValue + newValue);
            }
        }

        for (Map.Entry<String, Long> subject : subjects.entrySet()) {
            if (bestSubject == null || bestSubject.score() < subject.getValue()) {
                bestSubject = new Label(subject.getKey(), subject.getValue());
            }
        }

        return bestSubject;
    }
}
