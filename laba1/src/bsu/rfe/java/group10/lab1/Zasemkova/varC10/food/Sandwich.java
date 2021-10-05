package bsu.rfe.java.group10.lab1.Zasemkova.varC10.food;

public class Sandwich extends Food {

    private String filling1;
    private String filling2;

    public Sandwich(String filling1, String filling2) {
        super("Sandwich");
        this.filling1 = filling1;
        this.filling2 = filling2;

        if(filling1.equals("banana") || filling2.equals("banana")) {
            super.setCalories(super.getCalories() + 100);
        }
        if(filling1.equals("ham") || filling2.equals("ham")) {
            super.setCalories(super.getCalories() + 80);
        }
        if(filling1.equals("cheese") || filling2.equals("cheese")) {
            super.setCalories(super.getCalories() + 30);
        }
        if(filling1.equals("chocolate") || filling2.equals("chocolate")) {
            super.setCalories(super.getCalories() + 200);
        }
    }

    @Override
    public void calculateCalories() {
        System.out.println(this + " have " + super.getCalories() + "kcal");
    }

    @Override
    public void consume() {
        System.out.println(this + " was eaten");
    }

    public String getFilling1() {
        return filling1;
    }

    public void setFilling1(String filling1) {
        this.filling1 = filling1;
    }

    public String getFilling2() {
        return filling2;
    }

    public void setFilling2(String filling2) {
        this.filling2 = filling2;
    }

    public String toString() {
        return super.toString() + " with " + filling1.toUpperCase() + " & " + filling2.toUpperCase();
    }

    public boolean equals(Object object) {
        if (super.equals(object)) {
            if (!(object instanceof Sandwich)) {
                return false;
            }
            return filling1.equals(((Sandwich) object).filling1) && filling2.equals(((Sandwich) object).filling2) ||
                    filling2.equals(((Sandwich) object).filling1) && filling1.equals(((Sandwich) object).filling2);
        } else {
            return false;
        }
    }
}
