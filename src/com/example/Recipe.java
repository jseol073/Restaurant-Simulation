package com.example;

import java.util.Arrays;

public class Recipe extends Product{
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

    @Override
    public String toString() {
        return "Recipe{" +
                "name='" + name + '\'' +
                ", food=" + Arrays.toString(food) +
                ", equipment=" + Arrays.toString(equipment) +
                ", foodPrice=" + foodPrice +
                ", recipePrice=" + recipePrice +
                ", time=" + time +
                '}';
    }
}
