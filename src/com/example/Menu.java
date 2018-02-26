package com.example;

import java.util.Arrays;
import java.util.List;

public class Menu {
    private List<Food> food;

    public Menu(List<Food> f) {
        this.food = f;
    }

    public List<Food> getFood() {
        return food;
    }

    public void setFood(List<Food> food) {
        this.food = food;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "food=" + food.toString() +
                '}';
    }
}
