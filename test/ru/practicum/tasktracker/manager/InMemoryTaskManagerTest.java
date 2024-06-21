package ru.practicum.tasktracker.manager;

import org.junit.jupiter.api.Test;
import ru.practicum.tasktracker.Managers;
import ru.practicum.tasktracker.task.Epic;
import ru.practicum.tasktracker.task.Status;
import ru.practicum.tasktracker.task.Subtask;
import ru.practicum.tasktracker.task.Task;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {

    private static InMemoryTaskManager taskManager = new InMemoryTaskManager();

    @Test
    public void equalsTasksWithEqualsIds() {

        //проверьте, что экземпляры класса Task равны друг другу, если равен их id;
        //TaskManager taskManager = Managers.getDefault();

        Task task1 = new Task("Имя", "Описание", Status.NEW);
        Task task1Created = taskManager.createTask(task1);

        Task task2 = new Task("ИмяTask2", "ОписаниеTask3", Status.NEW);     //не понятно как создать задачи с одним и тем же id
        Task task2Created = taskManager.createTask(task2);

        task1 = task2;

        assertEquals(task1.getId(), task2.getId());
        assertEquals(task1, task2, "Объекты равны");

    }

    @Test
    public void heirsTasksWithEqualsIds() {

        //проверьте, что наследники класса Task равны друг другу, если равен их id;

        Task subtask1 = new Subtask("Имя", "Описание", Status.NEW);
        Task subtask1Created = taskManager.createTask(subtask1);

        Task subtask2Created = taskManager.createTask(subtask1);

        assertEquals(subtask1.getId(), subtask1.getId());
        assertEquals(subtask1, subtask1, "Объекты равны");

    }

    @Test
    public void addEpicInEpic() {

        //проверьте, что объект Epic нельзя добавить в самого себя в виде подзадачи;
        //проверьте, что объект Subtask нельзя сделать своим же эпиком;

        Epic epic1 = new Epic("Имя Эпика", "Описание эпика", Status.NEW);
        Epic epic1Created = taskManager.createEpic(epic1);

        Epic epic2 = new Epic("Имя Эпика", "Описание эпика", Status.NEW);
        Epic epic2Created = taskManager.createEpic(epic2);

        //не поняла как можно добавить эпик в самого себя же, ведь нет такой функции в InMemoryTaskManager.
        // Но, возможно, можно в сам класс Epic руками в обход InMemoryTaskManager. Но он будет отдельно от InMemoryTaskManager
        //типа такого ниже, но как тогда это проверить?
        // так же и с сабтаской

        epic2.addSubtaskId(epic1Created.getId());

        System.out.println(epic2);

    }

    @Test
    public void addAnyTasksFinId() {

        //проверьте, что InMemoryTaskManager действительно добавляет задачи разного типа и может найти их по id;

        Task task1 = new Task("Имя", "Описание", Status.NEW);
        Task task1Created = taskManager.createTask(task1);

        Epic epic1 = new Epic("Имя Эпика", "Описание эпика", Status.NEW);
        Epic epic1Created = taskManager.createEpic(epic1);

        Subtask subtask1 = new Subtask(epic1.getId(), "Имя Сабтаски", "Описание Сабтаски", Status.NEW);
        Subtask subtask1Created = taskManager.createSubtask(subtask1);

        assertNotNull(taskManager.getTask(task1Created.getId()), "Задача найдена");
        assertNotNull(taskManager.getEpic(epic1Created.getId()), "Эпик найден");
        assertNotNull(taskManager.getSubtask(subtask1Created.getId()), "Подзадача найдена");

    }

    //проверьте, что задачи с заданным id и сгенерированным id не конфликтуют внутри менеджера;     -а как это можно сделать? подробнее бы описать

    @Test
    public void taskNotChangeAfterAddInManager() {
        //создайте тест, в котором проверяется неизменность задачи (по всем полям) при добавлении задачи в менеджер

        String name = "Имя";
        String description = "Описание";

        Task task1 = new Task(name, description, Status.NEW);
        Task task1Created = taskManager.createTask(task1);

        assertEquals(task1Created.getName(), name);
        assertEquals(task1Created.getDescription(), description);

    }



}