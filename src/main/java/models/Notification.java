package models;

import org.json.simple.JSONObject;
import repo.TaskDao;
import service.TaskService;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;

import static models.Constant.*;

public class Notification extends TimerTask {
    private final TaskDao taskDao = new TaskDao();
    private final TaskService taskService = new TaskService(taskDao);
    private final List<Integer> listNotification = new ArrayList<>();
    private final JSONObject jsonObject = JsonParser.getJsonObject();

    @Override
    public void run() {
        try {
            displayTray();
        } catch (AWTException e) {
            LOGGER.error(e);
        }
    }

    public LocalDateTime getDelayedDateTimeTask(String datetime, Task task) {
        DelayedTime delayedTime = DelayedTime.getType(datetime);
        switch (delayedTime) {
            case FIVE_MINUTES: return task.time = task.time.plusMinutes(5);
            case THIRTY_MINUTES: return task.time = task.time.plusMinutes(30);
            case ONE_HOUR: return task.time = task.time.plusHours(1);
            case SIX_HOURS: return task.time = task.time.plusHours(6);
            case TWENTY_FOUR_HOURS: return task.time = task.time.plusDays(1);
        }
        return null;
    }

    public void displayTray() throws AWTException {
        List<Task> taskList = taskDao.searchTasksClosestInTime();
        if(taskList != null) {
            for(int i = 0; i < taskList.size(); i++) {
                if(!listNotification.contains(taskList.get(i).id) &&
                        taskList.get(i).time.format(FORMATTER)
                                .equals(LocalDateTime.now().format(FORMATTER))) {

                    listNotification.add(taskList.get(i).id);
                    SystemTray tray = SystemTray.getSystemTray();
                    Image image = Toolkit.getDefaultToolkit().createImage(FILE_NAME_IMAGE);

                    final PopupMenu menu = new PopupMenu();

                    assert jsonObject != null;
                    final MenuItem toCompleteTask = new MenuItem((String) jsonObject.get("completed"));
                    final MenuItem postponeTask = new MenuItem((String) jsonObject.get("postpone"));
                    final MenuItem changeDateTimeTask = new MenuItem((String) jsonObject.get("changeDateTimeTask"));
                    final MenuItem deleteTask = new MenuItem((String) jsonObject.get("deleteTask"));
                    final MenuItem overdueTask = new MenuItem((String) jsonObject.get("didntCompleteTheTask"));
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
                                        taskList + " \n" + jsonObject.get("completed"),
                                        (String) jsonObject.get("completed"),
                                        JOptionPane.INFORMATION_MESSAGE
                                );
                    });

                    postponeTask.addActionListener(e -> {
                        String[] timeInterval = {
                                (String) jsonObject.get("min5"),
                                (String) jsonObject.get("min30"),
                                (String) jsonObject.get("hour1"),
                                (String) jsonObject.get("hour6"),
                                (String) jsonObject.get("hour24")
                        };

                        String result = (String) JOptionPane.showInputDialog
                                (null,
                                        jsonObject.get("setAsideFor"),
                                        (String) jsonObject.get("postpone"),
                                JOptionPane.QUESTION_MESSAGE,
                                null, timeInterval, timeInterval[0]);

                        LocalDateTime dateTime = getDelayedDateTimeTask(result, taskList.get(finalI));
                        taskService.changeDateTimeTask(taskList.get(finalI), dateTime);
                        tray.remove(trayIcon);
                        JOptionPane.showMessageDialog
                                (null,
                                        jsonObject.get("theTaskHasBeenPostponedFor") +
                                                dateTime.format(FORMATTER));
                    });

                    changeDateTimeTask.addActionListener(e -> {
                        String result = JOptionPane.showInputDialog(jsonObject.get("enterDateTimeTask"));
                        LocalDateTime dateTime = null;
                        boolean a = true;
                        while (a) {
                            try {
                                dateTime = LocalDateTime.parse(result, FORMATTER);
                            } catch (Exception ignored) {}
                            if(dateTime != null && result.equals(FORMATTER.format(dateTime))) {
                                a = false;
                            } else {
                                result = JOptionPane.showInputDialog(jsonObject.get("enterDateTimeTask"));
                            }
                        }
                        taskService.changeDateTimeTask(taskList.get(finalI), dateTime);
                        tray.remove(trayIcon);
                        JOptionPane.showMessageDialog
                                (null,
                                        jsonObject.get("theTaskHasBeenPostponedFor") +
                                                dateTime.format(FORMATTER));
                    });

                    deleteTask.addActionListener(e -> {
                        taskDao.delete(taskList.get(finalI));
                        JOptionPane.showMessageDialog
                                (null,
                                        taskList.get(finalI) + "\n" + jsonObject.get("taskDeleted"),
                                        (String) jsonObject.get("delete"),
                                        JOptionPane.INFORMATION_MESSAGE
                                );
                        tray.remove(trayIcon);
                    });

                    overdueTask.addActionListener(e -> {
                        taskService.expiredTask(taskList.get(finalI));
                        JOptionPane.showMessageDialog(null,
                                taskList.get(finalI) + "\n" + jsonObject.get("expired"),
                                (String) jsonObject.get("didntCompleteTheTask"),
                                JOptionPane.INFORMATION_MESSAGE
                        );
                        tray.remove(trayIcon);
                    });

                    trayIcon.displayMessage(
                            taskList.get(i).name,
                            taskList.get(i).toString(),
                            TrayIcon.MessageType.INFO);
                }
            }
        }
    }
}
