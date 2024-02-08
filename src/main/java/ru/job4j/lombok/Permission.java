package ru.job4j.lombok;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

import java.util.List;

@Getter
@Builder(builderMethodName = "of")
@ToString
public class Permission {
    private int id;
    private String name;
    @Singular("ruleBy")
    private List<String> rules;

    public static void main(String[] args) {
        Permission permission = Permission.of()
                .id(2)
                .name("Good day commander!")
                .ruleBy("Rule 1")
                .ruleBy("Rule 2")
                .build();

        System.out.println("permission = " + permission);
    }
}
