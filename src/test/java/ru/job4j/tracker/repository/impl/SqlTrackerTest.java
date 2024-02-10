package ru.job4j.tracker.repository.impl;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.tracker.model.Item;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

public class SqlTrackerTest {
    static Connection connection;

    @BeforeAll
    public static void initConnection() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("db/liquibase_test.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @AfterAll
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @AfterEach
    void wipeTable() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM items")) {
            statement.execute();
        }
    }

    @Test
    void whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        assertThat(tracker.findById(item.getId())).isEqualTo(item);
    }

    @Test
    void whenReplace() {
        SqlTracker tracker = new SqlTracker(connection);
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
        SqlTracker tracker = new SqlTracker(connection);
        Item bug = new Item("Bug");
        Item item = tracker.add(bug);
        assertThat(tracker.findById(item.getId())).isEqualTo(bug);
    }

    @Test
    void whenTestFindAll() {
        SqlTracker tracker = new SqlTracker(connection);
        Item first = new Item("First");
        Item second = new Item("Second");
        tracker.add(first);
        tracker.add(second);
        assertThat(tracker.findAll()).isEqualTo(List.of(first, second));
    }

    @Test
    void whenTestFindByNameCheckSecondItemName() {
        SqlTracker tracker = new SqlTracker(connection);
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
        SqlTracker tracker = new SqlTracker(connection);
        Item bug = new Item();
        bug.setName("Bug");
        tracker.add(bug);
        int id = bug.getId();
        tracker.delete(id);
        assertThat(tracker.findById(id)).isNull();
    }
}