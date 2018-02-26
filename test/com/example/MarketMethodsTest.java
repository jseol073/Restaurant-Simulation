package com.example;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MarketMethodsTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void handleListEquipTest() {
        String jsonContent = Simulation.getFileContentsAsString("Market.json");
        Gson gson = new Gson();
        Market market = gson.fromJson(jsonContent, Market.class);
        String type = "equipment";
        String output = "{oven=Price per day: $20, stove=Price per day: $25, deep fryer=Price per day: $30}";
        assertEquals(output, MarketMethods.handleList(market, type));
    }

    @Test
    public void handleListFoodTest() {
        String jsonContent = Simulation.getFileContentsAsString("Market.json");
        Gson gson = new Gson();
        Market market = gson.fromJson(jsonContent, Market.class);
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
}