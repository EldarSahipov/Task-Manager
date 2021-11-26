package service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class InputValidation {
    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public static LocalDateTime getValidationDateTime(String input) {
        LocalDateTime dateTime = null;
        boolean a = true;
        while (a) {
            try {
                dateTime = LocalDateTime.parse(input, formatter);
            } catch (Exception ignored) {}
            if(dateTime != null && input.equals(formatter.format(dateTime))) {
                a = false;
            } else {
                //System.out.println("Введите корректное время по маске!");
                input = new Scanner(System.in).nextLine();
            }
        }
        return dateTime;
    }

}
