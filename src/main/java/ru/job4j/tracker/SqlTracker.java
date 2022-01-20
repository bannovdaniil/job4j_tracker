package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store, AutoCloseable {
    private Connection cn;
    private final String tableName = "items";

    public void init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    /**
     * добавить новую запись в таблицу
     *
     * @param item - значение
     * @return - саму и вернуть с указанием присвоенного ID
     */
    @Override
    public Item add(Item item) {
        try (PreparedStatement statement = cn.prepareStatement(
                "INSERT INTO items (name, created) VALUES(?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, item.getName());
            statement.setTimestamp(2, java.sql.Timestamp.valueOf(item.getCreated()));
            statement.execute();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    item.setId(generatedKeys.getInt("id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    /**
     * заменить имя
     *
      * @param id - номер
     * @param item - тут есть новое имя
     * @return - успех?
     */
    @Override
    public boolean replace(int id, Item item) {
        boolean result = true;
        try (PreparedStatement statement =
                     cn.prepareStatement("UPDATE items SET name = ? WHERE id = ?")) {
            statement.setString(1, item.getName());
            statement.setInt(2, id);
            result = statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    /**
     * удаление записи
     *
     * @param id - номер
     * @return - успех?
     */
    @Override
    public boolean delete(int id) {
        boolean result = true;
        try (PreparedStatement statement =
                     cn.prepareStatement("DELETE FROM items WHERE id = ?")) {
            statement.setInt(1, id);
            result = statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    /**
     * взять все записи из базы
     * @return - список всех элементов
     */
    @Override
    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement statement =
                     cn.prepareStatement("SELECT * FROM items ORDER BY id")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    items.add(new Item(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getTimestamp("created").toLocalDateTime()
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement statement =
                     cn.prepareStatement("SELECT * FROM items WHERE name = ?")) {
            statement.setString(1, key);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    items.add(new Item(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getTimestamp("created").toLocalDateTime()
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public Item findById(int id) {
        Item item = null;
        try (PreparedStatement statement =
                     cn.prepareStatement("SELECT * FROM items WHERE id = ?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    item = new Item(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getTimestamp("created").toLocalDateTime()
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

}