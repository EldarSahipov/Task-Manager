package controller;

import repo.TaskDao;
import models.Task;
import view.TaskView;
import java.util.*;

public class TaskController {

    private final TaskView taskView;
    private final TaskDao taskDao;

    public TaskController(TaskView taskView, TaskDao taskDao) {
        this.taskView = taskView;
        this.taskDao = taskDao;
    }

    public void getTasksAll() {
        List<Task> taskList = taskDao.getAll();
        taskView.printTaskAll(taskList);
    }

    public void addTask() {
        Task task = taskView.createNewTask();
        taskDao.save(task);
        System.out.println("Задача добавлена.");
    }

    public void findTasksByName() {
        String nameTask = taskView.getNameTask();
        List<Task> tasksList = taskDao.getTaskByName(nameTask);
        if(!tasksList.isEmpty()) {
            taskView.printTasks(tasksList);
        } else {
            System.out.println("Такой задачи не найдено");
        }
    }

    private int selectTaskNumber() {
        List<Task> taskList = taskDao.getAll();
        int numberTask = -1;
        if(!taskList.isEmpty()) {
            taskList.forEach((task -> System.out.println((taskList.indexOf(task) + 1) + " " + task)));
            System.out.println("Выберите номер задачи");
            Scanner scanner = new Scanner(System.in);
            numberTask = scanner.nextInt();
            if(numberTask > 0 || numberTask < taskList.size()) {
                return numberTask - 1;
            } else {
                System.out.println("С таким номером нет задачи в списке");
                return -1;
            }
        } else {
            System.out.println("Задач нет");
        }
        return numberTask - 1;
    }

    public void changeTask() {
        taskDao.update(taskView
                .setTaskView(taskDao
                        .getAll().get(selectTaskNumber())
                )
        );
    }

    public void deleteTask() {
        taskDao.delete(taskDao
                .getAll().get(selectTaskNumber()));
        System.out.println("Задача удалена");
    }

    public void deleteCompletedTask() {
        taskDao.deleteCompletedTask();
        System.out.println("Завершенные задачи удалены");
    }

    public void getExpiredTasks() {
        taskDao.getExpiredTasks()
                .forEach((task -> System.out.println(
                        (taskDao.getExpiredTasks().indexOf(task) + 1) + " " + task)));
    }

    public void getActiveTasks() {
        taskDao.getActiveTasks()
                .forEach((task -> System.out.println(
                        (taskDao.getActiveTasks().indexOf(task) + 1) + " " + task)));
    }

    public void getCompletedTasks() {
        taskDao.getCompletedTasks()
                .forEach((task -> System.out.println(
                        (taskDao.getCompletedTasks().indexOf(task) + 1) + " " + task)));
    }

    public void exit() {
        System.exit(1);
    }

    public void getMenu() {
        System.out.println("\nПланировщик задач");
        System.out.println("1. Добавить задачу");
        System.out.println("2. Поиск задачи");
        System.out.println("3. Вывести все задачи");
        System.out.println("4. Изменить задачу");
        System.out.println("5. Удалить задачу");
        System.out.println("6. Показать завершенные задачи");
        System.out.println("7. Удалить завершенные задачи");
        System.out.println("8. Показать просроченные задачи");
        System.out.println("9. Показать непросроченные задачи");
        System.out.println("10. Завершить работу");
    }


}
