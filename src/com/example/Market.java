package com.example;

public class Market {
    private String name;
    private Food[] food;
    private Equipment[] equipment;
    private Recipe[] recipe;
    private int marketTime;

    public Market(){
    }

    public String getName() {
        return name;
    }

    public Food[] getFood() {
        return food;
    }

    public Equipment[] getEquipment() {
        return equipment;
    }

    public Recipe[] getRecipe() {
        return recipe;
    }

    public int getMarketTime() {
        return marketTime;
    }
}
