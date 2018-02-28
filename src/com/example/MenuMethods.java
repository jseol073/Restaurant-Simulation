package com.example;

import java.util.List;

public class MenuMethods {
    private static final double MENU_SELLING_CONSTANT = 1.5;
    private static final String INVALID_INPUT = "I don't understand";

    /**
     * When the user's first word is menu and then this method handles the words after that
     * @param restaurant
     * @param menuOptions, should be "list", "add", or "remove"
     * @return calls helper method (listMenu, handleAddMenu, handleRemoveMenu) if input is valid
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
        return INVALID_INPUT;
    }

    /**
     * checks if entreeToRemove is one word or two words,
     * @param restaurant
     * @param entreeToRemove, possibly name of a cooked food object
     * @return calls helper method (removeEntree)
     */
    public static String handleRemoveMenu(Restaurant restaurant, String entreeToRemove) {
        String[] dishNameSplit = entreeToRemove.split("\\s+");
        if (dishNameSplit.length == 1) {
            return removeEntree(restaurant, dishNameSplit[0]);
        } else if (dishNameSplit.length == 2) {
            return removeEntree(restaurant, dishNameSplit[0] + " " + dishNameSplit[1]);
        }
        return String.format("%s is not a valid dish", entreeToRemove);
    }

    /**
     * Completes the action of removing a food object that is cooked (entree)
     * @param restaurant
     * @param entreeToRemove, name of the entree to remove from menuList
     * @return a string that announces the entree user removed
     */
    public static String removeEntree(Restaurant restaurant, String entreeToRemove) {
        List<Food> currMenuList = restaurant.getMenuList();
        for (int i = 0; i < currMenuList.size(); i++) {
            if (currMenuList.get(i).getName().equals(entreeToRemove)) {
                List<Food> tempMenu = restaurant.getMenuList();
                tempMenu.remove(currMenuList.get(i));
                restaurant.setMenuList(tempMenu);
                return String.format("You have removed %s from the Menu", entreeToRemove);
            }
        }
        return String.format("%s is not a valid dish to remove", entreeToRemove);
    }

    /**
     * Completes the action of adding a food object that is cooked (entree)
     * @param restaurant
     * @param entreeToAdd, possibly the name of an cooked food object
     * @return a string that announces the entree user removed
     */
    public static String addEntree(Restaurant restaurant, String entreeToAdd) {
        List<Food> entreeList = restaurant.getEntreeList();
        for (int i = 0; i < entreeList.size(); i++) {
            if (entreeList.get(i).getName().equals(entreeToAdd)) {
                List<Food> tempMenu = restaurant.getMenuList();
                tempMenu.add(entreeList.get(i));
                restaurant.setMenuList(tempMenu);

                //removing this dish from entreeList
                List<Food> newDishList = restaurant.getEntreeList();
                newDishList.remove(entreeList.get(i));
                restaurant.setEntreeList(newDishList);

                return String.format("You have added %s to the Menu", entreeToAdd);
            }
        }
        return String.format("%s is not a valid dish to add", entreeToAdd);
    }

    /**
     * checks if entreeToAdd is one word or two words,
     * @param restaurant
     * @param entreeToAdd, string that may be the name of a valid food object to add to the menu
     * @return calls helper method, addEntree
     */
    public static String handleAddMenu(Restaurant restaurant, String entreeToAdd) {
        String[] dishNameSplit = entreeToAdd.split("\\s+");
        if (dishNameSplit.length == 1) {
            return addEntree(restaurant, entreeToAdd);
        } else if (dishNameSplit.length == 2) {
            return addEntree(restaurant, dishNameSplit[0] + " " + dishNameSplit[1]);
        }
        return String.format("%s is not a valid dish", entreeToAdd);
    }

    /**
     *
     * @param restaurant
     * @return list of a food object that is in restaurant.getMenuList()
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
     * @return a food object with a new selling price
     */
    public static Food recipeToFood(Recipe recipe) {
        String name = recipe.getName();
        Food[] ingredients = recipe.getFood();
        double realPrice = 0;
        for (int i = 0; i < ingredients.length; i++) {
            realPrice += ingredients[i].getPrice(); //sum of the price of all the ingredients
        }
        double sellingPrice = MENU_SELLING_CONSTANT * realPrice;
        return new Food(name, sellingPrice);
    }
}
