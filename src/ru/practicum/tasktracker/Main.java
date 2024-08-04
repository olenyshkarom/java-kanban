package ru.practicum.tasktracker;

import ru.practicum.tasktracker.manager.Managers;
import ru.practicum.tasktracker.manager.TaskManager;
import ru.practicum.tasktracker.task.Epic;
import ru.practicum.tasktracker.task.Status;
import ru.practicum.tasktracker.task.Subtask;
import ru.practicum.tasktracker.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {
        System.out.println("Поехали!");
        testTasks();
    }

    private static void testTasks() {
        TaskManager taskManager = Managers.getDefault();

        System.out.println("Тест 1: Пустой список");
        List<Task> tasks = taskManager.getTasks();
        System.out.println("Таски должны быть пустые: " + tasks.isEmpty());

        System.out.println("\n Тест 2: Создание таски");
        Task task1 = new Task("Имя Task1", "Описание Task1", Status.NEW);
        Task task1Created = taskManager.createTask(task1);
        System.out.println("Созданная таска должна содержать айди: " + (task1Created.getId() != null));
        System.out.println("Список тасок должен содержать нашу таску: " + (taskManager.getTasks()));

        System.out.println("\n Тест 2.1: Создание еще таски");
        Task task3 = new Task("Имя Task3", "ОписаниеTask3", Status.NEW);
        Task task3Created = taskManager.createTask(task3);
        System.out.println("Созданная таска должна содержать айди: " + (task3Created.getId() != null));
        System.out.println("Список тасок должен содержать нашу таску: " + (taskManager.getTasks()));

        System.out.println("\n Тест 3: Обновление таски");
        Task task2 = new Task(task1Created.getId(), "Имя новое", "Описание новое", Status.IN_PROGRESS);
        Task task2Updated = taskManager.updateTask(task2);
        System.out.println("Обновленная такска должна иметь обновленные поля: " + task2Updated);

        System.out.println("\n Тест 4: Удаление таски");
        boolean deleteRes = taskManager.deleteTask(task2Updated);
        System.out.println("Удаление должно пройти успешно: " + deleteRes);
        System.out.println("Список тасок должен быть пустой: " + taskManager.getTasks());


        System.out.println("\n Тест 5: Создание эпика");
        Epic epic1 = new Epic("Имя Эпика1", "Описание эпика1", Status.NEW);
        Epic epic1Created = taskManager.createEpic(epic1);
        System.out.println("Созданный эпик должен содержать айди: " + (epic1Created.getId() != null));
        System.out.println("Список тасок должен содержать наш эпик: " + (taskManager.getEpics()));

        System.out.println("\n Тест 6: Создание сабтаски");
        Subtask subtask1 = new Subtask(epic1.getId(), "Имя Сабтаски1", "Описание Сабтаски1", Status.NEW);
        Subtask subtask1Created = taskManager.createSubtask(subtask1);
        System.out.println("Созданный сабтаск должен содержать айди: " + (subtask1Created.getId() != null));
        System.out.println("Список тасок должен содержать наш сабтаск: " + (taskManager.getSubtasks()));
        System.out.println();
        System.out.println("Список тасок должен содержать наш эпик: " + (taskManager.getEpics()));


        System.out.println("\n Тест 7: Распечатать список тасок");
        List<Task> tasksList = taskManager.getTasks();
        for (Task task : tasksList) {
            System.out.println(task.toString());
        }

        System.out.println("\n Тест 8: Распечатать список эпиков");
        ArrayList<Epic> epicList = taskManager.getEpics();
        for (Epic epic : epicList) {
            System.out.println(epic.toString());
        }

        System.out.println("\n Тест 8: Распечатать список сабтасок у эпика epic1Created");
        List<Integer> subtuskIds = epic1Created.getSubtaskIds();

        List<Subtask> subList = taskManager.getSubtasks();
        for (Subtask subtask : subList) {
            for (Integer subEpicIds : subtuskIds) {
                if (Objects.equals(subtask.getId(), subEpicIds)) {
                    System.out.println(subtask.toString());
                }
            }
        }


        System.out.println("\n Тест 9: getTask()");
        System.out.println(taskManager.getTask(task3Created.getId()));

        System.out.println("\n Тест 10: Просмотр истории");
        System.out.println("История " + taskManager.getHistory());

        System.out.println("\n Тест 11: getTask() повторно");
        taskManager.getTask(task3Created.getId());
        taskManager.getTask(task3Created.getId());

        System.out.println("\n Тест 12: Просмотр истории");
        System.out.println("История " + taskManager.getHistory());

        System.out.println("\n Тест 13: getSubtask()");
        System.out.println(taskManager.getSubtask(subtask1Created.getId()));

        System.out.println("\n Тест 13: getEpic()");
        System.out.println(taskManager.getEpic(epic1Created.getId()));
        System.out.println("История " + taskManager.getHistory());


        System.out.println("\n Тест 6_1: Создайте две задачи");

        Task task6_1 = new Task("Имя 6_1", "Описание 6_1", Status.NEW);
        Task task6_2 = new Task("Имя 6_2", "Описание 6_2", Status.NEW);
        Task task6_1Created = taskManager.createTask(task6_1);
        Task task6_2Created = taskManager.createTask(task6_2);
        System.out.println("Созданная таска должна содержать айди: " + (task6_1Created.getId() != null));
        System.out.println("Созданная таска должна содержать айди: " + (task6_2Created.getId() != null));
        System.out.println("Список тасок должен содержать нашу таску: " + (taskManager.getTasks()));


        System.out.println("\n Тест 6_1: эпик с тремя подзадачами");
        Epic epic6_1 = new Epic("Имя Эпика 6_1", "Описание эпика 6_1", Status.NEW);
        Epic epic6_1Created = taskManager.createEpic(epic6_1);
        System.out.println("Созданный эпик должен содержать айди: " + (epic6_1Created.getId() != null));
        System.out.println("Список эпиков должен содержать наш эпик: " + (taskManager.getEpics()));
        System.out.println("История " + taskManager.getHistory());

        System.out.println("\n Тест 6_1: Создание сабтасок");
        Subtask subtask6_1 = new Subtask(epic6_1.getId(), "Имя Сабтаски 6_1", "Описание Сабтаски 6_1", Status.NEW);
        Subtask subtask6_2 = new Subtask(epic6_1.getId(), "Имя Сабтаски 6_2", "Описание Сабтаски 6_1", Status.NEW);
        Subtask subtask6_1Created = taskManager.createSubtask(subtask6_1);
        Subtask subtask6_2Created = taskManager.createSubtask(subtask6_2);
        System.out.println("Созданный сабтаск должен содержать айди: " + (subtask6_1Created.getId() != null));
        System.out.println("Созданный сабтаск должен содержать айди: " + (subtask6_2Created.getId() != null));
        System.out.println("Список тасок должен содержать наши сабтаски: " + (taskManager.getSubtasks()));
        System.out.println("История " + taskManager.getHistory());


        System.out.println("\n Тест 6_1: эпик без подзадач");
        Epic epic6_3 = new Epic("Имя Эпика 6_3", "Описание эпика 6_3", Status.NEW);
        Epic epic6_3Created = taskManager.createEpic(epic6_3);
        System.out.println("Созданный эпик должен содержать айди: " + (epic6_3Created.getId() != null));
        System.out.println("Список эпиков должен содержать наш эпик: " + (taskManager.getEpics()));
        System.out.println("История " + taskManager.getHistory());

        System.out.println("\n Тест 6_1: Запросите созданные задачи несколько раз в разном порядке");
        System.out.println("История " + taskManager.getHistory());

        taskManager.getTask(task6_2Created.getId());
        System.out.println("История " + taskManager.getHistory());


        taskManager.getTask(task6_1Created.getId());
        taskManager.getTask(task6_2Created.getId());
        taskManager.getEpic(epic6_3Created.getId());
        taskManager.getSubtask(subtask6_1Created.getId());
        taskManager.getTask(task6_1Created.getId());


        System.out.println("\n Тест 6_1: Просмотр истории");
        System.out.println("История " + taskManager.getHistory());

        System.out.println("\n Тест 6_4: Удалите задачу, которая есть в истории, и проверьте, что при печати она не будет выводиться");







    }


}
