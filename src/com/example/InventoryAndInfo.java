package com.example;

import com.sun.org.apache.regexp.internal.RE;

import java.util.ArrayList;
import java.util.List;

public class InventoryAndInfo {
    private static final String FOOD = "food";
    private static final String EQUIPMENT = "equipment";
    private static final String RECIPE = "recipe";

    /**
     *
     * @param restaurant
     * @param type
     * @return
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
        }
        return String.format("%s is not a valid type", type);
    }

    public static String printInfo(Restaurant restaurant, String item) {
        if (getFood(restaurant, item) != null) {
            return FOOD;
        } else if (getEquipment(restaurant, item) != null) {
            return EQUIPMENT;
        } else if (getRecipe(restaurant, item) != null) {
            return RECIPE;
        }
        return String.format("Restaurant does not have %s", item);
    }

//    public static boolean isItemInRestaurant(Restaurant restaurant, String checkItem) {
//        if (!restaurant.getFoodInventory().isEmpty()) {
//            for (int fIndex = 0; fIndex < restaurant.getFoodInventory().size(); fIndex++) {
//                if (restaurant.getFoodInventory().get(fIndex).getName().equalsIgnoreCase(checkItem)) {
//                    return true;
//                }
//            }
//        } else if (!restaurant.getEquipInventory().isEmpty()) {
//            for (int eIndex = 0; eIndex < restaurant.getEquipInventory().size(); eIndex++) {
//                if (restaurant.getEquipInventory().get(eIndex).getName().equalsIgnoreCase(checkItem)) {
//                    return true;
//                }
//            }
//        } else if (!restaurant.getRecipeInventory().isEmpty()) {
//            for (int rIndex = 0; rIndex < restaurant.getRecipeInventory().size(); rIndex++) {
//                if (restaurant.getRecipeInventory().get(rIndex).getName().equalsIgnoreCase(checkItem)) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }

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
