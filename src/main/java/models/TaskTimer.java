package models;

import repo.TaskDao;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TaskTimer {
    List<Task> task = new TaskDao().searchTasksClosestInTime();

    public void start() {
        TimerTask timerTask = new Notification();
        Timer timer = new Timer();
        if(task != null) {
            Date dateTime = Date
                    .from(task.get(0).time.toInstant(ZoneId // обязательно заменить get(0)
                    .systemDefault()
                    .getRules()
                    .getOffset(task.get(0).time)));
            timer.schedule(timerTask, dateTime, 1000);
        }
    }
}