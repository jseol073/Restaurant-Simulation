package com.example;

import java.util.ArrayList;
import java.util.List;

public class CookMethods {

    /**
     *
     * @param restaurant
     * @param foodAndQuantity
     * @return
     */
    public static String handleCook(Restaurant restaurant, String foodAndQuantity) {
        String[] foodAndQuantArr = foodAndQuantity.split("\\s+");
        if (foodAndQuantArr[foodAndQuantArr.length - 1].matches(".*\\d+.*")) {
            int quantity = Integer.parseInt(foodAndQuantArr[foodAndQuantArr.length - 1]);
            if (quantity >= 1) {

            } else {
                return "Quantity must be greater than 1";
            }
        } else {
            if (foodAndQuantArr.length == 1){
                return cookFood(restaurant, foodAndQuantArr[0], 1);
            } else if (foodAndQuantArr.length == 2) {
                String twoWordedItem = foodAndQuantArr[0] + " " + foodAndQuantArr[1];
                return cookFood(restaurant, twoWordedItem, 1);
            }
        }
        return "";
    }

    /**
     * stuck on quantity and foodInventory with several of the same elements
     * @param restaurant
     * @param recipeName
     * @param quantity
     * @return
     */
    public static String cookFood(Restaurant restaurant, String recipeName, int quantity) {
        if (InventoryAndInfo.getRecipe(restaurant, recipeName) != null) {
            Recipe currRecipe = InventoryAndInfo.getRecipe(restaurant, recipeName);

            if (!restaurant.getFoodInventory().isEmpty() && !restaurant.getEquipInventory().isEmpty()) {
                if (quantity == 1) {

                   List<Food> currFoodInventory = restaurant.getFoodInventory();

                   if ((currFoodInventory.size() != (removeIngredients(restaurant, currRecipe).size()))
                           && checkEquipments(restaurant, currRecipe)) {

                       List<Food> newFoodInventory = removeIngredients(restaurant, currRecipe);
                       restaurant.setFoodInventory(newFoodInventory);


                       Food cookedFood = MenuMethods.recipeToFood(currRecipe);
                       List<Food> cookedFoodList = new ArrayList<>();
                       cookedFoodList.add(cookedFood);
                       restaurant.setCookedFoodList(cookedFoodList);


                       return String.format("You made %s", currRecipe.getName());
                   }
                } else {
                    return "food inventory issue";
                }
            } else {
                return "You have no food or equipments to cook with.";
            }
        }
        return "That recipe cannot be found.";
    }

//    public static Recipe findRecipe(Restaurant restaurant, String recipeName) {
//        if (!restaurant.getRecipeInventory().isEmpty()) {
//            List<Recipe> recipeList = restaurant.getRecipeInventory();
//            for (int i = 0; i < recipeList.size(); i++) {
//                if (recipeList.get(i).getName().equalsIgnoreCase(recipeName)) {
//                    return recipeList.get(i);
//                }
//            }
//        }
//        return null;
//    }

//    public static boolean canCook(Restaurant restaurant, Recipe recipe) {
//        Food[] ingredients = recipe.getFood();
//        Equipment[] equipmentArr = recipe.getEquipment();
//
//        return false;
//    }

    /**
     * buggy method when there are alot of the same food elements
     * @param restaurant
     * @param recipe
     * @return
     */
    public static List<Food> removeIngredients(Restaurant restaurant, Recipe recipe) {
        Food[] ingredients = recipe.getFood();
        List<Food> currFoodInventory = restaurant.getFoodInventory();
        List<Food> newFoodInventory = currFoodInventory;
        for (int ingIndex = 0; ingIndex < ingredients.length; ingIndex++) {
            for (int fIndex = ingIndex; fIndex < currFoodInventory.size(); fIndex++) {
                if (ingredients[ingIndex].equals(currFoodInventory.get(fIndex))) {
                    newFoodInventory.remove(currFoodInventory.get(fIndex));
                }
            }
        }
        return newFoodInventory;
    }

    /**
     *
     * @param restaurant
     * @param recipe
     * @return
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
