package com.example;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameEngine {
    public static boolean isMarket = false;
    private static final String WEALTH = "wealth";
    private double budget = 100.00;
    private int popularity = 0;
    private List<Food> foodList = new ArrayList<>();
    private List<Equipment> equipList = new ArrayList<>();
    private List<Recipe> recipeList = new ArrayList<>();
    private int time = 0;
    private Restaurant myRestaurant = new Restaurant(foodList, equipList, recipeList,
            budget, popularity, time);

    public GameEngine() {
    }

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
        }
    }

    public String handleUserInputMkt(Market market, Restaurant restaurant, String userInput) {
        String userInputLwr = userInput.toLowerCase();
        String userInputTrimmed = userInputLwr.trim();
        String[] userInputArr = userInputTrimmed.split("\\s+", 2);
        if (!userInputTrimmed.isEmpty()) {
            if (userInputArr.length == 1) {
                if (userInputArr[0].equalsIgnoreCase("exit")) {
                    isMarket = false;
                    return "You left Walmart.";
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
        return String.format("I don't understand %s", userInputTrimmed);
    }

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
                    return "You are now in Walmart.";
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
                    return TimeMethods.passTime(myRestaurant, userInputArr[1]);
                }
            }
        } else {
            return "Empty input";
        }
        return String.format("I don't understand %s", userInputTrimmed);
    }
}
