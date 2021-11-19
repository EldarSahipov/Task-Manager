package models;

import org.apache.log4j.Logger;
import repo.TaskDao;
import service.TaskService;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimerTask;

public class Notification extends TimerTask {
    private static final Logger log = Logger.getLogger(Notification.class);
    private final TaskService taskService = new TaskService();
    private final TaskDao taskDao = new TaskDao();

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    @Override
    public void run() {
        try {
            displayTray();
        } catch (AWTException e) {
            log.error(e);
        }
    }

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
            taskService.completeTask(task);
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
            LocalDateTime dateTime = LocalDateTime.parse(result, formatter);
            taskService.postponeTask(task, dateTime);
            tray.remove(trayIcon);
            JOptionPane.showMessageDialog
                    (null,
                            "Задача отложена на " +
                                    dateTime.format(formatter));
        }));

        deleteAndComplete.addActionListener(e -> {
            taskService.deleteAndCompleteTask(task);
            JOptionPane.showMessageDialog
                    (null,
                            task + "\nЗадача удалена и завершена",
                            "Удалить и завершить задачу",
                            JOptionPane.INFORMATION_MESSAGE
                    );
            tray.remove(trayIcon);
        });

        overdueTask.addActionListener(e -> {
            taskService.expiredTask(task);
            JOptionPane.showMessageDialog(null,
                    "task" + "\nпросрочена",
                    "Не выполнил задачу",
                    JOptionPane.INFORMATION_MESSAGE
                    );
            tray.remove(trayIcon);
        });

        trayIcon.displayMessage(task.name, task.toString(), TrayIcon.MessageType.INFO);
    }
}
