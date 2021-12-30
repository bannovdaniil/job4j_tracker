package ru.job4j.search;

import java.util.ArrayList;
import java.util.function.Predicate;

public class PhoneDictionary {
    private ArrayList<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    private boolean checkKey(String in, String key) {
        return in.contains(key);
    }

    public ArrayList<Person> find(String key) {
        Predicate<Person> combine = (person) -> (checkKey(person.getAddress(), key)
                || checkKey(person.getName(), key)
                || checkKey(person.getPhone(), key)
                || checkKey(person.getSurname(), key));
        ArrayList<Person> result = new ArrayList<>();
        for (Person person : persons) {
            if (combine.test(person)) {
                result.add(person);
            }
        }
        return result;
    }
}
