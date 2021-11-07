package view;

import models.Status;
import models.Task;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

public class TaskView {
    private final Scanner scanner = new Scanner(System.in);

    public Task getTaskView() {
        System.out.println("Введите название задачи");
        String name = scanner.nextLine();
        System.out.println("Введите описание задачи");
        String description = scanner.nextLine();
        Calendar time = enterTime();
        System.out.println("Введите контакты (если их нет, то нажмите enter)");
        scanner.next();
        String contacts = scanner.nextLine();
        Status status = Status.NOT_EXPIRED;
        return new Task(name, description, time, contacts, status);
    }

    public void printTaskAll(List<Task> taskList) {
        System.out.println("Все задачи:");
        System.out.print(taskList);
    }

    public Task setTaskView(Task task) {
        System.out.println("Введите новое название задачи");
        task.setName(scanner.nextLine());
        System.out.println("Введите новое описание задачи");
        task.setDescription(scanner.nextLine());
        Calendar time = enterTime();
        System.out.println("Введите контакты (если нет, то нажмите enter)");
        scanner.nextLine();
        return task;
    }

    public String getNameTask() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя задачи, которую хотите найти");
        return scanner.nextLine();
    }

//    public void

    public void printTask(List<Task> taskList) {
        System.out.println(taskList);
    }

    public Calendar enterTime() {
        System.out.println("Введите день напоминания");
        int day = scanner.nextInt();
        System.out.println("Введите месяц напоминания");
        int month = scanner.nextInt();
        System.out.println("Введите час напоминания");
        int hh = scanner.nextInt();
        System.out.println("Введите минуты напоминания");
        int mm = scanner.nextInt();
        return new GregorianCalendar(2021, month - 1, day, hh, mm);
    }








}

//
//        Notification notification = new Notification();
//
//
//        notification.displayTray();
//
//
//        TaskController ts = new TaskController();
//        Scanner scanner = new Scanner(System.in);
//        int text;
//        System.out.println("Планировщик задач");
//        System.out.println("1. Добавить задачу");
//        System.out.println("2. Поиск задачи");
//        System.out.println("3. Вывести все задачи");
//        System.out.println("4. Изменить задачу");
//        System.out.println("5. Удалить задачу");
//        System.out.println("6. Показать завершенные задачи");
//        System.out.println("7. Удалить завершенные задачи");
//        System.out.println("8. Показать незавершенные задачи");
//
//        while(true) {
//            System.out.println("\nВыберите число необходимой функции");
//            text = scanner.nextInt();
//            switch (text) {
//                case(1):
//                    System.out.println("Введите название задачи");
//                    scanner.nextLine();
//                    String name = scanner.nextLine();
//                    System.out.println("Введите описание задачи");
//                    String description = scanner.nextLine();
//                    System.out.println("Введите день напоминания");
//                    int day = scanner.nextInt();
//                    System.out.println("Введите месяц напоминания");
//                    int month = scanner.nextInt() - 1;
//                    System.out.println("Введите час напоминания");
//                    int hh = scanner.nextInt();
//                    System.out.println("Введите минуты напоминания");
//                    int mm = scanner.nextInt();
//                    Date time = new Date(2021, month, day, hh, mm,0);
//                    System.out.println("Введите контакты (если нет, то нажмите enter)");
//                    scanner.nextLine();
//                    String contacts = scanner.nextLine();
//                    ts.save(new Task(name, description, time, contacts));
//                    break;
//
//                case (2):
//                    System.out.println("Введите имя задачи, которую нужно найти");
//                    scanner.nextLine();
//                    name = scanner.nextLine();
//                    System.out.println(ts.findByName(name));
//                    break;

//                case(3):
//                    List<Task> list = ts.getAll();
//                    for(Task task: list) {
//                        System.out.println(task);
//                    }
//                    break;

//                case(4):
//                    System.out.println("Введите имя задачи которую нужно изменить");
//                    scanner.nextLine();
//                    name = scanner.nextLine();
//                    Task task = ts.findByName(name).get(0);
//                    System.out.println("Введите новое название задачи");
//                    task.setName(scanner.nextLine());
//                    System.out.println("Введите новое описание задачи");
//                    task.setDescription(scanner.nextLine());
//                    System.out.println("Введите день напоминания");
//                    day = scanner.nextInt();
//                    System.out.println("Введите месяц напоминания");
//                    month = scanner.nextInt() - 1;
//                    System.out.println("Введите час напоминания");
//                    hh = scanner.nextInt();
//                    System.out.println("Введите минуты напоминания");
//                    mm = scanner.nextInt();
//                    task.setTime(new Date(2021, month, day, hh, mm,0));
//                    System.out.println("Введите контакты (если нет, то нажмите enter)");
//                    scanner.nextLine();
//                    task.setContacts(scanner.nextLine());
//                    ts.update(task);
//                    break;
//
//                case(5):
//                    System.out.println("Введите название задачи, которую нужно удалить");
//                    ts.delete(
//                            ts.findByName(scanner.nextLine()).get(0));
//                    break;
//
//                case(6):
//                    if(ts.getCompletedTasks() == null) {
//                        System.out.println("Завершенных задач нет");
//                        break;
//                    }
//                    System.out.println(ts.getCompletedTasks());
//                    break;
//
//                case(7):
//                    ts.deleteTasksCompleted();
//                    System.out.println("Завершенные задачи удалены");
//                    break;
//
//                case (8):
//                    if (ts.toString().equals("[]")) {
//                        System.out.println("Нет незавершенных задач");
//                        break;
//                    }
//                    System.out.println(ts.getUncompletedTasks());
//                    break;
//            }
//        }

