package view;

import models.Task;
import org.json.simple.JSONObject;

import java.time.LocalDateTime;
import java.util.Scanner;

import static service.InputValidation.checkText;
import static service.InputValidation.getValidationDateTime;
import static models.JsonParser.getJsonObject;

public class InputTaskView {
    private final Scanner scanner = new Scanner(System.in);
    private final JSONObject message = getJsonObject();

    public Task createNewTask() {
        assert message != null;
        System.out.println(message.get("enterNameTask"));
        String name = scanner.nextLine();
        System.out.println(message.get("enterDescriptionTask"));
        String description = scanner.nextLine();
        LocalDateTime time = enterTime();
        System.out.println(message.get("enterContactsTask"));
        String contacts = scanner.nextLine();
        return new Task(name, description, time, contacts);
    }

    public LocalDateTime enterTime() {
        assert message != null;
        System.out.println(message.get("enterDateTimeTask"));
        return getValidationDateTime(scanner.nextLine());
    }

    public Task setTaskView(Task task) {
        assert message != null;
        System.out.println(message.get("enterNewNameTask"));
        task.setName(checkText(scanner.nextLine(), task.name));
        System.out.println(message.get("enterNewDescriptionTask"));
        task.setDescription(checkText(scanner.nextLine(), task.description));
        LocalDateTime time = enterTime();
        task.setTime(time);
        System.out.println(message.get("enterContactsTask"));
        task.setContacts(checkText(scanner.nextLine(), task.contacts));
        return task;
    }

}
