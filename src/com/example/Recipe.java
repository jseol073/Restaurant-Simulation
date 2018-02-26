package com.example;

public class Recipe {
    private String name;
    private Food[] food;
    private Equipment[] equipment;
    private double foodPrice;
    private double recipePrice;
    private int time;

    public Recipe(){
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

    public double getFoodPrice() {
        return foodPrice;
    }

    public double getRecipePrice() {
        return recipePrice;
    }

    public int getTime() {
        return time;
    }
}
