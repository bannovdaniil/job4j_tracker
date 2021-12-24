package ru.job4j.tracker;

import java.util.Arrays;

public class Tracker {
    private final Item[] items = new Item[100];
    private int ids = 1;
    private int size = 0;

    public Item add(Item item) {
        item.setId(ids++);
        items[size++] = item;
        return item;
    }

    public Item[] findByName(String key) {
        Item[] temp = new Item[size];
        int count = 0;
        for (int i = 0; i < this.size; i++) {
            if (key.equals(items[i].getName())) {
                temp[count++] = items[i];
            }
        }
        return Arrays.copyOf(temp, count);
    }

    public Item[] findAll() {
        return Arrays.copyOf(this.items, this.size);
    }

    public Item findById(int id) {
        int index = indexOf(id);
        return index != -1 ? items[index] : null;
    }

    private int indexOf(int id) {
        int rsl = -1;
        for (int index = 0; index < this.size; index++) {
            if (this.items[index].getId() == id) {
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
            items[idx] = item;
        }
        return rsl;
    }

    public boolean delete(int id) {
        int idx = indexOf(id);
        boolean rsl = idx != -1;
        if (rsl) {
            System.arraycopy(this.items, idx + 1, this.items, idx, this.size - 1 - idx);
            items[size - 1] = null;
            size--;
        }
        return rsl;
    }
}