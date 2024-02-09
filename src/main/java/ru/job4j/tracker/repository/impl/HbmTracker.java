package ru.job4j.tracker.repository.impl;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.internal.SessionImpl;
import org.hibernate.query.Query;
import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.repository.Store;

import java.io.InputStream;
import java.sql.Connection;
import java.util.List;
import java.util.Properties;

public class HbmTracker implements Store, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sessionFactory = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    public void init() {
        Session session = sessionFactory.openSession();
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("db/liquibase.properties")) {
            Properties config = new Properties();
            config.load(in);
            Connection connection = ((SessionImpl) session).connection();
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
            Liquibase liquibase = new Liquibase(config.getProperty("changeLogFile"), new ClassLoaderResourceAccessor(), database);
            liquibase.dropAll();
            liquibase.update();
        } catch (Exception e) {
            throw new RuntimeException("Can't init Hibernate " + e.getMessage());
        } finally {
            session.close();
        }
    }

    /**
     * Добавить новую запись в таблицу
     *
     * @param item - значение
     * @return - саму и вернуть с указанием присвоенного ID
     */
    @Override
    public Item add(Item item) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.persist(item);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return item;
    }

    /**
     * Заменить имя
     *
     * @param id   - номер
     * @param item - тут есть новое имя
     * @return - успех?
     */
    @Override
    public boolean replace(int id, Item item) {
        boolean result = false;
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query<Item> query = session.createQuery("""
                    UPDATE Item i
                    SET i.name = :name
                    WHERE i.id = :itemId
                    """);
            query.setParameter("name", item.getName())
                    .setParameter("itemId", item.getId())
                    .executeUpdate();
            session.getTransaction().commit();
            result = true;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    /**
     * Удаление записи
     *
     * @param itemId - номер
     * @return - успех?
     */
    @Override
    public boolean delete(int itemId) {
        boolean result = false;
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.createQuery("DELETE Item i WHERE i.id = :itemId")
                    .setParameter("itemId", itemId).executeUpdate();
            session.getTransaction().commit();
            result = true;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    /**
     * Взять все записи из базы
     *
     * @return - список всех элементов
     */
    @Override
    public List<Item> findAll() {
        Session session = sessionFactory.openSession();
        Query<Item> query = session.createQuery("FROM Item", Item.class);
        List<Item> itemList = query.list();
        session.close();

        return itemList;
    }

    /**
     * Найти по наименованию.
     *
     * @param name
     * @return
     */
    @Override
    public List<Item> findByName(String name) {
        Session session = sessionFactory.openSession();
        Query<Item> query = session.createQuery("FROM Item i WHERE i.name = :name", Item.class);
        List<Item> itemList = query.setParameter("name", name).list();

        session.close();

        return itemList;
    }

    /**
     * Найти по id
     *
     * @param itemId
     * @return
     */
    @Override
    public Item findById(int itemId) {
        Session session = sessionFactory.openSession();
        Query<Item> query = session.createQuery("FROM Item i WHERE i.id = :itemId", Item.class);
        Item item = query
                .setParameter("itemId", itemId)
                .getResultList()
                .stream().findFirst()
                .orElse(null);

        session.close();

        return item;
    }

    @Override
    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}