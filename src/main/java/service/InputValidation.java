package service;

import models.Constant;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static models.Constant.FORMATTER;

public class InputValidation {

    public static LocalDateTime getValidationDateTime(String input) {
        LocalDateTime dateTime = null;
        boolean a = true;
        while (a) {
            try {
                dateTime = LocalDateTime.parse(input, FORMATTER);
            } catch (Exception ignored) {}
            if(dateTime != null && input.equals(FORMATTER.format(dateTime))) {
                a = false;
            } else {
                input = new Scanner(System.in).nextLine();
            }
        }
        return dateTime;
    }

}
