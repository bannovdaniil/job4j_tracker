package ru.job4j.tracker.exception;

public class HibernateRepositoryException extends RuntimeException {
    public HibernateRepositoryException(String message) {
        super(message);
    }
}
