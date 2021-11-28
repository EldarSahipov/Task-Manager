package service;

import models.Status;
import models.Task;
import repo.TaskDao;

import java.time.LocalDateTime;
import static models.Constant.*;

public class TaskService {
    private final TaskDao taskDao = new TaskDao();

    public void completeTask(Task task) {
        task.status = Status.COMPLETED;
        taskDao.update(task);
    }

    public void changeDateTimeTask(Task task, LocalDateTime dateTime) {
        if(task.time.isBefore(LocalDateTime.parse(LocalDateTime.now().format(FORMATTER), FORMATTER))) {
            task.status = Status.EXPIRED;
        } else {
            task.status = Status.ACTIVE;
        }
        task.time = dateTime;
        taskDao.update(task);
    }

    public void expiredTask(Task task) {
        task.status = Status.EXPIRED;
        taskDao.update(task);
    }

}