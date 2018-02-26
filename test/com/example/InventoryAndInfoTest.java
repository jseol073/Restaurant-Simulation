package com.example;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class InventoryAndInfoTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void printInventoryTest() {
        double budget = 100.00;
        int popularity = 0;
        List<Food> foodList = new ArrayList<>();
        List<Equipment> equipList = new ArrayList<>();
        List<Recipe> recipeList = new ArrayList<>();
        int time = 0;
        Restaurant myRestaurant = new Restaurant(foodList, equipList, recipeList,
                budget, popularity, time);

        String type = "food";
        String output = "Food inventory is empty";
        assertEquals(output, InventoryAndInfo.printInventory(myRestaurant, type));
    }

    @Test
    public void printInfoTest() {
        double budget = 100.00;
        int popularity = 0;
        List<Food> foodList = new ArrayList<>();
        List<Equipment> equipList = new ArrayList<>();
        List<Recipe> recipeList = new ArrayList<>();
        int time = 0;
        Restaurant myRestaurant = new Restaurant(foodList, equipList, recipeList,
                budget, popularity, time);
        myRestaurant.setFoodInventory(myRestaurant.getFoodInventory().add());

        String item = "chicken";
    }
}