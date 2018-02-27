package com.example;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CookMethodsTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void removeIngredientsTest() {
        String jsonContent = Simulation.getFileContentsAsString("Market.json");
        Gson gson = new Gson();
        Market market = gson.fromJson(jsonContent, Market.class);

        Food[] allFood = market.getFood();
        Recipe pizza = market.getRecipe()[0];

        double budget = 100.00;
        int popularity = 0;
        List<Food> foodList = new ArrayList<>(Arrays.asList(allFood));
        List<Equipment> equipList = new ArrayList<>();
        List<Recipe> recipeList = new ArrayList<>();
        recipeList.add(pizza);
        int time = 0;
        Restaurant myRestaurant = new Restaurant(foodList, equipList, recipeList,
                budget, popularity, time);

        List<Food> output = new ArrayList<>(Arrays.asList(pizza.getFood()));
        assertEquals(output, CookMethods.removeIngredients(myRestaurant, pizza));
    }

    @Test
    public void checkEquipmentsTrueTest() {
        String jsonContent = Simulation.getFileContentsAsString("Market.json");
        Gson gson = new Gson();
        Market market = gson.fromJson(jsonContent, Market.class);

        Equipment[] allEquipments = market.getEquipment();
        Recipe pizza = market.getRecipe()[0];

        double budget = 100.00;
        int popularity = 0;
        List<Food> foodList = new ArrayList<>();
        List<Equipment> equipList = new ArrayList<>(Arrays.asList(allEquipments));
        List<Recipe> recipeList = new ArrayList<>();
        recipeList.add(pizza);
        int time = 0;
        Restaurant myRestaurant = new Restaurant(foodList, equipList, recipeList,
                budget, popularity, time);

        boolean output = true;
        assertEquals(output, CookMethods.checkEquipments(myRestaurant, pizza));
    }

    @Test
    public void checkEquipmentsFalseTest() {
        String jsonContent = Simulation.getFileContentsAsString("Market.json");
        Gson gson = new Gson();
        Market market = gson.fromJson(jsonContent, Market.class);

        Equipment refrigerator = market.getEquipment()[3];
        Recipe pizza = market.getRecipe()[0];

        double budget = 100.00;
        int popularity = 0;
        List<Food> foodList = new ArrayList<>();
        List<Equipment> equipList = new ArrayList<>();
        equipList.add(refrigerator);
        List<Recipe> recipeList = new ArrayList<>();
        int time = 0;
        Restaurant myRestaurant = new Restaurant(foodList, equipList, recipeList,
                budget, popularity, time);

        boolean output = false;
        assertEquals(output, CookMethods.checkEquipments(myRestaurant, pizza));
    }
}