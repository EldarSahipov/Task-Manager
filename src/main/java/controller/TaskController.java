package controller;

import models.JsonParser;
import org.json.simple.JSONObject;
import repo.TaskDao;
import models.Task;
import view.InputTaskView;
import view.OutputTaskView;
import java.util.*;

public class TaskController {
    private final InputTaskView inputTaskView;
    private final OutputTaskView outputTaskView;
    private final TaskDao taskDao;
    private final JSONObject jsonObject = JsonParser.getJsonObject();

    public TaskController(InputTaskView inputTaskView, OutputTaskView outputTaskView, TaskDao taskDao) {
        this.taskDao = taskDao;
        this.inputTaskView = inputTaskView;
        this.outputTaskView = outputTaskView;
    }

    public void getTasksAll() {
        List<Task> taskList = taskDao.getAll();
        outputTaskView.printTaskAll(taskList);
    }

    public void addTask() {
        Task task = inputTaskView.createNewTask();
        taskDao.save(task);
        assert jsonObject != null;
        System.out.println(jsonObject.get("taskAdded"));
    }

    public void findTasksByName() {
        String nameTask = outputTaskView.getNameTask();
        List<Task> tasksList = taskDao.getTaskByName(nameTask);
        if(!tasksList.isEmpty()) {
            outputTaskView.printTasks(tasksList);
        } else {
            assert jsonObject != null;
            System.out.println(jsonObject.get("noTasksFound"));
        }
    }

    private int selectTaskNumber() {
        List<Task> taskList = taskDao.getAll();
        int numberTask = -1;
        assert jsonObject != null;
        if(!taskList.isEmpty()) {
            taskList.forEach((task -> System.out.println((taskList.indexOf(task) + 1) + " " + task)));
            System.out.println(jsonObject.get("selectTaskNumber"));
            Scanner scanner = new Scanner(System.in);
            numberTask = scanner.nextInt();
            if(numberTask > 0 || numberTask < taskList.size()) {
                return numberTask - 1;
            } else {
                System.out.println(jsonObject.get("noTaskInTheList"));
                return -1;
            }
        } else {
            System.out.println(jsonObject.get("noTasks"));
        }
        return numberTask - 1;
    }

    public void changeTask() {
        taskDao.update(inputTaskView
                .setTaskView(taskDao
                        .getAll().get(selectTaskNumber())
                )
        );
    }

    public void deleteTask() {
        taskDao.delete(taskDao
                .getAll().get(selectTaskNumber()));
        assert jsonObject != null;
        System.out.println(jsonObject.get("taskDeleted"));
    }

    public void deleteCompletedTask() {
        taskDao.deleteCompletedTask();
        assert jsonObject != null;
        System.out.println(jsonObject.get("completedTasksDeleted"));
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
        outputTaskView.printMenu();
    }


}
