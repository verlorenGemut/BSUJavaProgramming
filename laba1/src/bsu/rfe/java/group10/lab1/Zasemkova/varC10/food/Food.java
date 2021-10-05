package bsu.rfe.java.group10.lab1.Zasemkova.varC10.food;

import bsu.rfe.java.group10.lab1.Zasemkova.varC10.properties.Consumable;
import bsu.rfe.java.group10.lab1.Zasemkova.varC10.properties.Nutritious;

public abstract class Food implements Nutritious, Consumable {
    private String name = null;
    private int calories = 0;

    public Food(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public boolean equals(Object object) {
        if (!(object instanceof Food)) {
            return false;
        }
        if (name == null || ((Food) object).name == null) {
            return false;
        }
        return name.equals(((Food) object).name) && calories == ((Food) object).calories;
    }
}