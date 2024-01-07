package ru.job4j.queue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * При парсинге некоторого текста что-то пошло не так и в итоге у нас получилось 2 очереди, представленные
 * последовательностью символов. Ваша задача завершить парсинг и получить окончательный вариант результирующей строки.
 * <p>
 * Ваша задача реализовать 2 приватных метода:
 * <p>
 * - метод getEvenElements должен взять из очереди evenElements только четные символы и все их соберет в
 * результирующую строку. Для реализации вам понадобится цикл fori, строку формируйте с помощью StringBuilder.
 * Эта очередь всегда имеет четное число элементов;
 * - метод getDescendingElements должен брать символы начиная с последнего символа и так пока не заберет их все.
 * Каждый символ мы добавляем в результирующую строку, формируем с помощью StringBuilder.
 */
public class ReconstructPhrase {

    private final Deque<Character> descendingElements;
    private final Deque<Character> evenElements;

    public ReconstructPhrase(Deque<Character> descendingElements, Deque<Character> evenElements) {
        this.descendingElements = descendingElements;
        this.evenElements = evenElements;
    }

    private String getEvenElements() {
        StringBuilder sb = new StringBuilder();
        Deque<Character> tempElement = new LinkedList<>(evenElements);

        for (int i = 0; i < evenElements.size(); i++) {
            if (i % 2 == 0) {
                sb.append(tempElement.poll());
            } else {
                tempElement.poll();
            }
        }

        return sb.toString();
    }

    private String getDescendingElements() {
        StringBuilder sb = new StringBuilder();
        Deque<Character> tempElement = new LinkedList<>(descendingElements);

        for (int i = 0; i < descendingElements.size(); i++) {
            sb.append(tempElement.pollLast());
        }

        return sb.toString();
    }

    public String getReconstructPhrase() {
        return getEvenElements() + getDescendingElements();
    }
}