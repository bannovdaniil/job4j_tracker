package ru.job4j.tracker.repository.impl;

import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.repository.Store;

import java.util.ArrayList;
import java.util.List;

public class MemTracker implements Store {
    private final List<Item> items = new ArrayList<>();
    private int ids = 1;

    public Item add(Item item) {
        item.setId(ids++);
        items.add(item);
        return item;
    }

    public List<Item> findByName(String key) {
        List<Item> temp = new ArrayList<>();
        for (Item item : items) {
            if (key.equals(item.getName())) {
                temp.add(item);
            }
        }
        return temp;
    }

    public List<Item> findAll() {
        return List.copyOf(items);
    }

    public Item findById(int id) {
        int index = indexOf(id);
        return index != -1 ? items.get(index) : null;
    }

    private int indexOf(int id) {
        int rsl = -1;
        for (int index = 0; index < items.size(); index++) {
            if (this.items.get(index).getId() == id) {
                rsl = index;
                break;
            }
        }
        return rsl;
    }

    public boolean replace(int id, Item item) {
        int idx = indexOf(id);
        boolean rsl = idx != -1;
        if (rsl) {
            item.setId(id);
            items.set(idx, item);
        }
        return rsl;
    }

    public boolean delete(int id) {
        int idx = indexOf(id);
        boolean rsl = idx != -1;
        if (rsl) {
            items.remove(idx);
        }
        return rsl;
    }
}