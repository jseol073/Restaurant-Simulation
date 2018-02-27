package com.example;

import java.util.ArrayList;
import java.util.List;

public class MenuMethods {
    private static final double MENU_SELLING_CONSTANT = 1.5;

    /**
     *
     * @param restaurant
     * @param menuOptions
     * @return
     */
    public static String handleMenuOptions(Restaurant restaurant, String menuOptions) {
        String[] menuOptionsArr = menuOptions.split("\\s+" ,2);
        if (menuOptionsArr.length == 1) {
            if (menuOptionsArr[0].equalsIgnoreCase("list")) {
                return listMenu(restaurant);
            }
        } else if (menuOptionsArr.length == 2) {
            if (menuOptionsArr[0].equalsIgnoreCase("add")) {
                return handleAddMenu(restaurant, menuOptionsArr[1]);
            } else if (menuOptionsArr[0].equalsIgnoreCase("remove")) {
                return handleRemoveMenu(restaurant, menuOptionsArr[1]);
            }
        }
        return "";
    }

    /**
     *
     * @param restaurant
     * @param dishToRemove
     * @return
     */
    public static String handleRemoveMenu(Restaurant restaurant, String dishToRemove) {
        String[] dishNameSplit = dishToRemove.split("\\s+");
        if (dishNameSplit.length == 1) {
            return removeDish(restaurant, dishToRemove);
        } else if (dishNameSplit.length == 2) {
            return removeDish(restaurant, dishNameSplit[0] + " " + dishNameSplit[1]);
        }
        return String.format("%s is not a valid dish", dishToRemove);
    }

    /**
     *
     * @param restaurant
     * @param dishToRemove
     * @return
     */
    public static String removeDish(Restaurant restaurant, String dishToRemove) {
        List<Food> currMenuList = restaurant.getMenuList();
        for (int i = 0; i < currMenuList.size(); i++) {
            if (currMenuList.get(i).getName().equals(dishToRemove)) {
                List<Food> tempMenu = restaurant.getMenuList();
                tempMenu.remove(currMenuList.get(i));
                restaurant.setMenuList(tempMenu);
                return String.format("You have removed %s from the Menu", dishToRemove);
            }
        }
        return String.format("%s is not a valid dish to remove", dishToRemove);
    }

    /**
     *
     * @param restaurant
     * @param dishToAdd
     * @return
     */
    public static String addDish(Restaurant restaurant, String dishToAdd) {
        List<Food> cookedFoodList = restaurant.getCookedFoodList();
        for (int i = 0; i < cookedFoodList.size(); i++) {
            if (cookedFoodList.get(i).getName().equals(dishToAdd)) {
                List<Food> tempMenu = restaurant.getMenuList();
                tempMenu.add(cookedFoodList.get(i));
                restaurant.setMenuList(tempMenu);

                //removing this dish from cookedFoodList
                List<Food> newDishList = restaurant.getCookedFoodList();
                newDishList.remove(cookedFoodList.get(i));
                restaurant.setCookedFoodList(newDishList);

                return String.format("You have added %s to the Menu", dishToAdd);
            }
        }
        return String.format("%s is not a valid dish to add", dishToAdd);
    }

    /**
     *
     * @param restaurant
     * @param dishToAdd
     * @return
     */
    public static String handleAddMenu(Restaurant restaurant, String dishToAdd) {
        String[] dishNameSplit = dishToAdd.split("\\s+");
        if (dishNameSplit.length == 1) {
            return addDish(restaurant, dishToAdd);
        } else if (dishNameSplit.length == 2) {
            return addDish(restaurant, dishNameSplit[0] + " " + dishNameSplit[1]);
        }
        return String.format("%s is not a valid dish", dishToAdd);
    }

    /**
     *
     * @param restaurant
     * @return
     */
    public static String listMenu(Restaurant restaurant) {
        if (restaurant.getMenuList().isEmpty()) {
            return "You don't have anything in your menu";
        }
        return restaurant.getMenuList().toString();
    }

    /**
     * Helper method for CookMethods.cookFood
     * Makes a food object from a recipe object
     * Only called when a food has been cooked from a recipe
     * @param recipe, a valid recipe object
     * @return
     */
    public static Food recipeToFood(Recipe recipe) {
        String name = recipe.getName();
        Food[] ingredients = recipe.getFood();
        double realPrice = 0;
        for (int i = 0; i < ingredients.length; i++) {
            realPrice += ingredients[i].getPrice();
        }
        double sellingPrice = MENU_SELLING_CONSTANT * realPrice;
        System.out.println(realPrice);
        System.out.println(sellingPrice);
        return new Food(name, sellingPrice);
    }
}
