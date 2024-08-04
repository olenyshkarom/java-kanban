package ru.practicum.tasktracker.manager;

import ru.practicum.tasktracker.task.Task;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {

    private Node head;
    private Node tail;
    private Map<Integer, Node> nodeMap = new HashMap<>();

    @Override
    public void add(Task task) {
        if (task == null) {
            return;
        }
        final int id = task.getId();
        remove(id);
        linkLast(task);     //тут же и в мапу добавляется/
        //Добавление лучше вынести сюда:
        //history.put(id, tail);
        //Ведь меирде linkLast отвечает за настройку ссылок, а тут как раз метод add который и должен отвечать за добавление

    }

    private void linkLast(Task task) {
        final Node node = new Node(task, tail, null);
        if (head == null) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;

        nodeMap.put(task.getId(), node);

    }

    @Override
    public List<Task> getHistory() {
        List<Task> result = new ArrayList<>();

        Node node = head;
        while (node != null) {
            result.add(node.value);     //Спасибо! а я чет намудрила, а оказывается можно намного проще и понятнее написать
            node = node.next;
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
        if (node.prev == null) {            //head      //tail
            if (node.next == null) {  // если только один узел, т.е. нет ни предыдущего, ни следующего элемента
                head = null;
                tail = null;
                return;
            }
            node.next.prev = null;
            head = node.next;
            // удаляемый узел - голова списка
        } else if (node.next == null) {
            node.prev.next = null;
            tail = node.prev;
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

        public Node(Task task, Node prev, Node next) {
            this.value = task;
            this.prev = prev;
            this.next = next;
        }

    }
}
