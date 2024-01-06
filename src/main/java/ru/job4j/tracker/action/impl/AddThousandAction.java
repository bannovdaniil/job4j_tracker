package ru.job4j.tracker.action.impl;

import ru.job4j.tracker.io.Input;
import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.io.Output;
import ru.job4j.tracker.action.UserAction;
import ru.job4j.tracker.repository.Store;

public class AddThousandAction implements UserAction {
    private final Output out;

    public AddThousandAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Add Thousand new Items";
    }

    @Override
    public boolean execution(Input input, Store tracker) {
        out.println("=== Create are Thousand new Items ===");
        for (int i = 0; i < 1000; i++) {
            Item item = new Item();
            item.setName(String.valueOf(i));
            item.setId(i);
            tracker.add(item);
        }
        out.println("Заявки добавлены.");
        return true;
    }
}