package service;

import dao.TaskDao;
import models.Task;

import java.util.List;

public class TaskService {
    private TaskDao taskDao = new TaskDao();

    public Task get(String name) {
        return taskDao.get(name);
    }

    public List<Task> getAll() {
        return taskDao.getAll();
    }

    public void save(Task t) {
        taskDao.save(t);
    }

    public void update(Task t) {
        taskDao.update(t);
    }

    public void delete(Task t) {
        taskDao.delete(t);
    }
}
