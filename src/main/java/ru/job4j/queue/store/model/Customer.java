package ru.job4j.queue.store.model;

/**
 * модель данных, которая описывает клиента магазина:
 *
 * @param name   - имя
 * @param amount - количество денег
 */
public record Customer(String name, int amount) {
}