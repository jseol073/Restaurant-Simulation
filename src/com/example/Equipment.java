package com.example;

public class Equipment {
    private String name;
    private int value;

    public Equipment(){
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
