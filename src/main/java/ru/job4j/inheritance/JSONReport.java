package ru.job4j.inheritance;

public class JSONReport extends TextReport {
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String generate(String name, String body) {
        String ln = System.lineSeparator();
        return "{" + ln
                + "\t\"name\" : \"" + name + "\"," + ln
                + "\t\"body\" : \"" + body + "\"" + ln
                + "}";
    }
}
