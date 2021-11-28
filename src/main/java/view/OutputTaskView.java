package view;

import models.JsonParser;
import models.Task;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.Scanner;

public class OutputTaskView {
    private final Scanner scanner = new Scanner(System.in);
    private final JSONObject jsonParser = JsonParser.getJsonObject();

    public void printTaskAll(List<Task> taskList) {
        assert jsonParser != null;
        System.out.println(jsonParser.get("taskAll"));
        taskList.forEach((task -> System.out.println((taskList.indexOf(task) + 1) + " " + task)));
    }

    public String getNameTask() {
        assert jsonParser != null;
        System.out.println(jsonParser.get("enterNameOfTaskYouWantToFind"));
        return scanner.nextLine();
    }

    public void printTasks(List<Task> taskList) {
        taskList.forEach((task -> System.out.println((taskList.indexOf(task) + 1) + " " + task)));
    }

    public void printMenu() {
        assert jsonParser != null;
        System.out.println(jsonParser.get("menu"));
    }
}
