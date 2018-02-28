package com.example;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RestaurantTest {
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
    public void realTimeHourTest() {
        myRestaurant.setTime(60);
        assertEquals("7:0 Day: 1", myRestaurant.realTime());
    }

    @Test
    public void realTimeMinTest() {
        myRestaurant.setTime(30);
        assertEquals("6:30 Day: 1", myRestaurant.realTime());
    }

    @Test
    public void realTimeSmallTest() {
        myRestaurant.setTime(150);
        assertEquals("8:30 Day: 1", myRestaurant.realTime());
    }

    @Test
    public void realTimeBigTest() {
        myRestaurant.setTime(1000);
        assertEquals("22:40 Day: 1", myRestaurant.realTime());
    }

    @Test
    public void realTimeNewDayTest() {
        myRestaurant.setTime(1500);
        assertEquals("7:0 Day: 2", myRestaurant.realTime());
    }
}