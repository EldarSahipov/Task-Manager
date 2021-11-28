package view;

import models.Task;
import org.json.simple.JSONObject;

import java.time.LocalDateTime;
import java.util.Scanner;

import static service.InputValidation.getValidationDateTime;
import static models.JsonParser.getJsonObject;

public class InputTaskView {
    private final Scanner scanner = new Scanner(System.in);
    private final JSONObject jsonObject = getJsonObject();


    public Task createNewTask() {
        assert jsonObject != null;
        System.out.println(jsonObject.get("enterNameTask"));
        String name = scanner.nextLine();
        System.out.println(jsonObject.get("enterDescriptionTask"));
        String description = scanner.nextLine();
        LocalDateTime time = enterTime();
        System.out.println(jsonObject.get("enterContactsTask"));
        String contacts = scanner.nextLine();
        return new Task(name, description, time, contacts);
    }

    public LocalDateTime enterTime() {
        assert jsonObject != null;
        System.out.println(jsonObject.get("enterDateTimeTask"));
        return getValidationDateTime(scanner.nextLine());
    }

    public Task setTaskView(Task task) {
        assert jsonObject != null;
        System.out.println(jsonObject.get("enterContactsTask"));
        task.setName(scanner.nextLine());
        System.out.println(jsonObject.get("enterNewDescriptionTask"));
        task.setDescription(scanner.nextLine());
        LocalDateTime time = enterTime();
        task.setTime(time);
        System.out.println(jsonObject.get("enterContactsTask"));
        task.setContacts(scanner.nextLine());
        return task;
    }

}
