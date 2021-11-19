package view;

import models.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class TaskView {
    private final Scanner scanner = new Scanner(System.in);

    public Task createTaskView() {
        System.out.println("Введите название задачи");
        String name = scanner.nextLine();
        System.out.println("Введите описание задачи");
        String description = scanner.nextLine();
        LocalDateTime time = enterTime();
        System.out.println("Введите контакты (если их нет, то нажмите enter)");
        String contacts = scanner.nextLine();
        return new Task(name, description, time, contacts);
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
        LocalDateTime time = enterTime();
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


    public void printTasks(List<Task> taskList) {
        System.out.println(taskList);
    }

    public LocalDateTime enterTime() {
        System.out.println("Введите дату по маске дд.мм.гггг чч:мм");
        String str = scanner.nextLine();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return LocalDateTime.parse(str, dateTimeFormatter);
    }


}
