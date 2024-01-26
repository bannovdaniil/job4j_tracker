package ru.job4j.tracker.action.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.job4j.tracker.io.Input;
import ru.job4j.tracker.io.Output;
import ru.job4j.tracker.io.impl.StubOutput;
import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.repository.Store;
import ru.job4j.tracker.repository.impl.MemTracker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

class FindItemByIdActionTest {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss");
    private Output output = new StubOutput();
    private Store tracker = new MemTracker();
    private Input input = Mockito.mock(Input.class);

    @DisplayName("Получить элемент по ID.")
    @Test
    void whenItemWasFindByIdSuccessfully() {
        LocalDateTime createTime = LocalDateTime.now();
        int expectedId = 1;
        tracker.add(new Item(expectedId, "Find My Item", createTime));
        FindItemByIdAction findItemByIdAction = new FindItemByIdAction(output);

        when(input.askInt(Mockito.any(String.class))).thenReturn(expectedId);

        findItemByIdAction.execution(input, tracker);

        assertThat(output.toString()).isEqualTo(
                String.format("=== Find item by id ===%n"
                        + "Item{id=%d, name='Find My Item', created=%s}%n", expectedId, createTime.format(FORMATTER))
        );
    }

    @DisplayName("Find item by not exist ID, then Error")
    @Test
    void whenItemWasFindByNotExistsIdThenWrong() {
        FindItemByIdAction findItemByIdAction = new FindItemByIdAction(output);

        int expectedId = 10;
        when(input.askInt(Mockito.any(String.class))).thenReturn(expectedId);

        findItemByIdAction.execution(input, tracker);

        assertThat(output.toString()).isEqualTo(
                String.format("=== Find item by id ===%nЗаявка с введенным id: %d не найдена.%n", expectedId));
    }
}