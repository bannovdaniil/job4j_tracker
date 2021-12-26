package ru.job4j.ex;

public class FindEl {

    public static void main(String[] args) {
        int result = -1;
        try {
            result = indexOf(new String[]{"222", "223"}, "123");
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }

    public static int indexOf(String[] value, String key) throws ElementNotFoundException {
        int rsl = -1;
        for (int i = 0; i < value.length; i++) {
            if (key.equals(value[i])) {
                rsl = i;
                break;
            }
        }
        if (rsl == -1) {
            throw new ElementNotFoundException("Element not found.");
        }

        return rsl;
    }
}
