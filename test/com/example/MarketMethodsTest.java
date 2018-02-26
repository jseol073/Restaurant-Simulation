package com.example;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MarketMethodsTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void handleListEquipTest() {
        String jsonContent = Simulation.getFileContentsAsString("Products.json");
        Gson gson = new Gson();
        Products market = gson.fromJson(jsonContent, Products.class);
        String type = "equipment";
        String output = "{oven=Price per day: $20, stove=Price per day: $25, deep fryer=Price per day: $30}";
        assertEquals(output, MarketMethods.handleList(market, type));
    }

    @Test
    public void handleListFoodTest() {
        String jsonContent = Simulation.getFileContentsAsString("Products.json");
        Gson gson = new Gson();
        Products market = gson.fromJson(jsonContent, Products.class);
        String type = "food";
        String output = "{eggs=Price: $1.50, tomatoes=Price: $1.25, bread=Price: $1.00, " +
                "chicken=Price: $3.50, potatoes=Price: $1.75, onion=Price: $0.50, noodles=Price: $2.00, " +
                "lettuce=Price: $0.75, water=Price: $0.00, cheese=Price: $1.25}";
        assertEquals(output, MarketMethods.handleList(market, type));
    }

    @Test
    public void handleBuy() {
    }

    @Test
    public void buyItem() {
    }

    @Test
    public void getFoodItemTest() {
        String jsonContent = Simulation.getFileContentsAsString("Products.json");
        Gson gson = new Gson();
        Products market = gson.fromJson(jsonContent, Products.class);
        String itemName = "cheese";
        Food output = market.getFood()[4];
        assertEquals(output, MarketMethods.getFoodItem(market, itemName));
    }

    @Test
    public void getEquipmentItemTest() {
        String jsonContent = Simulation.getFileContentsAsString("Products.json");
        Gson gson = new Gson();
        Products market = gson.fromJson(jsonContent, Products.class);
        String itemName = "stove";
        Equipment output = market.getEquipment()[0];
        assertEquals(output, MarketMethods.getEquipmentItem(market, itemName));
    }

    @Test
    public void getEquipmentNullTest() {
        String jsonContent = Simulation.getFileContentsAsString("Products.json");
        Gson gson = new Gson();
        Products market = gson.fromJson(jsonContent, Products.class);
        String itemName = "fdasfsa";
        assertEquals(null, MarketMethods.getEquipmentItem(market, itemName));
    }

    @Test
    public void isItemValidTrueTest() {
        String jsonContent = Simulation.getFileContentsAsString("Products.json");
        Gson gson = new Gson();
        Products market = gson.fromJson(jsonContent, Products.class);
        String checkItemName = "grilled chicken";
        boolean output = true;
        assertEquals(output, MarketMethods.isItemInMarket(market, checkItemName));
    }

    @Test
    public void isItemValidFalseTest() {
        String jsonContent = Simulation.getFileContentsAsString("Products.json");
        Gson gson = new Gson();
        Products market = gson.fromJson(jsonContent, Products.class);
        String checkItemName = "grilledchicken";
        boolean output = false;
        assertEquals(output, MarketMethods.isItemInMarket(market, checkItemName));
    }

    @Test
    public void handleBuyQuantityTest() {
        double budget = 100.00;
        int popularity = 0;
        String jsonContent = Simulation.getFileContentsAsString("Products.json");
        Gson gson = new Gson();
        Products market = gson.fromJson(jsonContent, Products.class);
        List<Food> foodList = new ArrayList<>();
        List<Equipment> equipList = new ArrayList<>();
        List<Recipe> recipeList = new ArrayList<>();
        int time = 0;
        Restaurant myRestaurant = new Restaurant(foodList, equipList, recipeList,
                budget, popularity, time);

        String[] itemAndQuantArr = {"grilled", "chicken", "4"};
        int quantity = 4;
        String output = "You bought 4 grilled chicken";
        assertEquals(output, MarketMethods.handleBuyQuantity(market, myRestaurant,
                itemAndQuantArr, quantity));
        }
}