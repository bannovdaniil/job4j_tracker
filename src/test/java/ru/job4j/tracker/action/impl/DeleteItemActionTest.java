package ru.job4j.tracker.action.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.job4j.tracker.io.Input;
import ru.job4j.tracker.io.Output;
import ru.job4j.tracker.io.impl.StubOutput;
import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.repository.Store;
import ru.job4j.tracker.repository.impl.MemTracker;

import static org.mockito.Mockito.when;

class DeleteItemActionTest {
    private Output output = new StubOutput();
    private Store tracker = new MemTracker();
    private Input input = Mockito.mock(Input.class);

    @DisplayName("Удаление созданного элемента.")
    @Test
    void whenItemWasDeleteSuccessfully() {
        tracker.add(new Item("Delete item"));
        DeleteItemAction deleteItemAction = new DeleteItemAction(output);

        when(input.askInt(Mockito.any(String.class))).thenReturn(1);

        deleteItemAction.execution(input, tracker);
        Assertions.assertEquals(String.format("=== Delete item ===%nЗаявка удалена успешно.%n"),
                output.toString()
        );
    }

    @DisplayName("Delete not exist ID")
    @Test
    void whenItemWasDeleteNotExistsIdThenWrong() {
        DeleteItemAction deleteItemAction = new DeleteItemAction(output);

        when(input.askInt(Mockito.any(String.class))).thenReturn(1);

        deleteItemAction.execution(input, tracker);

        Assertions.assertEquals(String.format("=== Delete item ===%nОшибка удаления заявки.%n"),
                output.toString()
        );
    }

}