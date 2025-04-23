package org.example;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<String> appetizers;
    private List<String> mainDishes;
    private List<String> desserts;

    public Menu() {
        appetizers = new ArrayList<>();
        mainDishes = new ArrayList<>();
        desserts = new ArrayList<>();
    }

    public void addAppetizer(String item) {
        appetizers.add(item);
    }

    public void addMainDish(String item) {
        mainDishes.add(item);
    }

    public void addDessert(String item) {
        desserts.add(item);
    }

    public List<String> getAppetizers() {
        return appetizers;
    }

    public List<String> getMainDishes() {
        return mainDishes;
    }

    public List<String> getDesserts() {
        return desserts;
    }

//makes the strings to ha hashmap
    public String toString() {
        return "Appetizers: " + appetizers +
                "Main Dishes: " + mainDishes +
                "Desserts: " + desserts;
    }
}
