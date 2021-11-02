package models;

import java.util.*;

public class ProgramRun {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int text;
        System.out.println("Планировщик задач");
        System.out.println("Выберите число необходимой функции");
        System.out.println("1. Добавить задачу");
        System.out.println("2. Поиск задачи");
        System.out.println("3. Вывести все задачи");
        System.out.println("4. Изменить задачу");
        System.out.println("5. Удалить задачу");
        System.out.println("6. Показать завершенные задачи");
        System.out.println("7. Удалить завершенные задачи");
        text = scanner.nextInt();

        switch (text) {
            case(1):
                System.out.println("Введите название задачи");
                scanner.nextLine();
                String name = scanner.nextLine();
                System.out.println("Введите описание задачи");
                scanner.nextLine();
                String description = scanner.nextLine();
                System.out.println("Введите день напоминания");
                int day = scanner.nextInt();
                System.out.println("Введите месяц напоминания");
                int month = scanner.nextInt() - 1;
                System.out.println("Введите час напоминания");
                int hh = scanner.nextInt();
                System.out.println("Введите минуты напоминания");
                int mm = scanner.nextInt();
                Calendar time = new GregorianCalendar(2021, month, day, hh, mm,0);
                System.out.println("Введите контакты (если нет, то нажмите enter)");
                scanner.nextLine();
                String contacts = scanner.nextLine();
                UserInterface.getUserInterface().addTask(new Task(name, description, time, contacts));
                break;

            case (2):
                System.out.println("Введите имя задачи, которую нужно найти");
                scanner.nextLine();
                name = scanner.nextLine();
                System.out.println(UserInterface.getUserInterface().getTask(name));
                break;

            case(3):
                List<Task> list = UserInterface.getUserInterface().getAll();
                for(Task task: list) {
                    System.out.println(task);
                }
                break;

            case(4):
                System.out.println("Введите имя задачи которую нужно изменить");
                scanner.nextLine();
                name = scanner.nextLine();
                Task task = UserInterface.getUserInterface().getTask(name);
                System.out.println("Введите новое описание задачи");
                scanner.nextLine();
                task.setDescription(scanner.nextLine());
                System.out.println("Введите день напоминания");
                day = scanner.nextInt();
                System.out.println("Введите месяц напоминания");
                month = scanner.nextInt() - 1;
                System.out.println("Введите час напоминания");
                hh = scanner.nextInt();
                System.out.println("Введите минуты напоминания");
                mm = scanner.nextInt();
                task.setTime(new GregorianCalendar(2021, month, day, hh, mm,0));
                System.out.println("Введите контакты (если нет, то нажмите enter)");
                scanner.nextLine();
                task.setContacts(scanner.nextLine());
                UserInterface.getUserInterface().setTask(task);
                break;

            case(5):
                System.out.println("Введите название задачи, которую нужно удалить");
                UserInterface.getUserInterface().deleteTask(
                        UserInterface.getUserInterface().getTask(scanner.nextLine()));
                break;

            case(6):

        }
    }
}
