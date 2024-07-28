package ru.practicum.tasktracker.manager;

import ru.practicum.tasktracker.task.Task;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {

    private Node first;
    private Node last;
    private Map<Integer, Node> nodeMap = new HashMap<>();

    //private List<Task> history = new ArrayList<>();
    public static final int MAX_SIZE = 10;

    @Override
    public void add(Task task) {
        if (task == null) {
            return;
        }
        linkLast(task);

        // remove
        // linkLast
        // nodeMap.put

    }

    private void linkLast(Task task) {
        if (task == null) {
            return;
        }

        Node node = new Node();
        node.value = task;

        if (nodeMap.isEmpty()) {
            first = node;
            last = node;
            nodeMap.put(task.getId(), node);
            return;
        }

        Node oldNode = nodeMap.get(task.getId());

        if (oldNode != null && oldNode.next != null) {      //если задача ранее была и она не в конце, то ставим ее в конец
            oldNode.prev.next = oldNode.next;               //если задача была и она в конце, то ничего не меняем
            Node lastNode = nodeMap.get(last.value.getId());
            lastNode.next = oldNode;
            oldNode.next = null;
            last = oldNode;

        } else if (oldNode == null) {                  //если задачи не было, то добавляем в мапу и делаем ее last
            nodeMap.put(task.getId(), node);
            Node lastNode = nodeMap.get(last.value.getId());
            lastNode.next = node;
            last = node;
        }

    }

    @Override
    public List<Task> getHistory() {
        List<Task> result = new ArrayList<>();

        if (nodeMap.isEmpty()) {
            return result;
        }

        Node nextNode;
        Node node = nodeMap.get(first.value.getId());
        result.add(node.value);

        int nextVal = 1;
        while(nextVal == 1) {
            nextNode = node.next;
            if (nextNode == null) {
                nextVal = 0;
                break;
            }
            result.add(nextNode.value);
            node = nextNode;

        }
        return result;

    }

    @Override
    public void remove(int id) {
        Node node = nodeMap.remove(id);
        if (node == null) {
            return;
        }

        removeNode(node);
    }

    private void removeNode(Node node) {
        if (node.prev == null) {
            if (node.next == null) {  // если только один узел, т.е. нет ни предыдущего, ни следующего элемента
                return;
            }
            node.next.prev = null;
            // удаляемый узел - голова списка
        } else if (node.next == null) {
            node.prev.next = null;
            // удаляемый узел - хвост списка
        } else {
            // удаляемый узел - середина списка
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

    }

    private static class Node {
        Node prev;
        Node next;
        Task value;
    }
}
