package ru.job4j.pojo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class College {
    public static void main(String[] args) {
        Student student = new Student();
        DateTimeFormatter fDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        student.setFio("Баннов Даниил Александрович");
        student.setGroup("257/2 ФВС");
        student.setReceipt(LocalDateTime.now());

        System.out.println(student.getFio() + ", Группа: " + student.getGroup()
                + ", Дата зачисления: " + student.getReceipt().format(fDate));
    }
}
