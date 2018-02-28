package com.example;

import java.util.HashMap;
import java.util.Map;

public class ListAndBuyMethods {
    private static final String FOOD = "food";
    private static final String EQUIPMENT = "equipment";
    private static final String RECIPE = "recipe";
    private static final int DEFAULT_QUANTITY = 1;
    private static final String NO_MONEY = "You do not have enough money.";

    /**
     * Handles the user's input for list commands
     * @param market
     * @param type, a string that could match with one of the types (food, equipment, recipe)
     * @return name and price of a food, equipment, or recipe type
     */
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

        } else if (type.equalsIgnoreCase(RECIPE)) {
            for (int rIndex = 0; rIndex < market.getRecipe().length; rIndex++) {
                recipeMap.put(market.getRecipe()[rIndex].getName(),
                        String.format(" Price: $%.2f", market.getRecipe()[rIndex].getRecipePrice()));
            }
            return recipeMap.toString();
        }
        return "";
    }

    /**
     * Handles the user's command when buying an item from the market
     * @param market, the market json object
     * @param restaurant, restaurant object
     * @param itemAndQuant, string that may contain the item and how many items
     * @return calls helper methods handleBuyQuantity, buyItem,
     */
    public static String handleBuy(Market market, Restaurant restaurant, String itemAndQuant) {
        String[] itemAndQuantArr = itemAndQuant.split("\\s+");
        if (itemAndQuantArr[itemAndQuantArr.length - 1].matches(".*\\d+.*")) {
            if (itemAndQuantArr.length <= 3) {
                int quantity = Integer.parseInt(itemAndQuantArr[itemAndQuantArr.length - 1]);
                if (quantity >= 1) {
                    return handleBuyQuantity(market, restaurant, itemAndQuantArr, quantity);
                } else {
                    return "Quantity must be greater than 1.";
                }
            } else {
                return "You might be adding a word";
            }
        } else {
            if (itemAndQuantArr.length == 1) {
                return buyItem(market, itemAndQuantArr[0], restaurant, DEFAULT_QUANTITY);
            } else if (itemAndQuantArr.length == 2) {
                String twoWordedItem = itemAndQuantArr[0] + " " + itemAndQuantArr[1];
                if (isItemInMarket(market, twoWordedItem)) {
                    return buyItem(market, twoWordedItem, restaurant, DEFAULT_QUANTITY);
                } else {
                    return String.format("%s is not a valid item", twoWordedItem);
                }
            }
        }
        return "I don't understand";
    }

    /**
     * Handles buying actions when there is a quantity specified
     * @param market
     * @param restaurant
     * @param itemArr
     * @param quantity
     * @return calls buyItem method
     */
    public static String handleBuyQuantity(Market market, Restaurant restaurant,
                                           String[] itemArr, int quantity) {
        if (itemArr.length == 2) {
            return buyItem(market, itemArr[0], restaurant, quantity);
        } else if (itemArr.length == 3) { //checks for two worded items
            String twoWordedItem = itemArr[0] + " " + itemArr[1];
            if (isItemInMarket(market, twoWordedItem)) {
                return buyItem(market, twoWordedItem, restaurant, quantity);
            } else {
                return String.format("%s is not a valid item", twoWordedItem);
            }
        }
        return "";
    }

    /**
     * Completes buying actions which also deducts money from the restaurant
     * @param market
     * @param itemName, could be a food, equipment, or recipe
     * @param restaurant
     * @param quantity, quantity items
     * @return A string to show user that they bought an particular item with a quantity as specified
     */
    public static String buyItem(Market market, String itemName, Restaurant restaurant, int quantity) {
        Food foodToBuy;
        Equipment equipToBuy;
        Recipe recipeToBuy;
        double price;

        if (getFoodItem(market, itemName) != null) {
            foodToBuy = getFoodItem(market, itemName);
            price = quantity * foodToBuy.getPrice();

            if (restaurant.getWealth() - price > 0) {
                for (int qIndex = 0; qIndex < quantity; qIndex++) {
                    restaurant.getFoodInventory().add(foodToBuy);
                }
                restaurant.setWealth(restaurant.getWealth() - price);
                return String.format("You bought %d %s (food)", quantity, foodToBuy.getName());
            } else {
                return NO_MONEY;
            }

        } else if (getEquipmentItem(market, itemName) != null) {
            equipToBuy = getEquipmentItem(market, itemName);
            price = quantity * equipToBuy.getValue();

            if (restaurant.getWealth() - price > 0) {
                for (int qIndex = 0; qIndex < quantity; qIndex++) {
                    restaurant.getEquipInventory().add(equipToBuy);
                }
                restaurant.setWealth(restaurant.getWealth() - price);
                return String.format("You bought %d %s (equipment)", quantity, equipToBuy.getName());
            } else {
                return NO_MONEY;
            }

        } else if (getRecipeItem(market, itemName) != null) {
            recipeToBuy = getRecipeItem(market, itemName);
            price = quantity * recipeToBuy.getRecipePrice();

            if (restaurant.getWealth() - price > 0) {
                for (int qIndex = 0; qIndex < quantity; qIndex++) {
                    restaurant.getRecipeInventory().add(recipeToBuy);
                }
                restaurant.setWealth(restaurant.getWealth() - price);
                return String.format("You bought %d %s (recipe)", quantity, recipeToBuy.getName());
            } else {
                return NO_MONEY;
            }
        }
        return String.format("%s is not in the market", itemName);
    }

    /**
     * takes a string and checks if it is in the market json object
     * if it is, returns that food object
     * @param market
     * @param itemName, could be the name of a food object
     * @return a Food object that matches the food name. Returns null if it does not
     */
    public static Food getFoodItem(Market market, String itemName) {
        Food[] foodArr = market.getFood();
        for (int i = 0; i < foodArr.length; i++) {
            if (itemName.equalsIgnoreCase(foodArr[i].getName())) {
                return foodArr[i];
            }
        }
        return null;
    }

    /**
     * takes a string and checks if it is in the market json object
     * if it is, returns that equipment object
     * @param market
     * @param itemName, could be the name of an equipment object
     * @return a equipment object that matches the equipment name. Returns null if it does not
     */
    public static Equipment getEquipmentItem(Market market, String itemName) {
        Equipment[] equipArr = market.getEquipment();
        for (int i = 0; i < equipArr.length; i++) {
            if (itemName.equalsIgnoreCase(equipArr[i].getName())) {
                return equipArr[i];
            }
        }
        return null;
    }

    /**
     * takes a string and checks if it is in the market json object
     * if it is, returns that recipe object
     * @param market
     * @param itemName, could be the name of a recipe object
     * @return a Food object that matches the recipe name. Returns null if it does not
     */
    public static Recipe getRecipeItem(Market market, String itemName) {
        Recipe[] recipeArr = market.getRecipe();
        for (int i = 0; i < recipeArr.length; i++) {
            if (itemName.equalsIgnoreCase(recipeArr[i].getName())) {
                return recipeArr[i];
            }
        }
        return null;
    }

    /**
     * takes a string and checks if it is in the market json object
     * if it is, returns that food object
     * @param market
     * @param checkItemName, could be the name of a type of item
     * @return a Food object that matches the item name. Returns null if it is not
     */
    public static boolean isItemInMarket(Market market, String checkItemName) {
        if (getRecipeItem(market, checkItemName) != null) {
            return true;
        } else if (getEquipmentItem(market, checkItemName) != null) {
            return true;
        } else if (getFoodItem(market, checkItemName) != null) {
            return true;
        }
        return false;
    }
}
