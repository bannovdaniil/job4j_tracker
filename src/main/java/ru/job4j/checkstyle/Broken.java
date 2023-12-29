package ru.job4j.checkstyle;

/**
 * по модификаторам доступа тут явно беда, не хватает Getters и Setters.
 * задача стояла, довести все до состояния удовлетворения CheckStyle.
 * 1. Именна переменных преведены в вид обусловленный конвенцией.
 * 2. Переменные находятся в самом начале класса, и отсортированы по уровню доступа.
 * 3. Коструктор перенесен в самый верх.
 * 4. Добавленны фигурные скобки в условие if.
 * 5. Количество переменных в методе не может превышать 8. Оставил 7.
 * 6. Убранны двойные пустые строки.
 */
public class Broken {
    private static final String NEW_VALUE = "";
    private String name;
    private String surname;
    private int sizeOfEmpty = 10;

    public Broken() {
    }

    public Broken(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public void echo() {
    }

    public void media(Object obj) {
        if (obj != null) {
            System.out.println(obj);
        }
    }

    public void method(int a, int b, int c, int d, int e, int f, int g) {
    }
}