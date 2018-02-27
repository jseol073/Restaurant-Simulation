package com.example;

public class SellMethods {
    private static final double SELLING_CONSTANT = 1.5;

    /**
     *
     * @param market
     * @param restaurant
     * @param itemAndQuant
     * @return
     */
    public static String handleSell(Market market, Restaurant restaurant, String itemAndQuant) {
        String[] itemAndQuantArr = itemAndQuant.split("\\s+");
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

        } else {
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
     *
     * @param market
     * @param itemName
     * @param restaurant
     * @param quantity
     * @return
     */
    public static String sellItem(Market market, String itemName, Restaurant restaurant, int quantity) {
        Food foodToSell;
        Equipment equipToSell;
        Recipe recipeToSell;
        double price;

        if (ListAndBuyMethods.getFoodItem(market, itemName) != null) {
            foodToSell = ListAndBuyMethods.getFoodItem(market, itemName);
            price = quantity * SELLING_CONSTANT * foodToSell.getPrice();

            if (restaurant.getFoodInventory().size() - quantity >= 0) {
                for (int qIndex = 0; qIndex < quantity; qIndex++) {
                    restaurant.getFoodInventory().remove(foodToSell);
                }
            } else {
                return String.format("No more %s left in your inventory", itemName);
            }
            restaurant.setWealth(restaurant.getWealth() + price);
            return String.format("You sold %d %s (food)", quantity, foodToSell.getName());


        } else if (ListAndBuyMethods.getEquipmentItem(market, itemName) != null) {
            equipToSell = ListAndBuyMethods.getEquipmentItem(market, itemName);
            price = quantity * SELLING_CONSTANT * equipToSell.getValue();

            if (restaurant.getEquipInventory().size() - quantity >= 0) {
                for (int qIndex = 0; qIndex < quantity; qIndex++) {
                    restaurant.getEquipInventory().remove(equipToSell);
                }
            } else {
                return String.format("No more %s left in your inventory", itemName);
            }
            restaurant.setWealth(restaurant.getWealth() + price);
            return String.format("You sold %d %s (equipment)", quantity, equipToSell.getName());

        } else if (ListAndBuyMethods.getRecipeItem(market, itemName) != null) {
            recipeToSell = ListAndBuyMethods.getRecipeItem(market, itemName);
            price = quantity * recipeToSell.getRecipePrice();

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
     *
     * @param market
     * @param restaurant
     * @param itemAndQuantArr
     * @param quantity
     * @return
     */
    public static String handleSellQuantity(Market market, Restaurant restaurant,
                                            String[] itemAndQuantArr, int quantity) {
        if (itemAndQuantArr.length == 2) {
            return sellItem(market, itemAndQuantArr[0], restaurant, quantity);
        } else if (itemAndQuantArr.length == 3) {
            String twoWordedItem = itemAndQuantArr[0] + " " + itemAndQuantArr[1];
            if (ListAndBuyMethods.isItemInMarket(market, twoWordedItem)) {
                return sellItem(market, twoWordedItem, restaurant, quantity);
            } else {
                return String.format("%s is not a valid item", twoWordedItem);
            }
        }
        return "";
    }
}
