package ru.practicum.tasktracker;

import ru.practicum.tasktracker.manager.InMemoryTaskManager;
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
        Task task1 = new Task("Имя", "Описание", Status.NEW);
        Task task1Created = taskManager.createTask(task1);
        System.out.println("Созданная таска должна содержать айди: " + (task1Created.getId() != null));
        System.out.println("Список тасок должен содержать нашу таску: " + (taskManager.getTasks()));

        System.out.println("\n Тест 2.1: Создание еще таски");
        Task task3 = new Task("ИмяTask3", "ОписаниеTask3", Status.NEW);
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
        Epic epic1 = new Epic("Имя Эпика", "Описание эпика", Status.NEW);
        Epic epic1Created = taskManager.createEpic(epic1);
        System.out.println("Созданный эпик должен содержать айди: " + (epic1Created.getId() != null));
        System.out.println("Список тасок должен содержать наш эпик: " + (taskManager.getEpics()));

        System.out.println("\n Тест 6: Создание сабтаски");
        Subtask subtask1 = new Subtask(epic1.getId(), "Имя Сабтаски", "Описание Сабтаски", Status.NEW);
        Subtask subtask1Created = taskManager.createSubtask(subtask1);
        System.out.println("Созданный сабтаск должен содержать айди: " + (subtask1Created.getId() != null));
        System.out.println("Список тасок должен содержать наш сабтаск: " + (taskManager.getSubtasks()));
        System.out.println();
        System.out.println("Список тасок должен содержать наш эпик: " + (taskManager.getEpics()));


        System.out.println("\n Тест 7: Распечатать список тасок");
        ArrayList<Task> tasksList = taskManager.getTasks();
        for (Task task : tasksList) {
            System.out.println(task.toString());
        }

        System.out.println("\n Тест 8: Распечатать список эпиков");
        ArrayList<Epic> epicList = taskManager.getEpics();
        for (Epic epic : epicList) {
            System.out.println(epic.toString());
        }

        System.out.println("\n Тест 8: Распечатать список сабтасок у эпика epic1Created");
        ArrayList<Integer> subtuskIds = epic1Created.getSubtuskIds();

        ArrayList<Subtask> subList = taskManager.getSubtasks();
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
        System.out.println(taskManager.getHistory());

        System.out.println("\n Тест 11: getTask() повторно");
        taskManager.getTask(task3Created.getId());
        taskManager.getTask(task3Created.getId());
        taskManager.getTask(task3Created.getId());
        taskManager.getTask(task3Created.getId());
        taskManager.getTask(task3Created.getId());
        taskManager.getTask(task3Created.getId());
        taskManager.getTask(task3Created.getId());
        taskManager.getTask(task3Created.getId());
        taskManager.getTask(task3Created.getId());
        taskManager.getTask(task3Created.getId());
        taskManager.getTask(task3Created.getId());
        taskManager.getTask(task3Created.getId());
        taskManager.getTask(task3Created.getId());


        System.out.println("\n Тест 12: Просмотр истории");
        System.out.println(taskManager.getHistory());

        System.out.println("\n Тест 13: getSubtask()");
        System.out.println(taskManager.getSubtask(subtask1Created.getId()));

        System.out.println("\n Тест 13: getEpic()");
        System.out.println(taskManager.getEpic(epic1Created.getId()));

        System.out.println("\n Тест 14: Просмотр истории с сабтаской");
        System.out.println(taskManager.getHistory());

    }


}
