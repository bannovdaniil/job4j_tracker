package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.service.ItemDescByName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ItemDescByNameTest {
    @Test
    public void whenDescByName() {
        Item bug1 = new Item("Bug1");
        Item bug2 = new Item("Bug2");
        Item bug3 = new Item("Bug3");
        Item bug4 = new Item("Bug4");
        Item bug5 = new Item("Bug5");
        List<Item> excepted = new ArrayList<>();
        excepted.add(bug5);
        excepted.add(bug4);
        excepted.add(bug3);
        excepted.add(bug2);
        excepted.add(bug1);
        List<Item> list = new ArrayList<>();
        list.add(bug5);
        list.add(bug2);
        list.add(bug1);
        list.add(bug4);
        list.add(bug3);
        Collections.sort(list, new ItemDescByName());
        assertThat(list, is(excepted));
    }
}