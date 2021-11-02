package service;

import dao.TaskDao;
import models.Task;

import java.util.Date;
import java.util.List;

public class TaskService extends TaskDao{

    public Task get(String name) {
        return super.get(name);
    }

    public List<Task> getAll() {
        return super.getAll();
    }

    public void save(Task t) {
        super.save(t);
    }

    public void update(Task t) {
        super.update(t);
    }

    public void delete(Task t) {
        super.delete(t);
    }

    public List<Task> tasksCompleted() {
        List<Task> taskList = getAll();
        taskList.removeIf(task -> task.time.getTime() > new Date().getTime());
        return taskList;
    }

    public List<Task> tasksUncompleted () {
        List<Task> taskList = getAll();
        taskList.removeIf(task -> task.time.getTime() < new Date().getTime());
        return taskList;
    }

    public void deleteTasksCompleted() {
        List<Task> taskList = tasksCompleted();
        for (Task task: taskList) {
            delete(task);
        }
    }
}
