package ru.job4j.mapstuct.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentDto {
    private int id;
    private String name;
    private String className;
}
