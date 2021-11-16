package models;

import org.apache.log4j.Logger;
import repo.TaskDao;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimerTask;

public class Notification extends TimerTask {
    private static final Logger log = Logger.getLogger(Notification.class);
    private final TaskDao taskDao = new TaskDao();

    public void displayTray() throws AWTException {
        Task task = taskDao.searchTaskClosestInTime();

        SystemTray tray = SystemTray.getSystemTray();
        Image image = Toolkit.getDefaultToolkit().createImage("icon.jpg");

        final PopupMenu menu = new PopupMenu();

        final MenuItem toComplete = new MenuItem("Выполнил");
        final MenuItem postpone = new MenuItem("Отложить");
        final MenuItem deleteAndComplete = new MenuItem("Удалить и завершить задачу");
        final MenuItem overdueTask = new MenuItem("Не выполнил задачу");
        menu.add(toComplete);
        menu.add(postpone);
        menu.add(deleteAndComplete);
        menu.add(overdueTask);


        TrayIcon trayIcon = new TrayIcon(image, "Tray");
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip(task.name);
        trayIcon.setPopupMenu(menu);
        tray.add(trayIcon);

        toComplete.addActionListener(e -> {
            taskDao.completeTask(task);
            tray.remove(trayIcon);
            JOptionPane.showMessageDialog
                    (null,
                            task + " \nвыполнена",
                            "Выполнил",
                            JOptionPane.INFORMATION_MESSAGE
                    );
        });

        postpone.addActionListener(e -> postpone.addActionListener(e1 -> {
            String result = JOptionPane.showInputDialog("Введите дату по маске дд.мм.гггг чч:мм");
            String[] subStr = result.split("\\.|:| ");

            int day = 0, month = 0, year = 0, hh = 0, mm = 0;

            for (int i = 0; i < subStr.length; i++) {
                day = Integer.parseInt(subStr[0]);
                month = Integer.parseInt(subStr[1]);
                year = Integer.parseInt(subStr[2]);
                hh = Integer.parseInt(subStr[3]);
                mm = Integer.parseInt(subStr[4]);
            }
            LocalDateTime dateTime = LocalDateTime.of(year, month, day, hh, mm);
            taskDao.postponeTask(task, dateTime);
            tray.remove(trayIcon);
            JOptionPane.showMessageDialog
                    (null,
                            "Задача отложена на " +
                                    dateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
        }));

        deleteAndComplete.addActionListener(e -> {
            taskDao.deleteAndCompleteTask(task);
            JOptionPane.showMessageDialog
                    (null,
                            task + "\nЗадача удалена и завершена",
                            "Удалить и завершить задачу",
                            JOptionPane.INFORMATION_MESSAGE
                    );
        });

        overdueTask.addActionListener(e -> {
            taskDao.expiredTask(task);
            JOptionPane.showMessageDialog(null,
                    "task" + "\nпросрочена",
                    "Не выполнил задачу",
                    JOptionPane.INFORMATION_MESSAGE
                    );
        });

        trayIcon.displayMessage(task.name, task.toString(), TrayIcon.MessageType.INFO);


        try {
            Thread.sleep(60000);
            tray.remove(trayIcon);
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
