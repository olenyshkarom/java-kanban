package ru.practicum.task_tracker.manager;

import ru.practicum.task_tracker.task.Epic;
import ru.practicum.task_tracker.task.Subtask;
import ru.practicum.task_tracker.task.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TaskManager {

    private final Map<Integer, Task> tasks = new HashMap<>();

    private int nextId;

    public List<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    public Task createTask(Task task) {
        task.setId(getNextId());
        tasks.put(task.getId(), task);
        return task;
    }

    public Task updateTask(Task task) {
        Integer taskId = task.getId();
        if (taskId == null || !tasks.containsKey(taskId)) {
            return null;
        }
        tasks.put(taskId, task);
        return task;
    }

    public boolean deleteTask(int taskId) {
        return tasks.remove(taskId) != null;
    }

    private int getNextId() {
        return nextId++;
    }

    public Subtask createSubtask(Epic epic, Subtask task) {         //Прошу помочь. Где можно сделать и как сделать обновление статуса эпика?
        task.setId(getNextId());                                    //как можно пройтись по задачам эпика?
        tasks.put(task.getId(), task);
        epic.addSubtuskIds(task.getId());
        System.out.println(epic.getSubtuskIds());
        System.out.println(updateEpicStatusFromSubtask(task.getId()));
        return task;
    }

    public Task updateSubtask(Task task) {
        Integer taskId = task.getId();
        if (taskId == null || !tasks.containsKey(taskId)) {
            return null;
        }
        tasks.put(taskId, task);
        updateEpicStatusFromSubtask(taskId);
        //проверить и заменить статус эпика if


        return task;
    }

    public Epic updateEpicStatusFromSubtask(Integer taskId){
        Subtask subtask = (Subtask) tasks.get(taskId);
        Epic epic = (Epic) tasks.get(subtask.getEpicId());
        return epic;
    }

    public boolean deleteSubtask(Integer taskId) {
        Subtask subtask = (Subtask) tasks.get(taskId);
        Epic epic = (Epic) tasks.get(subtask.getEpicId());
        epic.removeSubtuskIds(taskId);

        return tasks.remove(taskId) != null;
    }


}
