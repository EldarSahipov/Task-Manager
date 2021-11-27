package models;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TaskTimer {
    public void start() {
        TimerTask timerTask = new Notification();
        Timer timer = new Timer();
        Date dateTimeNow = Date
                .from(LocalDateTime.now().toInstant(ZoneId
                .systemDefault()
                .getRules()
                .getOffset(LocalDateTime.now())));
        timer.schedule(timerTask, dateTimeNow, 1000);
    }
}