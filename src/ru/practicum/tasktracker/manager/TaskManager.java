package ru.practicum.tasktracker.manager;

import ru.practicum.tasktracker.task.Epic;
import ru.practicum.tasktracker.task.Subtask;
import ru.practicum.tasktracker.task.Task;

import java.util.ArrayList;
import java.util.List;

public interface TaskManager {
    ArrayList<Task> getTasks();

    Task createTask(Task task);

    Task updateTask(Task task);

    boolean deleteTask(Task task);

    ArrayList<Epic> getEpics();

    Epic createEpic(Epic epic);

    Epic updateEpic(Epic epic);

    boolean deleteEpic(Epic epic);

    ArrayList<Subtask> getSubtasks();

    Subtask createSubtask(Subtask subtask);

    Subtask updateSubtask(Subtask subtask);

    boolean deleteSubtask(Subtask subtask);

    Task getTask(int id);

    Subtask getSubtask(int id);

    Epic getEpic(int id);

    List<Task> getHistory();
}
