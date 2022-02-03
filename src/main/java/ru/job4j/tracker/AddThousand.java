package ru.job4j.tracker;

public class AddThousand implements UserAction {
    private final Output out;

    public AddThousand(Output out) {
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