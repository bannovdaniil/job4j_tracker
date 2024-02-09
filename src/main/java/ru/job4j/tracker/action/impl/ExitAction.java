package ru.job4j.tracker.action.impl;

import ru.job4j.tracker.action.UserAction;
import ru.job4j.tracker.io.Input;
import ru.job4j.tracker.io.Output;
import ru.job4j.tracker.repository.Store;

public class ExitAction implements UserAction {
    private final Output out;

    public ExitAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Exit Program";
    }

    @Override
    public boolean execution(Input input, Store tracker) {
        out.println("=== Exit Program ===");
        return false;
    }
}
