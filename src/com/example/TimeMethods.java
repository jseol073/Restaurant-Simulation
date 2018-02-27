package com.example;

public class TimeMethods {

    public static String passTime(Restaurant restaurant, String timeInput) {
        String[] timeInputArr = timeInput.split("\\s+");
        if (timeInputArr.length == 2 && timeInputArr[0].equalsIgnoreCase("time")) {
            if (timeInputArr[1].matches(".*\\d+.*")) {

            } else {
                return String.format("%s must be a number", timeInputArr[1]);
            }
        }
        return String.format("I don't understand %s", timeInput);
    }

    //public static
}
