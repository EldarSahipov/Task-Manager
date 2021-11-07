package Controller;

import dao.TaskDao;
import models.Notification;
import models.Task;
import view.TaskView;

import java.util.List;
import java.util.Scanner;

public class TaskController {

    private final TaskView taskView;
    private final TaskDao taskDao;
    private final Notification notification;

    public TaskController(TaskView taskView, TaskDao taskDao, Notification notification) {
        this.taskView = taskView;
        this.taskDao = taskDao;
        this.notification = notification;
    }

    public void getTasksAll() {
        List<Task> taskList = taskDao.getAll();
        taskView.printTaskAll(taskList);
    }

    public void addTask() {
        Task task = taskView.getTaskView();
        taskDao.save(task);
        System.out.println("Задача добавлена.");
    }

    public void findTaskByName() {
        String nameTask = taskView.getNameTask();
        List<Task> taskList = taskDao.getTaskByName(nameTask);
        if(!taskList.isEmpty()) {
            taskView.printTask(taskList);
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
