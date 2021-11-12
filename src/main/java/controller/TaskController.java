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

    public void changeTask() {
        List<Task> taskList = taskDao.getAll();

        if(!taskList.isEmpty()) {
            System.out.println("Выберите номер задачи");
            for(int i = 0; i < taskList.size(); i++) {
                System.out.println((i + 1) + ")" + taskList.get(i));
            }
            Scanner scanner = new Scanner(System.in);
            Task task = taskView.setTaskView(taskList.get(scanner.nextInt() - 1));
            taskDao.update(task);
            } else {
            System.out.println("Задач нет");
        }
    }

    public void deleteTask() {
        List<Task> taskList = taskDao.getAll();

        if(!taskList.isEmpty()) {
            System.out.println("Выберите номер задачи");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println((i + 1) + ")" + taskList.get(i));
            }
            Scanner scanner = new Scanner(System.in);
            taskDao.delete(taskList.get(scanner.nextInt() - 1));
        } else {
            System.out.println("Задач нет");
        }
    }

    public void deleteCompletedTask() {

    }


}
