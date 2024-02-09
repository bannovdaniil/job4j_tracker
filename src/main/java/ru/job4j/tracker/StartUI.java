package ru.job4j.tracker;

import ru.job4j.tracker.action.UserAction;
import ru.job4j.tracker.action.impl.*;
import ru.job4j.tracker.io.Input;
import ru.job4j.tracker.io.Output;
import ru.job4j.tracker.io.impl.ConsoleInput;
import ru.job4j.tracker.io.impl.ConsoleOutput;
import ru.job4j.tracker.io.impl.ValidateInput;
import ru.job4j.tracker.repository.Store;
import ru.job4j.tracker.repository.impl.MemTracker;
import ru.job4j.tracker.repository.impl.SqlTracker;

import java.util.List;

public class StartUI {
    private final Output out;

    public StartUI(Output out) {
        this.out = out;
    }

    public static void main(String[] args) {
        String version = "test";
        Output output = new ConsoleOutput();
        Input input = new ValidateInput(output, new ConsoleInput());
        if ("test".equals(version)) {
            MemTracker tracker = new MemTracker();
            List<UserAction> actions = List.of(
                    new CreateAction(output),
                    new ShowAllItemsAction(output),
                    new EditItemAction(output),
                    new DeleteItemAction(output),
                    new FindItemByIdAction(output),
                    new FindItemsByNameAction(output),
                    new AddThousandAction(output),
                    new DeleteAllAction(output),
                    new ExitAction(output)
            );
            new StartUI(output).init(input, tracker, actions);
        } else if ("mem".equals(version)) {
            MemTracker tracker = new MemTracker();
            List<UserAction> actions = List.of(
                    new CreateAction(output),
                    new ShowAllItemsAction(output),
                    new EditItemAction(output),
                    new DeleteItemAction(output),
                    new FindItemByIdAction(output),
                    new FindItemsByNameAction(output),
                    new ExitAction(output)
            );
            new StartUI(output).init(input, tracker, actions);
        } else if ("sql".equals(version)) {
            try (SqlTracker tracker = new SqlTracker()) {
                tracker.init();
                List<UserAction> actions = List.of(
                        new CreateAction(output),
                        new ShowAllItemsAction(output),
                        new EditItemAction(output),
                        new DeleteItemAction(output),
                        new FindItemByIdAction(output),
                        new FindItemsByNameAction(output),
                        new ExitAction(output)
                );
                new StartUI(output).init(input, tracker, actions);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void init(Input input, Store tracker, List<UserAction> actions) {
        boolean run = true;
        while (run) {
            this.showMenu(actions);
            int select = input.askInt("Select: ");
            if (select < 0 || select >= actions.size()) {
                out.println("Wrong input, you can select: 0 .. " + (actions.size() - 1));
                continue;
            }
            UserAction action = actions.get(select);
            run = action.execution(input, tracker);
        }
    }

    private void showMenu(List<UserAction> actions) {
        out.println("Menu:");
        for (int i = 0; i < actions.size(); i++) {
            out.println(i + ". " + actions.get(i).name());
        }
    }
}
