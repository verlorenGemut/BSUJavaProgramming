package bsu.rfe.java.group10.lab1.Zasemkova.varC10.food;

public class Cheese extends Food {
    public Cheese() {
        super("Cheese");

        super.setCalories(60);
    }

    @Override
    public void calculateCalories() {
        System.out.println(this + " have " + super.getCalories() + "kcal");
    }

    @Override
    public void consume() {
        System.out.println(this + " was eaten");
    }
}
