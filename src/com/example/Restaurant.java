package com.example;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private double wealth;
    private int popularity;
    private int time;
    private int day;
    private List<Food> foodInventory = new ArrayList<>();
    private List<Equipment> equipInventory = new ArrayList<>();
    private List<Recipe> recipeInventory = new ArrayList<>();
    private List<Food> cookedFoodList = new ArrayList<>();
    private List<Food> menuList = new ArrayList<>();

    public Restaurant(List<Food> food, List<Equipment> equipment, List<Recipe> recipe,
                      double wealth, int popularity, int time) {
        this.foodInventory = food;
        this.equipInventory = equipment;
        this.recipeInventory = recipe;
        this.wealth = wealth;
        this.popularity = popularity;
        this.time = time;
    }

    public List<Food> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Food> menuList) {
        this.menuList = menuList;
    }

    public List<Food> getCookedFoodList() {
        return cookedFoodList;
    }

    public void setCookedFoodList(List<Food> cookedFoodList) {
        this.cookedFoodList = cookedFoodList;
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
        int hour = this.getTime() / 60;
        int min = this.getTime() % 60;
        int realHour = 6 + hour;
        return String.format("%d:%d", hour, min);
    }
}
