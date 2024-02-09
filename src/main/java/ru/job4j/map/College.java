package ru.job4j.map;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class College {
    private final Map<Student, Set<Subject>> students;

    public College(Map<Student, Set<Subject>> students) {
        this.students = students;
    }

    public Optional<Student> findByAccount(String account) {
        return students.keySet()
                .stream()
                .filter(s -> account.equals(s.getAccount()))
                .findFirst();
    }

    public Optional<Subject> findBySubjectName(String account, String name) {
        Optional<Subject> rsl = Optional.empty();
        Optional<Student> s = findByAccount(account);
        if (s.isPresent()) {
            Set<Subject> subjects = students.get(s.get());
            rsl = subjects
                    .stream()
                    .filter(subj -> Optional.ofNullable(subj).isPresent())
                    .filter(subj -> name.equals(subj.getName()))
                    .findFirst();
        }
        return rsl;
    }
}
