package bsu.rfe.java.group10.lab1.Zasemkova.varC10.food;

public class Apple extends Food {
    private String size;

    public Apple(String size) {
        super("Apple");
        this.size = size;

        switch (size) {
            case "small":
                super.setCalories(super.getCalories() + 20);
                break;
            case "middle":
                super.setCalories(super.getCalories() + 30);
                break;
            case "big":
                super.setCalories(super.getCalories() + 40);
                break;
        }
    }

    @Override
    public void consume() {
        System.out.println(this + " was eaten");
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public boolean equals(Object arg0) {
        if (super.equals(arg0)) {
            if (!(arg0 instanceof Apple)) {
                return false;
            }
            return size.equals(((Apple) arg0).size);
        } else
            return false;
    }

    public String toString() {
        return super.toString() + " size " + size.toUpperCase();
    }

    @Override
    public void calculateCalories() {
        System.out.println(this + " have " + super.getCalories() + "kcal");
    }
}
