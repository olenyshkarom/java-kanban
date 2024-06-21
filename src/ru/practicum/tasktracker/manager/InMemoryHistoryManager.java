package ru.practicum.tasktracker.manager;

import ru.practicum.tasktracker.task.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    private static List<Task> history = new ArrayList<>();

    @Override
    public void add(Task task) {
        //только существующие задачи
        if (history.size() >= 10 ) {
            history.remove(0);
        }
        history.add(task);

    }

    @Override
    public List<Task> getHistory() {
        return history;
    }
}
