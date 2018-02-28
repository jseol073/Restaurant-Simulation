package com.example;

import java.util.ArrayList;
import java.util.List;

public class InventoryAndInfo {
    private static final String FOOD = "food";
    private static final String EQUIPMENT = "equipment";
    private static final String RECIPE = "recipe";

    /**
     * Uses restaurant object to prints the names each type of inventory by using an ArrayList
     * @param restaurant
     * @param type, refers to type of item (food, equipment, or recipe)
     * @return String of the names of whichever type
     */
    public static String printInventory(Restaurant restaurant, String type) {
        List<String> names = new ArrayList<>();
        if (type.equalsIgnoreCase(FOOD)) {
            if (!restaurant.getFoodInventory().isEmpty()) {
                for (int fIndex = 0; fIndex < restaurant.getFoodInventory().size(); fIndex++) {
                    names.add(restaurant.getFoodInventory().get(fIndex).getName());
                }
                return names.toString();
            } else {
                return "Food inventory is empty";
            }

        } else if (type.equalsIgnoreCase(EQUIPMENT)) {
            if (!restaurant.getEquipInventory().isEmpty()) {
                for (int eIndex = 0; eIndex < restaurant.getEquipInventory().size(); eIndex++) {
                    names.add(restaurant.getEquipInventory().get(eIndex).getName());
                }
                return names.toString();
            } else {
                return "Equipment inventory is empty";
            }

        } else if (type.equalsIgnoreCase(RECIPE)) {
            if (!restaurant.getRecipeInventory().isEmpty()) {
                for (int rIndex = 0; rIndex < restaurant.getRecipeInventory().size(); rIndex++) {
                    names.add(restaurant.getRecipeInventory().get(rIndex).getName());
                }
                return names.toString();
            } else {
                return "Recipe inventory is empty";
            }
        } else if (type.equalsIgnoreCase("dishes")) {
            if (!restaurant.getEntreeList().isEmpty()) {
                return restaurant.getEntreeList().toString();
            } else {
                return "Dishes inventory is empty";
            }
        }
        return String.format("%s is not a valid type", type);
    }

    /**
     * Prints the type of the item
     * @param restaurant
     * @param item, possibly a name of a valid item type
     * @return string which represents the type (food, recipe, equipment)
     */
    public static String printInfo(Restaurant restaurant, String item) {
        if (getFood(restaurant, item) != null) {
            return FOOD;
        } else if (getEquipment(restaurant, item) != null) {
            return EQUIPMENT;
        } else if (getRecipe(restaurant, item) != null) {
            return RECIPE;
        }
        return String.format("Restaurant does not have '%s'", item);
    }

    /**
     * takes a string and checks if it is in the Restaurant object
     * if it is, returns that food object
     * @param restaurant
     * @param checkItem, could be the name of a Food object
     * @return a Food object that matches the food name. Returns null if it does not
     */
    public static Food getFood(Restaurant restaurant, String checkItem) {
        if (!restaurant.getFoodInventory().isEmpty()) {
            List<Food> foodList = restaurant.getFoodInventory();
            for (int i = 0; i < foodList.size(); i++) {
                if (foodList.get(i).getName().equalsIgnoreCase(checkItem)) {
                    return foodList.get(i);
                }
            }
        }
        return null;
    }

    /**
     * takes a string and checks if it is in the Restaurant object
     * if it is, returns that equipment object
     * @param restaurant
     * @param checkItem, could be the name of an Equipment object
     * @return a equipment object that matches the equipment name. Returns null if it does not
     */
    public static Equipment getEquipment(Restaurant restaurant, String checkItem) {
        if (!restaurant.getEquipInventory().isEmpty()) {
            List<Equipment> equipList = restaurant.getEquipInventory();
            for (int i = 0; i < equipList.size(); i++) {
                if (equipList.get(i).getName().equalsIgnoreCase(checkItem)) {
                    return equipList.get(i);
                }
            }
        }
        return null;
    }

    /**
     * takes a string and checks if it is in the Restaurant object
     * if it is, returns that Recipe object
     * @param restaurant
     * @param checkItem, could be the name of an Recipe object
     * @return a equipment object that matches the Recipe name. Returns null if it does not
     */
    public static Recipe getRecipe(Restaurant restaurant, String checkItem) {
        if (!restaurant.getRecipeInventory().isEmpty()) {
            List<Recipe> recipeList = restaurant.getRecipeInventory();
            for (int i = 0; i < recipeList.size(); i++) {
                if (recipeList.get(i).getName().equalsIgnoreCase(checkItem)) {
                    return recipeList.get(i);
                }
            }
        }
        return null;
    }
}
