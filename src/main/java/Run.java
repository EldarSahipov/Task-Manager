import controller.TaskController;
import models.TaskTimer;
import repo.TaskDao;
import view.InputTaskView;
import view.OutputTaskView;
import java.util.Scanner;

public class Run {

    public static void main(String[] args) {
        InputTaskView inputTaskView = new InputTaskView();
        OutputTaskView outputTaskView = new OutputTaskView();
        TaskDao taskDao = new TaskDao();
        TaskController taskController = new TaskController(inputTaskView, outputTaskView, taskDao);
        TaskTimer taskTimer = new TaskTimer();
        taskTimer.start();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            taskController.getMenu();
            int text = scanner.nextInt();
            switch (text) {
                case (1):
                    taskController.addTask();
                    break;
                case (2):
                    taskController.findTasksByName();
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
                    taskController.getCompletedTasks();
                    break;
                case (7):
                    taskController.deleteCompletedTask();
                    break;
                case (8):
                    taskController.getExpiredTasks();
                    break;
                case (9):
                    taskController.getActiveTasks();
                    break;
                case (10):
                    taskController.exit();
                }
            }
        }
    }
