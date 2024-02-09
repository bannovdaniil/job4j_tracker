package ru.job4j.tracker;

import ru.job4j.tracker.action.UserAction;
import ru.job4j.tracker.action.impl.*;
import ru.job4j.tracker.io.Input;
import ru.job4j.tracker.io.Output;
import ru.job4j.tracker.io.impl.ConsoleInput;
import ru.job4j.tracker.io.impl.ConsoleOutput;
import ru.job4j.tracker.io.impl.ValidateInput;
import ru.job4j.tracker.repository.Store;
import ru.job4j.tracker.repository.impl.HbmTracker;
import ru.job4j.tracker.repository.impl.MemTracker;
import ru.job4j.tracker.repository.impl.SqlTracker;

import java.util.ArrayList;
import java.util.List;

public class StartUI {
    private final Output out;

    public StartUI(Output out) {
        this.out = out;
    }

    public static void main(String[] args) {
        String version = "hbm";
        Output output = new ConsoleOutput();
        Input input = new ValidateInput(output, new ConsoleInput());

        List<UserAction> actions = new ArrayList<>(
                List.of(
                        new CreateAction(output),
                        new ShowAllItemsAction(output),
                        new EditItemAction(output),
                        new DeleteItemAction(output),
                        new FindItemByIdAction(output),
                        new FindItemsByNameAction(output),
                        new ExitAction(output)
                )
        );

        switch (version) {
            case "test":
                MemTracker testMemTracker = new MemTracker();
                actions.remove(6);

                actions.add(new AddThousandAction(output));
                actions.add(new DeleteAllAction(output));
                actions.add(new ExitAction(output));

                new StartUI(output).init(input, testMemTracker, actions);
                break;
            case "mem":
                MemTracker memTracker = new MemTracker();
                new StartUI(output).init(input, memTracker, actions);
                break;
            case "sql":
                try (SqlTracker sqlTracker = new SqlTracker()) {
                    sqlTracker.init();
                    new StartUI(output).init(input, sqlTracker, actions);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "hbm":
                try (HbmTracker hbmTracker = new HbmTracker()) {
                    hbmTracker.init();
                    new StartUI(output).init(input, hbmTracker, actions);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("Реализация не определенна.");
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
