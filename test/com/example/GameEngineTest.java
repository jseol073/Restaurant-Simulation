package com.example;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GameEngineTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void handleUserInputMkt() {
        double budget = 100.00;
        int popularity = 0;
        List<Food> foodList = new ArrayList<>();
        List<Equipment> equipList = new ArrayList<>();
        List<Recipe> recipeList = new ArrayList<>();
        int time = 0;
        Restaurant myRestaurant = new Restaurant(foodList, equipList, recipeList,
                budget, popularity, time);
        GameEngine test = new GameEngine();
        String jsonContent = Simulation.getFileContentsAsString("Products.json");
        Gson gson = new Gson();
        Products market = gson.fromJson(jsonContent, Products.class);
        String userInput = "list food";
        String output = "{eggs= Price: $1.50, tomatoes= Price: $1.25, bread= Price: $1.00, " +
                "chicken= Price: $3.50, potatoes= Price: $1.75, onion= Price: $0.50, noodles= " +
                "Price: $2.00, lettuce= Price: $0.75, water= Price: $0.00, cheese= Price: $1.25}";
        assertEquals(output, test.handleUserInputMkt(market, myRestaurant, userInput));
    }

    @Test
    public void handleUserInput() {
    }
}