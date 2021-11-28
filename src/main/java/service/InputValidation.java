package service;

import java.time.LocalDateTime;
import java.util.Scanner;

import static models.Constant.FORMATTER;

public class InputValidation {

    public static LocalDateTime getValidationDateTime(String input) {
        LocalDateTime dateTime = null;
        boolean a = true;
        while (a) {
            try {
                dateTime = LocalDateTime.parse(input, FORMATTER);
            } catch (Exception ignored) {
                System.out.println("Неправильно! Введите еще раз время по маске");
                input = new Scanner(System.in).nextLine();
            }
            if(dateTime != null && input.equals(FORMATTER.format(dateTime))) {
                a = false;
            } else {
                System.out.println("Неправильно! Введите еще раз время по маске");
                input = new Scanner(System.in).nextLine();
            }
        }
        return dateTime;
    }

    public static String checkText(String str, String taskField) {
        if(!str.equals("")) {
            return str;
        }
        return taskField;
    }

}
