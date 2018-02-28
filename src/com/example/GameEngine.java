package com.example;

import com.google.gson.Gson;

import java.util.*;

public class GameEngine {
    public static boolean isMarket = false;
    private static final String WEALTH = "wealth";
    private static final String POP_LEVEL = "Popularity is now level:";
    private double budget = 200.00;
    private int popularity = 0;
    private List<Food> foodList = new ArrayList<>();
    private List<Equipment> equipList = new ArrayList<>();
    private List<Recipe> recipeList = new ArrayList<>();
    private int time = 0;
    private Restaurant myRestaurant = new Restaurant(foodList, equipList, recipeList,
            budget, popularity, time);

    public GameEngine() {
    }

    /**
     * This runs the simulation by using a while loop
     * Uses public boolean variable, isMarket, to allow users to enter/exit the market
     */
    public void runGame() {
        String jsonContent = Simulation.getFileContentsAsString("Market.json");
        Gson gson = new Gson();
        Market market = gson.fromJson(jsonContent, Market.class);
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()) {
            if (isMarket) {
                String userInput = sc.nextLine();
                System.out.println(handleUserInputMkt(market, myRestaurant, userInput));
            } else {
                String userInput = sc.nextLine();
                System.out.println(handleUserInput(userInput));
            }
            System.out.println(setPopularity(myRestaurant));
        }
    }

    /**
     * Handles the user's input (exit, list, buy, and sell) when the user in the market
     * @param market, a market object from the json file
     * @param restaurant, restaurant object
     * @param userInput, the input of the user
     * @return calls helper methods which returns various String outputs depending on the input of the user
     */
    public String handleUserInputMkt(Market market, Restaurant restaurant, String userInput) {
        String userInputLwr = userInput.toLowerCase();
        String userInputTrimmed = userInputLwr.trim();
        String[] userInputArr = userInputTrimmed.split("\\s+", 2);

        if (!userInputTrimmed.isEmpty()) {
            if (userInputArr.length == 1) {
                if (userInputArr[0].equalsIgnoreCase("exit")) {
                    isMarket = false;
                    int currTime = restaurant.getTime();
                    restaurant.setTime(currTime + market.getMarketTime());
                    return "You left the market.";
                }
            } else if (userInputArr.length == 2) {
                if (userInputArr[0].equalsIgnoreCase("list")) {
                    return ListAndBuyMethods.handleList(market, userInputArr[1]);

                } else if (userInputArr[0].equalsIgnoreCase("buy")) {
                    return ListAndBuyMethods.handleBuy(market, restaurant, userInputArr[1]);

                } else if (userInputArr[0].equalsIgnoreCase("sell")) {
                    return SellMethods.handleSell(market, restaurant, userInputArr[1]);
                }
            }
        }
        return String.format("I don't understand '%s'", userInputTrimmed);
    }

    /**
     * Handles the users input while not in the market such as
     * (wealth, time, pass time, inventory, info, menu, cook)
     * @param userInput
     * @return valid inputs will call helper methods while invalid ones will get an error message
     */
    public String handleUserInput(String userInput) {
        String userInputLwr = userInput.toLowerCase();
        String userInputTrimmed = userInputLwr.trim();
        String[] userInputArr = userInputTrimmed.split("\\s+", 2);

        if (!userInputTrimmed.isEmpty()) {
            if (userInputArr.length == 1) {
                if (userInputArr[0].equalsIgnoreCase(WEALTH)) {
                    return String.format("Restaurant budget: $%.2f", myRestaurant.getWealth());

                } else if (userInputArr[0].equalsIgnoreCase("time")) {
                    return myRestaurant.realTime();

                } else if (userInputArr[0].equalsIgnoreCase("market")) {
                    isMarket = true;
                    return "You are now in the market.";
                }
            } else if (userInputArr.length == 2) {
                if (userInputArr[0].equalsIgnoreCase("inventory")) {
                    return InventoryAndInfo.printInventory(myRestaurant, userInputArr[1]);

                } else if(userInputArr[0].equalsIgnoreCase("info")){
                    return InventoryAndInfo.printInfo(myRestaurant, userInputArr[1]);

                } else if(userInputArr[0].equalsIgnoreCase("menu")) {
                    return MenuMethods.handleMenuOptions(myRestaurant, userInputArr[1]);

                } else if(userInputArr[0].equalsIgnoreCase("cook")) {
                    return CookMethods.handleCook(myRestaurant, userInputArr[1]);

                } else if(userInputArr[0].equalsIgnoreCase("pass")) {
                    return TimeMethods.handleTime(myRestaurant, userInputArr[1]);
                }
            }
        } else {
            return "Empty input";
        }
        return String.format("I don't understand '%s'", userInputTrimmed);
    }

    /**
     * Sets the popularity number by checking the size of the menu list up to 3 and then checking it's variety
     * Highest popularity will go is 5
     * @param restaurant
     * @return a string that shows the current popularity level
     */
    public String setPopularity(Restaurant restaurant) {
        int popularity = restaurant.getPopularity();
        if (restaurant.getMenuList().size() == 1) {
            restaurant.setPopularity(popularity + 1);
            return String.format("%s 1", POP_LEVEL);
        } else if (restaurant.getMenuList().size() == 4) {
            restaurant.setPopularity(popularity + 2);
            return String.format("%s 2", POP_LEVEL);
        } else if (restaurant.getMenuList().size() > 4) {
            Set<Food> menuSet = new HashSet<>(restaurant.getMenuList());
            if (menuSet.size() == 5) {
                restaurant.setPopularity(3);
                return String.format("%s 3", POP_LEVEL);
            } else if (menuSet.size() == 7) {
                restaurant.setPopularity(4);
                return String.format("%s 4", POP_LEVEL);
            } else if (menuSet.size() == 10) {
                restaurant.setPopularity(5);
                return String.format("%s 5", POP_LEVEL);
            }
        }
        return "";
    }

    public void executePopularity(Restaurant restaurant) {
        int level = restaurant.getPopularity();
        if (level != 0) {
            if (level == 1) {
                sellDishes(restaurant, 1);
            } else if (level == 2) {
                sellDishes(restaurant, 3);
            } else if (level == 3) {
                sellDishes(restaurant, 5);
            } else if (level == 4) {
                sellDishes(restaurant, 7);
            } else if (level == 5) {
                sellDishes(restaurant, 10);
            }
        }
    }

    public void sellDishes(Restaurant restaurant, int sellAmount) {
        double profit = 0;
        if (restaurant.getTime() > 0 && restaurant.getTime() < 840) {
            List<Food> currMenuList = restaurant.getMenuList();
            for (int i = 0; i < sellAmount; i++) {
                profit += currMenuList.get(0).getPrice();
                currMenuList.remove(0);
            }
            restaurant.setMenuList(currMenuList);
        }
        double currWealth = restaurant.getWealth();
        restaurant.setWealth(currWealth + profit);
    }
}
