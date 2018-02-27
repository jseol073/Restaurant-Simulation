package com.example;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MenuMethodsTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void recipeToFood() {
        String jsonContent = Simulation.getFileContentsAsString("Market.json");
        Gson gson = new Gson();
        Market market = gson.fromJson(jsonContent, Market.class);
        Recipe pizza = market.getRecipe()[0];
        assertEquals(new Food("pizza", 5.25), MenuMethods.recipeToFood(pizza));
    }

    @Test
    public void listMenuEmptyTest() {
        double budget = 100.00;
        int popularity = 0;
        List<Food> foodList = new ArrayList<>();
        List<Equipment> equipList = new ArrayList<>();
        List<Recipe> recipeList = new ArrayList<>();
        int time = 0;
        Restaurant myRestaurant = new Restaurant(foodList, equipList, recipeList,
                budget, popularity, time);

        assertEquals("You don't have any cooked food", MenuMethods.listMenu(myRestaurant));
    }

    @Test
    public void listMenuTest() {
        String jsonContent = Simulation.getFileContentsAsString("Market.json");
        Gson gson = new Gson();
        Market market = gson.fromJson(jsonContent, Market.class);

        double budget = 100.00;
        int popularity = 0;
        List<Food> foodList = new ArrayList<>();
        List<Equipment> equipList = new ArrayList<>();
        List<Recipe> recipeList = new ArrayList<>();
        int time = 0;
        Restaurant myRestaurant = new Restaurant(foodList, equipList, recipeList,
                budget, popularity, time);
        List<Food> cookedFoodList = new ArrayList<>();
        cookedFoodList.add(new Food("pizza", 5.25));
        myRestaurant.setMenuList(cookedFoodList);

        assertEquals("[Food{name='pizza', price=5.25}]", MenuMethods.listMenu(myRestaurant));
    }

    @Test
    public void removeDishTest() {
        double budget = 100.00;
        int popularity = 0;
        List<Food> foodList = new ArrayList<>();
        List<Equipment> equipList = new ArrayList<>();
        List<Recipe> recipeList = new ArrayList<>();
        int time = 0;
        Restaurant myRestaurant = new Restaurant(foodList, equipList, recipeList,
                budget, popularity, time);
        List<Food> cookedFoodList = new ArrayList<>();
        cookedFoodList.add(new Food("pizza", 5.25));
        myRestaurant.setMenuList(cookedFoodList);

        assertEquals("You have removed pizza from the Menu" ,
                MenuMethods.removeDish(myRestaurant, "pizza"));

    }

    @Test
    public void addDishTest() {
        double budget = 100.00;
        int popularity = 0;
        List<Food> foodList = new ArrayList<>();
        List<Equipment> equipList = new ArrayList<>();
        List<Recipe> recipeList = new ArrayList<>();
        int time = 0;
        Restaurant myRestaurant = new Restaurant(foodList, equipList, recipeList,
                budget, popularity, time);
        List<Food> cookedFoodList = new ArrayList<>();
        cookedFoodList.add(new Food("pizza", 5.25));
        myRestaurant.setCookedFoodList(cookedFoodList);

        assertEquals("You have added pizza to the Menu" ,
                MenuMethods.addDish(myRestaurant, "pizza"));
    }
}