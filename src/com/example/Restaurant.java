package com.example;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private double wealth;
    private int popularity;
    private int time;
    private int day = 1;
    private List<Food> foodInventory;
    private List<Equipment> equipInventory;
    private List<Recipe> recipeInventory;
    private List<Food> entreeList = new ArrayList<>();
    private List<Food> menuList = new ArrayList<>();
    private static final int MIN_IN_DAY = 1440;

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

    public List<Food> getEntreeList() {
        return entreeList;
    }

    public void setEntreeList(List<Food> entreeList) {
        this.entreeList = entreeList;
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

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    /**
     * Creates a military style clock
     * @return a String in the format "hour:minutes Day: x"
     */
    public String realTime() {
        StringBuilder realTime = new StringBuilder();
        if (this.newDay()) {
            this.newDay();
        }
        int hour = this.getTime() / 60;
        int min = this.getTime() % 60;
        int realHour = 6 + hour;
        realTime.append(String.format("%d:%d Day: %d", realHour, min, this.getDay()));

        return realTime.toString();
    }

    /**
     * Checks if time elapses into the next day which resets the time but updates the day
     * @return true if time elapsed into the next day
     */
    public boolean newDay(){
        int hour = this.getTime() / 60;
        int realHour = 6 + hour;
        if (realHour >= 24) {
            int remainingTime = this.getTime() - MIN_IN_DAY;
            this.setDay(this.getDay() + 1);
            this.setTime(remainingTime);
            chargeEquipment();
            return true;
        }
        return false;
    }

    /**
     * After each day, the wealth will be deducted by the fees of the equipmemt
     * using a loop to get the totalFees and subtract that from restaurant's current wealth
     */
    public void chargeEquipment() {
        double totalFees = 0;
        if (!this.getEquipInventory().isEmpty()) {
            double currWealth = this.getWealth();
            for (Equipment e : this.getEquipInventory()) {
                totalFees += e.getValue();
            }
            if (currWealth - totalFees <= 0) {
                List<Equipment> emptyList = new ArrayList<>();
                this.setEquipInventory(emptyList);
                System.out.println("Equipments are gone because you don't have any money");
            } else {
                this.setWealth(currWealth - totalFees);
            }
        }
    }
}
