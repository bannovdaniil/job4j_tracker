package ru.job4j.school.model;

import java.util.List;

public record Pupil(String name, List<Subject> subjects) {
}