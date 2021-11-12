package models;

import org.apache.log4j.Logger;
import repo.TaskDao;
import java.awt.*;
import java.util.TimerTask;

public class Notification extends TimerTask {
    private static final Logger log = Logger.getLogger(Notification.class);
    private final TaskDao taskDao = new TaskDao();

    public void displayTray() throws AWTException {
        Task task = taskDao.searchTaskClosestInTime();

        SystemTray tray = SystemTray.getSystemTray();
        Image image = Toolkit.getDefaultToolkit().createImage("icon.jpg");
        TrayIcon trayIcon = new TrayIcon(image, "Tray");
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip(task.name);
        tray.add(trayIcon);

        trayIcon.displayMessage(task.name, task.toString(), TrayIcon.MessageType.INFO);

        try {
            Thread.sleep(60000);

        } catch (InterruptedException e) {
            log.error(e);
        }

        MyTimer myTimer = new MyTimer();
        myTimer.timer();
    }

    @Override
    public void run() {
        try {
            displayTray();
        } catch (AWTException e) {
            log.error(e);
        }
    }
}
