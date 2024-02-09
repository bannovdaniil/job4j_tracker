package ru.job4j.tracker.action.impl;

import ru.job4j.tracker.action.UserAction;
import ru.job4j.tracker.io.Input;
import ru.job4j.tracker.io.Output;
import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.repository.Store;

public class DeleteAllAction implements UserAction {
    private final Output out;

    public DeleteAllAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Delete all Items";
    }

    @Override
    public boolean execution(Input input, Store tracker) {
        out.println("=== Delete all Items ===");
        var items = tracker.findAll();
        for (Item item : items) {
            tracker.delete(item.getId());
        }
        out.println("Все записи удалены");
        return true;
    }

}
