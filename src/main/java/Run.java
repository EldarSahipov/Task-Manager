import Controller.TaskController;
import dao.TaskDao;
import models.Notification;
import view.TaskView;

import java.util.Scanner;

public class Run {


    public static void main(String[] args) {
        System.out.println("Планировщик задач");
        System.out.println("1. Добавить задачу");
        System.out.println("2. Поиск задачи");
        System.out.println("3. Вывести все задачи");
        System.out.println("4. Изменить задачу");
        System.out.println("5. Удалить задачу");
        System.out.println("6. Показать завершенные задачи");
        System.out.println("7. Удалить завершенные задачи");
        System.out.println("8. Показать просроченные задачи");
        System.out.println("9. Показать непросроченные задачи");

        TaskView taskView = new TaskView();
        TaskDao taskDao = new TaskDao();
        Notification notification = new Notification();
        TaskController taskController = new TaskController(taskView, taskDao, notification);

        Scanner scanner = new Scanner(System.in);
        int text;

        text = scanner.nextInt();
        switch (text) {
            case (1):
                taskController.addTask();
                break;
            case (2):
                taskController.findTaskByName();
                break;
            case (3):
                taskController.getTasksAll();
                break;
            case (4):
                taskController.changeTask();
                break;
            case (5):
                taskController.deleteTask();
                break;
            case (6):
                break;
            case (7):
                break;
            case (8):

                break;

        }

    }
}