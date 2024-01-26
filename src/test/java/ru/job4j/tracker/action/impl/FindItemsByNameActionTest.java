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

class FindItemsByNameActionTest {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss");
    private Output output = new StubOutput();
    private Store tracker = new MemTracker();
    private Input input = Mockito.mock(Input.class);

    @DisplayName("Получить элемент по Name.")
    @Test
    void whenItemWasFindByNameSuccessfully() {
        LocalDateTime createTime = LocalDateTime.now();
        String expectedName = "Find My Item";
        tracker.add(new Item(1, expectedName, createTime));
        FindItemsByNameAction findItemsByNameAction = new FindItemsByNameAction(output);

        when(input.askStr(Mockito.any(String.class))).thenReturn(expectedName);

        findItemsByNameAction.execution(input, tracker);

        assertThat(output.toString()).isEqualTo(
                String.format("=== Find items by name ===%n"
                        + "Item{id=1, name='Find My Item', created=%s}%n", createTime.format(FORMATTER))
        );
    }

    @DisplayName("Find item by not exist Name, then Error")
    @Test
    void whenItemWasFindByNotExistsNameThenWrong() {
        String expectedName = "Find My Item";
        FindItemsByNameAction findItemsByNameAction = new FindItemsByNameAction(output);

        when(input.askStr(Mockito.any(String.class))).thenReturn(expectedName);

        findItemsByNameAction.execution(input, tracker);

        assertThat(output.toString()).isEqualTo(
                String.format("=== Find items by name ===%nЗаявки с именем: %s не найдены.%n", expectedName));
    }
}