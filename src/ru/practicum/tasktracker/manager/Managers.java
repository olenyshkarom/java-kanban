package ru.practicum.tasktracker.manager;

import ru.practicum.tasktracker.manager.HistoryManager;
import ru.practicum.tasktracker.manager.InMemoryHistoryManager;
import ru.practicum.tasktracker.manager.InMemoryTaskManager;
import ru.practicum.tasktracker.manager.TaskManager;

public class Managers {


    private Managers() {
    }

    ;

    public static TaskManager getDefault() {
        return new InMemoryTaskManager();
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }

    ;
}
