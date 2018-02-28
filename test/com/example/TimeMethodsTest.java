package com.example;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TimeMethodsTest {
    private static Restaurant myRestaurant;

    @Before
    public void setUp() throws Exception {
        double budget = 100.00;
        int popularity = 0;
        List<Food> foodList = new ArrayList<>();
        List<Equipment> equipList = new ArrayList<>();
        List<Recipe> recipeList = new ArrayList<>();
        int time = 0;
        myRestaurant = new Restaurant(foodList, equipList, recipeList,
                budget, popularity, time);
    }

    @Test
    public void handleTimeMinTest() {
        String input = "time 50";
        assertEquals("Time is now 6:50 Day: 1", TimeMethods.handleTime(myRestaurant, input));
    }

    @Test
    public void handleHourTest() {
        String input = "time 120";
        assertEquals("Time is now 8:0 Day: 1", TimeMethods.handleTime(myRestaurant, input));
    }

    @Test
    public void handleDayTest() {
        String input = "time 1500";
        assertEquals("Time is now 7:0 Day: 2", TimeMethods.handleTime(myRestaurant, input));
    }
}