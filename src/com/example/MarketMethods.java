package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarketMethods {
    private static final String FOOD = "food";
    private static final String EQUIPMENT = "equipment";
    private static final String RECIPES = "recipes";
    private static final int DEFAULT_QUANTITY = 1;

    public static String handleList(Market market, String type) {
        Map<String, String> foodMap = new HashMap<>();
        Map<String, String> equipMap = new HashMap<>();
        Map<String, String> recipeMap = new HashMap<>();

        if (type.equalsIgnoreCase(FOOD)) {
            for (int fIndex = 0; fIndex < market.getFood().length; fIndex++) {
                foodMap.put(market.getFood()[fIndex].getName(),
                        String.format(" Price: $%.2f", market.getFood()[fIndex].getPrice()));
            }
            return foodMap.toString();

        } else if (type.equalsIgnoreCase(EQUIPMENT)) {
            for (int eIndex = 0; eIndex < market.getEquipment().length; eIndex++) {
                equipMap.put(market.getEquipment()[eIndex].getName(),
                        String.format(" Price per day: $%d", market.getEquipment()[eIndex].getValue()));
            }

            return equipMap.toString();

        } else if (type.equalsIgnoreCase(RECIPES)) {
            for (int rIndex = 0; rIndex < market.getRecipe().length; rIndex++) {
                recipeMap.put(market.getRecipe()[rIndex].getName(),
                        String.format(" Price: $%.2f", market.getRecipe()[rIndex].getRecipePrice()));
            }
            return recipeMap.toString();
        }
        return "";
    }

    public static String handleBuy(Market market, Restaurant restaurant, String itemAndQuant) {
        String[] itemAndQuantArr = itemAndQuant.split("\\s+");
        if (itemAndQuantArr[itemAndQuantArr.length - 1].matches(".*\\d+.*")) {
            int quantity = Integer.parseInt(itemAndQuantArr[itemAndQuantArr.length - 1]);
            if (quantity >= 1) {

            } else {
                return "Quantity must be greater than 1.";
            }

        } else {
            if (itemAndQuantArr.length == 1) {
                return buyItem(market, itemAndQuantArr[0], restaurant, DEFAULT_QUANTITY);
            } else if (itemAndQuantArr.length == 2) {

            }
        }
        return "";
    }

    public static String buyItem(Market market, String item, Restaurant restaurant, int quantity) {
        
        return "";
    }
}
