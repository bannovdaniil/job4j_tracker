package ru.job4j.queue;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * PriorityQueue.
 * <p>
 * Задача будет реализовать класс ScheduleTask, каркас ниже:
 * - addTask() просто добавляет переданную задачу в очередь;
 * - readTask() просто читает элемент из головы очереди без удаления;
 * - getTask() просто возвращает задачу из головы очереди с удалением.
 * Если очередь пуста – реализация не должна генерировать исключений.
 */
public class ScheduleTask {
    private final PriorityQueue<Task> queue;

    public ScheduleTask(Comparator<Task> comparator) {
        this.queue = new PriorityQueue<>(comparator);
    }

    public void addTask(Task task) {
        queue.offer(task);
    }

    public Task readTask() {
        return queue.element();
    }

    public Task getTask() {
        return queue.poll();
    }
}