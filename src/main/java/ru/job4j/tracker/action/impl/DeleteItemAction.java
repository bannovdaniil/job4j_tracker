package ru.job4j.tracker.action.impl;

import ru.job4j.tracker.action.UserAction;
import ru.job4j.tracker.io.Input;
import ru.job4j.tracker.io.Output;
import ru.job4j.tracker.repository.Store;

public class DeleteItemAction implements UserAction {
    private final Output out;

    public DeleteItemAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Delete item";
    }

    @Override
    public boolean execution(Input input, Store tracker) {
        out.println("=== Delete item ===");
        int id = input.askInt("Enter id: ");
        if (tracker.delete(id)) {
            out.println("Заявка удалена успешно.");
        } else {
            out.println("Ошибка удаления заявки.");
        }
        return true;
    }
}
