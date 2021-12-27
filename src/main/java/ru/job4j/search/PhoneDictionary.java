package ru.job4j.search;

import java.util.ArrayList;

public class PhoneDictionary {
    private ArrayList<Person> persons = new ArrayList<Person>();

    public void add(Person person) {
        this.persons.add(person);
    }

    private boolean checkKey(String in, String key) {
        return in.toLowerCase().contains(key.toLowerCase());
    }

    public ArrayList<Person> find(String key) {
        ArrayList<Person> result = new ArrayList<>();
        for (Person person : this.persons) {
            if (checkKey(person.getAddress(), key) || checkKey(person.getName(), key)
                    || checkKey(person.getPhone(), key) || checkKey(person.getSurname(), key)) {
                result.add(person);
            }
        }
        return result;
    }
}
