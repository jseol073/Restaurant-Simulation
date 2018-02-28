package com.example;

public class TimeMethods {
    private static final int PASS_TIME_LIMIT = 1440; //1 Day

    /**
     * Handles the user's input after their first word is "pass"
     * @param restaurant
     * @param timeInput, a string that should have time and minutes
     * @return if input is valid, calls helper method passTime. Otherwise, return invalid error strings
     */
    public static String handleTime(Restaurant restaurant, String timeInput) {
        String[] timeInputArr = timeInput.split("\\s+");
        if (timeInputArr.length == 2 && timeInputArr[0].equalsIgnoreCase("time")) {
            if (timeInputArr[1].matches(".*\\d+.*")) {
                return passTime(restaurant, timeInputArr[1]);
            } else {
                return String.format("'%s' must be a number", timeInputArr[1]);
            }
        }
        return String.format("I don't understand '%s'", timeInput);
    }

    /**
     * Sets the new time by adding variable time to the current time
     * @param restaurant
     * @param time, a number that represents minutes and must be less than or equal to 1 day
     * @return a string that announces the new time
     */
    public static String passTime(Restaurant restaurant, String time) {
        int minutes = Integer.parseInt(time);
        if (minutes <= PASS_TIME_LIMIT) {
            int currTime = restaurant.getTime();
            restaurant.setTime(currTime + minutes);
            return String.format("Time is now %s", restaurant.realTime());
        }
        return "Minutes cannot exceed over one day";
    }
}
