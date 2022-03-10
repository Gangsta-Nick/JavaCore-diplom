package ru.netology.javacore;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

public class TodosTests {
    Todos todo = new Todos();

    @BeforeEach
    public void initData() {
        todo.addTask("Спорт");
        todo.addTask("Уборка");
        todo.addTask("Магазин");
        todo.addTask("Прогулка");
    }

    @AfterEach
    public void clearData() {
        todo.clearTasksList();
    }

    @Test
    void addTaskTest() {
        List<String> expected = new ArrayList<>(Arrays.asList("Спорт", "Уборка", "Магазин", "Прогулка", "Сон"));
        todo.addTask("Сон");
        Assertions.assertEquals(expected, todo.getTasksList());
    }

    @Test
    void remoteTaskTest() {
        List<String> expected = new ArrayList<>(Arrays.asList("Спорт", "Уборка", "Магазин"));
        todo.removeTask("Прогулка");
        Assertions.assertEquals(expected, todo.getTasksList());
    }

    @Test
    void getAllTasksTest() {
        String expected = "Магазин Прогулка Спорт Уборка";
        String result = todo.getAllTasks();
        Assertions.assertEquals(expected, result);
    }
}
