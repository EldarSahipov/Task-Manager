package models;

import service.TaskService;

import java.util.Date;
import java.util.List;

public class UserInterface {
    private static UserInterface userInterface;
    private static TaskService taskService;

    private UserInterface() {}

    public static UserInterface getUserInterface() {
        if(userInterface == null) {
            userInterface = new UserInterface();
            taskService = new TaskService();
        }
        return userInterface;
    }

    public void addTask(Task task) {
        taskService.save(task);
    }

    public Task getTask(String name) {
        return taskService.get(name);
    }

    public List<Task> getAll() {
        return taskService.getAll();
    }

    public void deleteTask(Task task) {
        taskService.delete(task);
    }

    public void setTask(Task task) {
        taskService.update(task);
    }

    public List<Task> findByName(String name) {
        return taskService.finByName(name);
    }

    public List<Task> tasksCompleted() {
        return taskService.tasksCompleted();
    }

    public void deleteTasksCompleted() {
        taskService.deleteTasksCompleted();
    }

    public List<Task> tasksUncompleted () {
        return taskService.tasksUncompleted();
    }
}
