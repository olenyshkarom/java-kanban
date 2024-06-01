package ru.practicum.task_tracker.task;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task{

    List<Integer> subtuskIds = new ArrayList<>();

    public List<Integer> getSubtuskIds() {
        return subtuskIds;
    }




    public Epic(String name, String description, Status status) {
        super(name, description, status);
    }

    public Epic(Integer id, String name, String description, Status status) {
        super(id, name, description, status);
    }

    public void addSubtuskIds(Integer subtuskId) {
        subtuskIds.add(subtuskId);
    }

    public void removeSubtuskIds(Integer subtuskId) {
        subtuskIds.remove(subtuskId);
    }



    //переопределить toString


}
