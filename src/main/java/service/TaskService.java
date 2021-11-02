package service;

import dao.TaskDao;
import models.Task;

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
}
