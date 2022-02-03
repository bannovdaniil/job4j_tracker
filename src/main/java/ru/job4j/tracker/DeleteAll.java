package ru.job4j.tracker;

public class DeleteAll implements UserAction {
    private final Output out;

    public DeleteAll(Output out) {
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
