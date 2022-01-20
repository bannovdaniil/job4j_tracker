package ru.job4j.tracker;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.nullValue;

public class SqlTrackerTest {
    @Test
    public void whenReplace() {
        try (SqlTracker tracker = new SqlTracker()) {
            tracker.init();
            Item bug = new Item();
            bug.setName("Bug");
            tracker.add(bug);
            int id = bug.getId();
            Item bugWithDesc = new Item();
            bugWithDesc.setName("Bug with description");
            tracker.replace(id, bugWithDesc);
            assertThat(tracker.findById(id).getName(), is("Bug with description"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenTestFindById() {
        try (SqlTracker tracker = new SqlTracker()) {
            tracker.init();
            Item bug = new Item("Bug");
            Item item = tracker.add(bug);
            Item result = tracker.findById(item.getId());
            assertThat(result.getName(), is(item.getName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenTestFindAll() {
        try (SqlTracker tracker = new SqlTracker()) {
            tracker.init();
            int records = tracker.findAll().size();
            Item first = new Item("First");
            Item second = new Item("Second");
            tracker.add(first);
            tracker.add(second);
            Item result = tracker.findAll().get(records);
            assertThat(result.getName(), is(first.getName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenTestFindByNameCheckArrayLength() {
        try (SqlTracker tracker = new SqlTracker()) {
            tracker.init();
            Item first = new Item("First");
            Item second = new Item("Second");
            int records = tracker.findByName(first.getName()).size();
            tracker.add(first);
            tracker.add(second);
            tracker.add(new Item("First"));
            tracker.add(new Item("Second"));
            tracker.add(new Item("First"));
            List<Item> result = tracker.findByName(first.getName());
            assertThat(result.size(), is(records + 3));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenTestFindByNameCheckSecondItemName() {
        try (SqlTracker tracker = new SqlTracker()) {
            tracker.init();
            Item first = new Item("First");
            Item second = new Item("Second");
            tracker.add(first);
            tracker.add(second);
            tracker.add(new Item("First"));
            tracker.add(new Item("Second"));
            tracker.add(new Item("First"));
            List<Item> result = tracker.findByName(second.getName());
            assertThat(result.get(1).getName(), is(second.getName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenDelete() {
        try (SqlTracker tracker = new SqlTracker()) {
            tracker.init();
            Item bug = new Item();
            bug.setName("Bug");
            tracker.add(bug);
            int id = bug.getId();
            tracker.delete(id);
            assertThat(tracker.findById(id), is(nullValue()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

