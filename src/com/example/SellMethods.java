package com.example;

public class SellMethods {
    private static final double FOOD_CONSTANT = 0.8;
    private static final double EQUIP_RECIPE_CONSTANT = 0.5;

    /**
     * Handles the user's command when selling an item from the market
     * @param market, the market json object
     * @param restaurant, restaurant object
     * @param itemAndQuant, string that may contain a type of item and how many items
     * @return calls helper methods handleSellQuantity, sellItem, and invalid input messages
     */
    public static String handleSell(Market market, Restaurant restaurant, String itemAndQuant) {
        String[] itemAndQuantArr = itemAndQuant.split("\\s+");

        //checks if last element is a number:
        if (itemAndQuantArr[itemAndQuantArr.length - 1].matches(".*\\d+.*")) {
            if (itemAndQuantArr.length <= 3) {
                int quantity = Integer.parseInt(itemAndQuantArr[itemAndQuantArr.length - 1]);
                if (quantity >= 1) {
                    return handleSellQuantity(market, restaurant, itemAndQuantArr, quantity);
                } else {
                    return "Quantity must be greater than 1.";
                }
            } else {
                return "You might be adding a word";
            }

        } else { //No quantity specified
            if (itemAndQuantArr.length == 1) {
                return sellItem(market, itemAndQuantArr[0], restaurant, 1);
            } else if (itemAndQuantArr.length == 2) {
                String twoWordedItem = itemAndQuantArr[0] + " " + itemAndQuantArr[1];
                if (ListAndBuyMethods.isItemInMarket(market, twoWordedItem)) {
                    return sellItem(market, twoWordedItem, restaurant, 1);
                } else {
                    return String.format("%s is not a valid item", twoWordedItem);
                }
            }
        }
        return "";
    }

    /**
     * Completes action of selling items which also adds money to the restaurant
     * @param market
     * @param itemName, a string that may be a name of an item
     * @param restaurant,
     * @param quantity of items to sell
     * @return a string that announces if an item is sold and for how many sold. if item does not exist or
     * quantity specified is wrong, returns a string that explains why
     */
    public static String sellItem(Market market, String itemName, Restaurant restaurant, int quantity) {
        Food foodToSell;
        Equipment equipToSell;
        Recipe recipeToSell;
        double price;

        if (ListAndBuyMethods.getFoodItem(market, itemName) != null) { //Food
            foodToSell = ListAndBuyMethods.getFoodItem(market, itemName);
            price = quantity * FOOD_CONSTANT * foodToSell.getPrice();

            if (restaurant.getFoodInventory().size() - quantity >= 0) {
                for (int qIndex = 0; qIndex < quantity; qIndex++) {
                    restaurant.getFoodInventory().remove(foodToSell);
                }
            } else {
                return String.format("No more %s left in your inventory", itemName);
            }
            restaurant.setWealth(restaurant.getWealth() + price);
            return String.format("You sold %d %s (food)", quantity, foodToSell.getName());


        } else if (ListAndBuyMethods.getEquipmentItem(market, itemName) != null) { //Equipment
            equipToSell = ListAndBuyMethods.getEquipmentItem(market, itemName);
            price = quantity * EQUIP_RECIPE_CONSTANT * equipToSell.getValue();

            if (restaurant.getEquipInventory().size() - quantity >= 0) {
                for (int qIndex = 0; qIndex < quantity; qIndex++) {
                    restaurant.getEquipInventory().remove(equipToSell);
                }
            } else {
                return String.format("No more %s left in your inventory", itemName);
            }
            restaurant.setWealth(restaurant.getWealth() + price);
            return String.format("You sold %d %s (equipment)", quantity, equipToSell.getName());

        } else if (ListAndBuyMethods.getRecipeItem(market, itemName) != null) { //Recipe
            recipeToSell = ListAndBuyMethods.getRecipeItem(market, itemName);
            price = quantity * EQUIP_RECIPE_CONSTANT * recipeToSell.getRecipePrice();

            if (restaurant.getRecipeInventory().size() - quantity >= 0) {
                for (int qIndex = 0; qIndex < quantity; qIndex++) {
                    restaurant.getRecipeInventory().remove(recipeToSell);
                }
            } else {
                return String.format("No more %s left in your inventory", itemName);
            }
            restaurant.setWealth(restaurant.getWealth() + price);
            return String.format("You sold %d %s (recipe)", quantity, recipeToSell.getName());
        }
        return String.format("%s is not in the market", itemName);
    }

    /**
     * This method is called when the item may be two worded
     * @param market
     * @param restaurant
     * @param itemNameArr, may be a name of a type of item
     * @param quantity of item specified
     * @return should call sellItem method if two worded item is valid
     */
    public static String handleSellQuantity(Market market, Restaurant restaurant,
                                            String[] itemNameArr, int quantity) {
        if (itemNameArr.length == 2) {
            return sellItem(market, itemNameArr[0], restaurant, quantity);
        } else if (itemNameArr.length == 3) {
            String twoWordedItem = itemNameArr[0] + " " + itemNameArr[1];
            if (ListAndBuyMethods.isItemInMarket(market, twoWordedItem)) {
                return sellItem(market, twoWordedItem, restaurant, quantity);
            } else {
                return String.format("%s is not a valid item", twoWordedItem);
            }
        }
        return "";
    }
}
