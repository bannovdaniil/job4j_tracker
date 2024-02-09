package ru.job4j.tracker.service;

import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.repository.Store;
import ru.job4j.tracker.repository.impl.MemTracker;

import java.util.List;

public final class SingleTracker {
    private static SingleTracker instance = null;
    private Store tracker = new MemTracker();

    private SingleTracker() {
    }

    public synchronized SingleTracker getInstance() {
        if (instance == null) {
            instance = new SingleTracker();
        }
        return instance;
    }

    public Item add(Item item) {
        return tracker.add(item);
    }

    public List<Item> findByName(String key) {
        return tracker.findByName(key);
    }

    public List<Item> findAll() {
        return tracker.findAll();
    }

    public Item findById(int id) {
        return tracker.findById(id);
    }

    public boolean replace(int id, Item item) {
        return tracker.replace(id, item);
    }

    public boolean delete(int id) {
        return tracker.delete(id);
    }
}