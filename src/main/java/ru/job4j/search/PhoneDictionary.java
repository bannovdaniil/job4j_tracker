package ru.job4j.search;

import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * то что было до извращений с or
 * Predicate<String> equalKey = s -> s.contains(key);
 */
public class PhoneDictionary {
    private ArrayList<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    public ArrayList<Person> find(String key) {
        Predicate<Person> address = s -> s.getAddress().contains(key);
        Predicate<Person> name = s -> s.getName().contains(key);
        Predicate<Person> phone = s -> s.getPhone().contains(key);
        Predicate<Person> surname = s -> s.getSurname().contains(key);
        Predicate<Person> combine = (person) ->
                address.or(name.or(phone.or(surname))).test(person);
        ArrayList<Person> result = new ArrayList<>();
        for (Person person : persons) {
            if (combine.test(person)) {
                result.add(person);
            }
        }
        return result;
    }
}
