package ru.job4j.tracker;

public interface UserAction {
    String name();

    boolean execution(Input input, Tracker tracker);
}
