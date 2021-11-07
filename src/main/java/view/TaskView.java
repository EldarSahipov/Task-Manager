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
        task.setTime(time);
        System.out.println("Введите контакты (если нет, то нажмите enter)");
        task.setContacts(scanner.nextLine());
        return task;
    }

    public String getNameTask() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя задачи, которую хотите найти");
        return scanner.nextLine();
    }


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
