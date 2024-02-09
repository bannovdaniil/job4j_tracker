package ru.job4j.queue;

/**
 * Модель данных.
 *
 * @param position    - позиция.
 * @param description - описание.
 * @param urgency     - некая величина для приоритета в очереди.
 */
public record Task(Position position,
                   String description,
                   int urgency) {
}
