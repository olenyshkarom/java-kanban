package ru.practicum.tasktracker.manager;

import org.junit.jupiter.api.Test;
import ru.practicum.tasktracker.task.Status;
import ru.practicum.tasktracker.task.Task;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {

    private static InMemoryTaskManager taskManager = new InMemoryTaskManager();
    //InMemoryHistoryManager history = new InMemoryHistoryManager();

    private final HistoryManager historyManager = Managers.getDefaultHistory();

    @Test
    void checkPreviousTask() {

        //убедитесь, что задачи, добавляемые в HistoryManager, сохраняют предыдущую версию задачи и её данных.
        Task task1 = new Task("Имя", "Описание", Status.NEW);
        Task task1Created = taskManager.createTask(task1);      //1) Добавить задачу
        Task getTask = taskManager.getTask(task1Created.getId());       //2) Получить задачу

        historyManager.add(getTask);     //3) Получить историю
        //List<Task> history =  historyManager.getHistory();
        System.out.println(historyManager.getHistory());

        getTask.setName("Имя новое");       //4) Обновить добавленную задачу
        taskManager.updateTask(getTask);


        //List<Task> history2 =  historyManager.getHistory();
        //Task task1FromHistory = history.get(0);
        //Task task2FromHistory = history.get(1);

        //assertEquals(task1FromHistory, task2FromHistory);     //6) Проверить что историю из п3 и п5 равны, что обновление задачи не изменило задачу в истории

        System.out.println(historyManager.getHistory());        //5) Получить историю

    }

}