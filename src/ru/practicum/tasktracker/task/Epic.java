package ru.practicum.tasktracker.task;

import java.util.ArrayList;
import java.util.Objects;

public class Epic extends Task {

    private ArrayList<Integer> subtuskIds = new ArrayList<>();

    public ArrayList<Integer> getSubtuskIds() {
        return subtuskIds;
    }


    public Epic(String name, String description, Status status) {
        super(name, description, status);
    }

    public Epic(Integer id, String name, String description, Status status) {
        super(id, name, description, status);
    }

    public void addSubtaskId(Integer subtaskId) {
        subtuskIds.add(subtaskId);
    }

    public void removeSubtuskIds(Integer subtuskId) {
        subtuskIds.remove(subtuskId);
    }


    @Override
    public String toString() {
        return "Epic{" +
                ", name='" + super.getName() + '\'' +
                ", description='" + super.getDescription() + '\'' +
                ", status='" + super.getStatus() + '\'' +
                "subtuskIds=" + subtuskIds +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Epic epic = (Epic) o;
        return Objects.equals(subtuskIds, epic.subtuskIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), subtuskIds);
    }
}
