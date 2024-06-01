import ru.practicum.task_tracker.manager.TaskManager;
import ru.practicum.task_tracker.task.Epic;
import ru.practicum.task_tracker.task.Status;
import ru.practicum.task_tracker.task.Subtask;
import ru.practicum.task_tracker.task.Task;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Поехали!");
        testTasks();


    }

    private static void testTasks() {
        TaskManager taskManager = new TaskManager();

        System.out.println("Тест 1: Пустой список");
        List<Task> tasks = taskManager.getTasks();
        System.out.println("Таски должны быть пустые: " + tasks.isEmpty());
        System.out.println();

        System.out.println("Тест 2: Создание таски");
        Task task1 = new Task("Имя", "Описание", Status.NEW);
        Task task1Created = taskManager.createTask(task1);
        System.out.println("Созданная таска должна содержать айди: " + (task1Created.getId() != null));
        System.out.println("Список тасок должен содержать нашу таску: " + (taskManager.getTasks()));
        System.out.println();

        System.out.println("Тест 2.1: Создание еще таски");
        Task task3 = new Task("ИмяTask3", "ОписаниеTask3", Status.NEW);
        Task task3Created = taskManager.createTask(task3);
        System.out.println("Созданная таска должна содержать айди: " + (task3Created.getId() != null));
        System.out.println("Список тасок должен содержать нашу таску: " + (taskManager.getTasks()));
        System.out.println();

        System.out.println("Тест 3: Обновление таски");
        Task task2 = new Task(task1Created.getId(), "Имя новое", "Описание новое", Status.IN_PROGRESS);
        Task task2Updated = taskManager.updateTask(task2);
        System.out.println("Обновленная такска должна иметь обновленные поля: " + task2Updated);
        System.out.println();

        System.out.println("Тест 4: Удаление таски");
        boolean deleteRes = taskManager.deleteTask(task2Updated.getId());
        System.out.println("Удаление должно пройти успешно: " + deleteRes);
        System.out.println("Список тасок должен быть пустой: " + taskManager.getTasks());
        System.out.println();


        System.out.println("Тест 5: Создание эпика");
        Epic epic1 = new Epic("Имя Эпика", "Описание эпика", Status.NEW);
        Task epic1Created = taskManager.createTask(epic1);
        System.out.println("Созданный эпик должен содержать айди: " + (epic1Created.getId() != null));
        System.out.println("Список тасок должен содержать наш эпик: " + (taskManager.getTasks()));
        System.out.println();

        System.out.println("Тест 6: Создание сабтаски");
        Subtask subtask1 = new Subtask("Имя Сабтаски", "Описание Сабтаски", Status.NEW);
        Task subtask1Created = taskManager.createSubtask(epic1, subtask1);
        System.out.println("Созданный сабтаск должен содержать айди: " + (subtask1Created.getId() != null));
        System.out.println("Список тасок должен содержать наш сабтаск: " + (taskManager.getTasks()));
        System.out.println();




        //распечатать списки задач конкретного эпика
        //распечатать только эпики
        //распечатать только сабтаски
        //удалить подзадачу
        //удалить эпик


    }


}
