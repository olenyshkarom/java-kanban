package ru.practicum.task_tracker.task;

public class Subtask extends Task {

    private Integer epicId;

    public Integer getEpicId() {
        return epicId;
    }

    public Subtask(String name, String description, Status status) {
        super(name, description, status);
    }

    public Subtask(Integer epicId, String name, String description, Status status) {
        super(name, description, status);
        this.epicId = epicId;
    }

    public Subtask(Integer epicId, Integer id, String name, String description, Status status) {
        super(id, name, description, status);
        this.epicId = epicId;
    }

    //переопределить toString
}
