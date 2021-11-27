package service;

import models.Status;
import models.Task;
import repo.TaskDao;

import java.time.LocalDateTime;

public class TaskService{
    private final TaskDao taskDao;

    public TaskService(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public void completeTask(Task task) {
        task.status = Status.COMPLETED;
        taskDao.update(task);
    }

    public void changeDateTimeTask(Task task, LocalDateTime dateTime) {
        if(task.time.isBefore(LocalDateTime.now())) {
            task.status = Status.EXPIRED;
        }
        task.time = dateTime;
        taskDao.update(task);
    }

    public void expiredTask(Task task) {
        task.status = Status.EXPIRED;
        taskDao.update(task);
    }

}