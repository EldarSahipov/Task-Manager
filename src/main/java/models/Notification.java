package models;

import org.apache.log4j.Logger;
import repo.TaskDao;
import service.TaskService;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

public class Notification extends TimerTask {
    private static final Logger log = Logger.getLogger(Notification.class);
    private final TaskDao taskDao = new TaskDao();
    private final TaskService taskService = new TaskService(taskDao);
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"); //  в константы
    private final List<Integer> listNotification = new ArrayList<>();


    @Override
    public void run() {
        try {
            displayTray();
        } catch (AWTException e) {
            log.error(e);
        }
    }

    public LocalDateTime getDelayedDateTimeTask(String datetime, Task task) {
        switch (datetime) {
            case "5 минут":
                return task.time = task.time.plusMinutes(5);
            case "30 минут":
                return task.time = task.time.plusMinutes(30);
            case "1 час":
                return task.time = task.time.plusHours(1);
            case "6 часов":
                return task.time = task.time.plusHours(6);
            case "24 часа":
                return task.time = task.time.plusDays(1);
        }
        return null;
    }


    public void displayTray() throws AWTException {
        List<Task> taskList = taskDao.searchTasksClosestInTime();
        if(taskList != null) {
            for(int i = 0; i < taskList.size(); i++) {
                if(!listNotification.contains(taskList.get(i).id) &&
                        taskList.get(i).time.format(formatter)
                                .equals(LocalDateTime.now().format(formatter))) {

                    listNotification.add(taskList.get(i).id);
                    SystemTray tray = SystemTray.getSystemTray();
                    Image image = Toolkit.getDefaultToolkit().createImage("src/main/resources/icon.jpg");

                    final PopupMenu menu = new PopupMenu();

                    final MenuItem toCompleteTask = new MenuItem("Выполнил");
                    final MenuItem postponeTask = new MenuItem("Отложить");
                    final MenuItem changeDateTimeTask = new MenuItem("Изменить время задачи");
                    final MenuItem deleteTask = new MenuItem("Удалить задачу");
                    final MenuItem overdueTask = new MenuItem("Не выполнил задачу");
                    menu.add(toCompleteTask);
                    menu.add(postponeTask);
                    menu.add(changeDateTimeTask);
                    menu.add(deleteTask);
                    menu.add(overdueTask);

                    TrayIcon trayIcon = new TrayIcon(image, "Tray");
                    trayIcon.setImageAutoSize(true);
                    trayIcon.setToolTip(taskList.get(i).name);
                    trayIcon.setPopupMenu(menu);
                    tray.add(trayIcon);

                    int finalI = i;

                    toCompleteTask.addActionListener(e -> {
                        taskService.completeTask(taskList.get(finalI));
                        tray.remove(trayIcon);
                        JOptionPane.showMessageDialog
                                (null,
                                        taskList + " \nвыполнена",
                                        "Выполнил",
                                        JOptionPane.INFORMATION_MESSAGE
                                );
                    });

                    postponeTask.addActionListener(e -> changeDateTimeTask.addActionListener(e1 -> {
                        String[] timeInterval = {
                                "5 минут",
                                "30 минут",
                                "1 час",
                                "6 часов",
                                "24 часа"
                        };
                        String result = (String) JOptionPane.showInputDialog(null,"Отложить на:", "Отложить",
                                JOptionPane.QUESTION_MESSAGE,
                                null, timeInterval, timeInterval[0]);
                        LocalDateTime dateTime = getDelayedDateTimeTask(result, taskList.get(finalI));
                        taskService.changeDateTimeTask(taskList.get(finalI), dateTime);
                        tray.remove(trayIcon);
                        JOptionPane.showMessageDialog
                                (null,
                                        "Задача отложена на " +
                                                dateTime.format(formatter));
                    }));

                    changeDateTimeTask.addActionListener(e -> changeDateTimeTask.addActionListener(e1 -> {
                        String result = JOptionPane.showInputDialog("Введите дату по маске дд.мм.гггг чч:мм");
                        LocalDateTime dateTime = null;
                        boolean a = true;
                        while (a) {
                            try {
                                dateTime = LocalDateTime.parse(result, formatter);
                            } catch (Exception ignored) {}
                            if(dateTime != null && result.equals(formatter.format(dateTime))) {
                                a = false;
                            } else {
                                result = JOptionPane.showInputDialog("Введите дату по маске дд.мм.гггг чч:мм");
                            }
                        }
                        taskService.changeDateTimeTask(taskList.get(finalI), dateTime);
                        tray.remove(trayIcon);
                        JOptionPane.showMessageDialog
                                (null,
                                        "Задача отложена на " +
                                                dateTime.format(formatter));
                    }));

                    deleteTask.addActionListener(e -> {
                        taskDao.delete(taskList.get(finalI));
                        JOptionPane.showMessageDialog
                                (null,
                                        taskList.get(finalI) + "\nЗадача удалена",
                                        "Удалить",
                                        JOptionPane.INFORMATION_MESSAGE
                                );
                        tray.remove(trayIcon);
                    });

                    overdueTask.addActionListener(e -> {
                        taskService.expiredTask(taskList.get(finalI));
                        JOptionPane.showMessageDialog(null,
                                taskList.get(finalI) + "\nпросрочена",
                                "Не выполнил задачу",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                        tray.remove(trayIcon);
                    });

                    trayIcon.displayMessage(taskList.get(i).name, taskList.get(i).toString(), TrayIcon.MessageType.INFO);
                }
            }
        }
    }
}
