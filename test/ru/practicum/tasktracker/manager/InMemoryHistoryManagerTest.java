package ru.practicum.tasktracker.manager;

import org.junit.jupiter.api.Test;
import ru.practicum.tasktracker.task.Status;
import ru.practicum.tasktracker.task.Task;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {

    //убедитесь, что задачи, добавляемые в HistoryManager, сохраняют предыдущую версию задачи и её данных.
    private static InMemoryTaskManager taskManager = new InMemoryTaskManager();
    InMemoryHistoryManager history = new InMemoryHistoryManager();

    @Test
    void checkPreviousTask() {

        //убедитесь, что задачи, добавляемые в HistoryManager, сохраняют предыдущую версию задачи и её данных.
        Task task1 = new Task("Имя", "Описание", Status.NEW);
        //Task task1Created = taskManager.createTask(task1);

        history.add(task1);

        task1.setName("Имя новое");

        history.add(task1);

        System.out.println(history.getHistory());       //А каа можно сделать так, чтобы возвращалась предыдущая версия(



    }

}