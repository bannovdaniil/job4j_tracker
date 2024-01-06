package ru.job4j.tracker.action;

import ru.job4j.tracker.io.Input;
import ru.job4j.tracker.repository.Store;

public interface UserAction {
    String name();

    boolean execution(Input input, Store tracker);
}
