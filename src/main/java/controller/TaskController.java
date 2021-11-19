package controller;

import repo.TaskDao;
import models.Task;
import view.TaskView;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

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
        Task task = taskView.createTaskView();
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

    private int numberTask() {
        List<Task> taskList = taskDao.getAll();
        int numberTask = -1;
        if(!taskList.isEmpty()) {
            AtomicInteger i = new AtomicInteger(1);
            System.out.println("Выберите номер задачи");
            taskList.forEach((task -> System.out.println(i.getAndIncrement() + " " + task)));
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
                        .getAll().get(numberTask())
                )
        );
    }

    public void deleteTask() {
        taskDao.delete(taskDao
                .getAll().get(numberTask())
        );

    }

    public void deleteCompletedTask() {
        taskDao.deleteCompletedTask();
    }

    public void getExpiredTasks() {
        taskDao.getExpiredTasks().forEach(System.out::println);
    }

    public void getActiveTasks() {
        taskDao.getActiveTasks().forEach(System.out::println);
    }

    public void getCompletedTasks() {
        taskDao.getCompletedTasks().forEach(System.out::println);
    }

    public void exit() {
        System.exit(1);
    }


}
