package ru.job4j.map;

import java.util.Map;
import java.util.Set;
import java.util.Optional;

public class College {
    private final Map<Student, Set<Subject>> students;

    public College(Map<Student, Set<Subject>> students) {
        this.students = students;
    }

    public Optional<Student> findByAccount(String account) {
        Optional<Student> rsl = Optional.empty();
        for (Student s : students.keySet()) {
            if (account.equals(s.getAccount())) {
                rsl = Optional.of(s);
                break;
            }
        }
        return rsl;
    }

    /**
     * isPresent, как и isEmpy не гарантируют не чего, и также пропускают
     * с null поинтером, чтобы извлечь не Null, к переменной желательно
     * добавить еще и .get() но ошибка не явная, в тестах без этого валится
     * интерпритатор молчит, поэтому внимательность и еще раз внимательность...
     * @param account - номер студента в списке ?
     * @param name - имя студента
     * @return - предметы
     */
    public Optional<Subject> findBySubjectName(String account, String name) {
        Optional<Subject> rsl = Optional.empty();
        Optional<Student> s = findByAccount(account);
        if (s.isPresent()) {
            Set<Subject> subjects = students.get(s.get());
            for (Subject subj : subjects) {
                if (name.equals(subj.getName())) {
                    rsl = Optional.of(subj);
                    break;
                }
            }
        }
        return rsl;
    }
}
