package models;

import repo.TaskDao;
import java.time.ZoneId;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MyTimer {
    public void start() {
        TaskDao taskDao = new TaskDao();
        TimerTask timerTask = new Notification();
        Timer timer = new Timer();
        Task task = taskDao.searchTaskClosestInTime();
        Date dateTime = Date.from(task.time.toInstant(ZoneId
                .systemDefault()
                .getRules()
                .getOffset(task.time)));
        timer.schedule(timerTask, dateTime, 30000);

    }
}