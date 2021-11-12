package models;

import repo.TaskDao;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MyTimer {
    public void timer() {
        TaskDao taskDao = new TaskDao();
        TimerTask timerTask = new Notification();
        Timer timer = new Timer();
        Task task = taskDao.searchTaskClosestInTime();
        Calendar time = Calendar.getInstance();
        time.set(Calendar.YEAR, task.getTime().getYear());
        time.set(Calendar.MONTH, (task.getTime().getMonthValue()-1));
        time.set(Calendar.DAY_OF_MONTH, task.getTime().getDayOfMonth());
        time.set(Calendar.HOUR_OF_DAY, task.getTime().getHour());
        time.set(Calendar.MINUTE, task.getTime().getMinute());
        time.set(Calendar.SECOND, task.getTime().getSecond());
        time.set(Calendar.MILLISECOND, 0);
        timer.schedule(timerTask, time.getTime());
    }
}
