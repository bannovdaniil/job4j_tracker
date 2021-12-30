package ru.job4j.search;

import java.util.ArrayList;
import java.util.function.Predicate;

public class PhoneDictionary {
    private ArrayList<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    public ArrayList<Person> find(String key) {
        Predicate<String> equalKey = s -> s.contains(key);
        Predicate<Person> combine = (person) ->  equalKey.test(person.getAddress())
                    || equalKey.test(person.getName())
                    || equalKey.test(person.getPhone())
                    || equalKey.test(person.getSurname());

        ArrayList<Person> result = new ArrayList<>();
        for (Person person : persons) {
            if (combine.test(person)) {
                result.add(person);
            }
        }
        return result;
    }
}
