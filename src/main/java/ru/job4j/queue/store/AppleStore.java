package ru.job4j.queue.store;

import ru.job4j.queue.store.model.Customer;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * класс, описывающий магазин:
 * <p>
 * - поле Queue хранит в себе очередь клиентов, которые пришли в магазин;
 * - поле count хранит в себе количество доступного к покупке товара;
 * - метод getLastHappyCustomer() должен вернуть имя последнего счастливого обладателя желаемого товара;
 * - метод getFirstUpsetCustomer() должен вернуть имя первого клиента, которому сегодня не повезло и ему не хватило товара;
 * - очередь не может быть пустой;
 */
public class AppleStore {
    private final Queue<Customer> queue;
    private final int count;

    public AppleStore(Queue<Customer> queue, int count) {
        this.queue = queue;
        this.count = count;
    }

    /**
     * каждый клиент может оказаться потенциально последним.
     * 1. вариан, это убрать из очереди всех кроме одного.
     * 2. запоминать всех убираемых.
     * - реализован 1 вариант.
     *
     * @throws NoSuchElementException if this element is empty
     */
    public String getLastHappyCustomer() {
        Queue<Customer> tempQueue = new LinkedList<>(queue);
        for (int i = 0; i < count - 1; i++) {
            tempQueue.poll();
        }
        return tempQueue.element().name();
    }

    /**
     * убираем из очереди всех клиентов тот кто нам нужен находится у нас в голове очереди.
     *
     * @throws NoSuchElementException if this element is empty
     */
    public String getFirstUpsetCustomer() {
        Queue<Customer> tempQueue = new LinkedList<>(queue);
        for (int i = 0; i < count; i++) {
            tempQueue.poll();
        }
        return tempQueue.element().name();
    }
}
