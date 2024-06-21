package ru.practicum.tasktracker.manager;

import ru.practicum.tasktracker.task.Epic;
import ru.practicum.tasktracker.task.Status;
import ru.practicum.tasktracker.task.Subtask;
import ru.practicum.tasktracker.task.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class InMemoryTaskManager implements TaskManager {

    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();
    private final HashMap<Integer, Subtask> subtasks = new HashMap<>();


    private int nextId;

    @Override
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public ArrayList<Epic> getEpics() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public ArrayList<Subtask> getSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    @Override
    public Task createTask(Task task) {
        task.setId(getNextId());
        tasks.put(task.getId(), task);
        return task;
    }

    @Override
    public Epic createEpic(Epic epic) {
        epic.setId(getNextId());
        epics.put(epic.getId(), epic);

        return epic;
    }

    @Override
    public Subtask createSubtask(Subtask subtask) {
        int epicId = subtask.getEpicId();
        Epic epic = epics.get(epicId);
        if (epic == null) {
            return null;
        }
        int id = getNextId();
        subtask.setId(id);

        subtask.setEpicId(epic.getId());
        subtasks.put(id, subtask);
        epic.addSubtaskId(id);

        updateEpicStatus(epic);

        return subtask;

    }

    @Override
    public Task updateTask(Task task) {
        Integer taskId = task.getId();
        if (taskId == null || !tasks.containsKey(taskId)) {
            return null;
        }
        tasks.put(taskId, task);
        return task;
    }

    @Override
    public Subtask updateSubtask(Subtask subtask) {
        int id = subtask.getId();
        int epicId = subtask.getEpicId();
        Subtask savedSubtask = subtasks.get(id);
        if (savedSubtask == null) {
            return null;
        }
        Epic epic = epics.get(epicId);
        if (epic == null) {
            return null;
        }
        subtasks.put(id, subtask);
        updateEpicStatus(epic);
        return subtask;
    }

    @Override
    public Epic updateEpic(Epic epic) {
        Epic savedEpic = epics.get(epic.getId());
        if (savedEpic == null) {
            return null;
        }
        savedEpic.setName(epic.getName());
        savedEpic.setDescription(epic.getDescription());

        return epic;
    }

    @Override
    public boolean deleteTask(Task task) {
        return tasks.remove(task.getId()) != null;
    }

    @Override
    public boolean deleteSubtask(Subtask subtask) {
        return subtasks.remove(subtask.getId()) != null;
    }

    @Override
    public boolean deleteEpic(Epic epic) {
        if (epics.get(epic.getId()) == null) {
            return false;
        }
        ArrayList<Integer> sab = epic.getSubtuskIds();
        for (Integer sabId : sab) {
            if (subtasks.get(sabId) != null) {
                subtasks.remove(sabId);
            }
        }
        epics.remove(epic.getId());
        return true;
    }


    private void updateEpicStatus(Epic epic) {
        ArrayList<Integer> sabtasksIds = epic.getSubtuskIds();
        if (sabtasksIds.isEmpty()) {
            return;
        }
        Status statusInProgress = null;     // не знала как лучше посчитать варианты, чтобы охватить все статусы
        Status statusNew = null;
        Status statusDone = null;
        for (Integer sabId : sabtasksIds) {
            Subtask sabtask = subtasks.get(sabId);
            if (sabtask.getStatus() == Status.IN_PROGRESS) {
                statusInProgress = Status.IN_PROGRESS;
            } else if (sabtask.getStatus() == Status.NEW) {
                statusNew = Status.NEW;
            } else if (sabtask.getStatus() == Status.DONE) {
                statusDone = Status.DONE;
            }
        }

        if ((statusInProgress != null) || (statusNew != null && statusDone != null)) {
            epic.setStatus(Status.IN_PROGRESS);
        } else if (statusNew != null && statusDone == null) {
            epic.setStatus(Status.NEW);
        } else if (statusNew == null && statusDone != null) {
            epic.setStatus(Status.DONE);
        }
        // если есть хоть одна сабтаска со статусом IN_PROGRESS, то эпик IN_PROGRESS
        // если все новые, то эпик новый
        // если все Done, то эпик Done
        // если есть и новые, и Done, то эпик в процессе

    }

    private int getNextId() {
        return ++nextId;
    }

    private static InMemoryHistoryManager history = new InMemoryHistoryManager();

    public List<Task> getHistory() {
        return history.getHistory();
    }

    public Task getTask(int id) {
        history.add(tasks.get(id));
        return tasks.get(id);
    }

    public Subtask getSubtask(int id) {
        history.add(subtasks.get(id));
        return subtasks.get(id);
    }

    public Epic getEpic(int id) {
        history.add(epics.get(id));
        return epics.get(id);
    }

}
