package ru.job4j.tracker.repository.impl;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.tracker.model.Item;

import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class HbmTrackerTest {
    private static HbmTracker tracker;

    @BeforeAll
    static void beforeAll() {
        tracker = new HbmTracker();
        tracker.init();
    }

    @AfterAll
    static void afterAll() {
        tracker.close();
    }

    @AfterEach
    void wipeTable() throws SQLException {
        tracker.findAll().forEach(
                item -> tracker.delete(item.getId())
        );
    }

    @Test
    void whenAddNewItemThenTrackerHasSameItem() {
        Item item = new Item();
        item.setName("test1");
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        assertThat(result.getName()).isEqualTo(item.getName());
    }

    @Test
    void whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() {
        Item item = new Item("item");
        tracker.add(item);
        assertThat(tracker.findById(item.getId())).isEqualTo(item);
    }

    @Test
    void whenReplace() {
        Item bug = new Item("Bug");
        tracker.add(bug);
        int id = bug.getId();
        Item bugWithDesc = new Item("Bug with description");
        bugWithDesc.setId(id);
        tracker.replace(id, bugWithDesc);
        assertThat(tracker.findById(id).getName()).isEqualTo(bugWithDesc.getName());
    }

    @Test
    void whenTestFindById() {
        Item bug = new Item("Bug");
        Item item = tracker.add(bug);
        assertThat(tracker.findById(item.getId())).isEqualTo(bug);
    }

    @Test
    void whenTestFindAll() {
        Item first = new Item("First");
        Item second = new Item("Second");
        tracker.add(first);
        tracker.add(second);
        assertThat(tracker.findAll()).isEqualTo(List.of(first, second));
    }

    @Test
    void whenTestFindByNameCheckSecondItemName() {
        Item first1 = new Item("First");
        Item first2 = new Item("First");
        Item second1 = new Item("Second");
        Item second2 = new Item("Second");
        tracker.add(first1);
        tracker.add(second1);
        tracker.add(second2);
        tracker.add(first2);
        assertThat(tracker.findByName(second1.getName())).isEqualTo(List.of(second1, second2));
    }

    @Test
    void whenDelete() {
        Item bug = new Item();
        bug.setName("Bug");
        tracker.add(bug);
        int id = bug.getId();
        tracker.delete(id);
        assertThat(tracker.findById(id)).isNull();
    }
}