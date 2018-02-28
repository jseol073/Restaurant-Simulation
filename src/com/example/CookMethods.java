package com.example;

import java.util.ArrayList;
import java.util.List;

public class CookMethods {
    private static List<Food> dishList = new ArrayList<>();

    /**
     * handles the user's input on what to cook
     * @param restaurant
     * @param foodToCook, string that is supposed to be the name of a valid entree
     * @return calls helper method cookFood. Otherwise an error message
     */
    public static String handleCook(Restaurant restaurant, String foodToCook) {
        String[] foodAndQuantArr = foodToCook.split("\\s+");
        if (foodAndQuantArr.length == 1){
            return cookFood(restaurant, foodAndQuantArr[0]);
        } else if (foodAndQuantArr.length == 2) {
            String twoWordedItem = foodAndQuantArr[0] + " " + foodAndQuantArr[1];
            return cookFood(restaurant, twoWordedItem);
        }
        return "I don't understand";
    }

    /**
     * Takes recipeName and makes a Food object out of it
     * @param restaurant
     * @param recipeName, a String of the food to cook which is basically the name of one of the recipes
     * @return A string that announces the name of the food object that has been cooked
     */
    public static String cookFood(Restaurant restaurant, String recipeName) {
        if (InventoryAndInfo.getRecipe(restaurant, recipeName) != null) {
            Recipe currRecipe = InventoryAndInfo.getRecipe(restaurant, recipeName);
            if (!restaurant.getFoodInventory().isEmpty() && !restaurant.getEquipInventory().isEmpty()) {
                if (hasIngredients(restaurant, currRecipe) && checkEquipments(restaurant, currRecipe)) {
                    List<Food> newFoodInventory = removeIngredients(restaurant, currRecipe);
                    restaurant.setFoodInventory(newFoodInventory);
                    cookTime(restaurant, currRecipe);
                    Food cookedFood = MenuMethods.recipeToFood(currRecipe);
                    dishList.add(cookedFood);
                    restaurant.setEntreeList(dishList);
                    return String.format("You made %s", currRecipe.getName());
                   }
            } else {
                return "You have no food or equipments to cook with.";
            }
        }
        return "That recipe cannot be found.";
    }

    /**
     * updates the time after cooking
     * @param restaurant
     * @param recipe, recipe object of that represents the food that has been cooked
     */
    public static void cookTime(Restaurant restaurant, Recipe recipe) {
        int currTime = restaurant.getTime();
        restaurant.setTime((currTime + recipe.getTime()));
    }

    /**
     * Removes the ingredients (food objects) of the recipe from restaurant's food inventory
     * @param restaurant
     * @param recipe, recipe object that represents the food that will be cooked
     * @return an arraylist of food objects after the ingredients have been removed
     */
    public static List<Food> removeIngredients(Restaurant restaurant, Recipe recipe) {
        Food[] ingredients = recipe.getFood();
        List<Food> currFoodInventory = restaurant.getFoodInventory();
        for (int ingIndex = 0; ingIndex < ingredients.length; ingIndex++) {
            for (int fIndex = 0; fIndex < currFoodInventory.size(); fIndex++) {
                if (ingredients[ingIndex].equals(currFoodInventory.get(fIndex))) {
                    currFoodInventory.remove(currFoodInventory.get(fIndex));
                    break;
                }
            }
        }
        return currFoodInventory;
    }

    /**
     * checks to see if restaurant has the ingredients of the recipe
     * that is used to make an entree out of by comparing if the currFoodInventory will
     * be different after the ingredients are taken out of it
     * @param restaurant
     * @param recipe, recipe object that represents the food that will be cooked
     * @return true if currFoodInventory changed. False otherwise
     */
    public static boolean hasIngredients(Restaurant restaurant, Recipe recipe) {
        Food[] ingredients = recipe.getFood();
        List<Food> currFoodInventory = restaurant.getFoodInventory();
        List<Food> oldFoodInventory = new ArrayList<>(currFoodInventory);
        for (int ingIndex = 0; ingIndex < ingredients.length; ingIndex++) {
            for (int fIndex = 0; fIndex < currFoodInventory.size(); fIndex++) {
                if (ingredients[ingIndex].equals(currFoodInventory.get(fIndex))) {
                    currFoodInventory.remove(currFoodInventory.get(fIndex));
                    break;
                }
            }
        }
        if (currFoodInventory.size() != oldFoodInventory.size()) {
            return true;
        }
        return false;
    }

    /**
     * Checking if restaurant has the equipments of the recipe
     * @param restaurant
     * @param recipe,
     * @return true if restaurant does have all the required equipments
     */
    public static boolean checkEquipments(Restaurant restaurant, Recipe recipe) {
        Equipment recipeEquipment = recipe.getEquipment()[0];
        List<Equipment> restEquipments = restaurant.getEquipInventory();
        for (Equipment equipment : restEquipments) {
            if (equipment.equals(recipeEquipment)) {
                return true;
            }
        }
        return false;
    }
}
