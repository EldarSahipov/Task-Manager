package service;

import models.Status;
import models.Task;
import repo.TaskDao;

import java.time.LocalDateTime;

public class TaskService{
    private TaskDao taskDao;

    public void completeTask(Task task) {
        task.status = Status.COMPLETED;
        taskDao.update(task);
    }

    public void postponeTask(Task task, LocalDateTime dateTime) {
        task.time = dateTime;
        taskDao.update(task);
    }

    public void deleteAndCompleteTask(Task task) {
        taskDao.delete(task);
    }


    public void expiredTask(Task task) {
        task.status = Status.EXPIRED;
        taskDao.update(task);
    }

}
