package com.example;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private double wealth;
    private int popularity;
    private int time;
    private List<Food> foodInventory = new ArrayList<>();
    private List<Equipment> equipInventory = new ArrayList<>();
    private List<Recipe> recipeInventory = new ArrayList<>();

    public Restaurant(List<Food> food, List<Equipment> equipment, List<Recipe> recipe,
                      double wealth, int popularity, int time) {
        this.foodInventory = food;
        this.equipInventory = equipment;
        this.recipeInventory = recipe;
        this.wealth = wealth;
        this.popularity = popularity;
        this.time = time;
    }

    public List<Food> getFoodInventory() {
        return foodInventory;
    }

    public void setFoodInventory(List<Food> foodInventory) {
        this.foodInventory = foodInventory;
    }

    public List<Equipment> getEquipInventory() {
        return equipInventory;
    }

    public void setEquipInventory(List<Equipment> equipInventory) {
        this.equipInventory = equipInventory;
    }

    public List<Recipe> getRecipeInventory() {
        return recipeInventory;
    }

    public void setRecipeInventory(List<Recipe> recipeInventory) {
        this.recipeInventory = recipeInventory;
    }

    public double getWealth() {
        return wealth;
    }

    public void setWealth(double wealth) {
        this.wealth = wealth;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String realTime() {
        String amPm = "am";
        int hour = this.getTime() / 60;
        int min = this.getTime() % 60;
        int realHour = 6 + hour;
        if (realHour >= 12) {
            amPm = "pm";
        }
        return String.format("%d:%d %s", hour, min, amPm);
    }
}
