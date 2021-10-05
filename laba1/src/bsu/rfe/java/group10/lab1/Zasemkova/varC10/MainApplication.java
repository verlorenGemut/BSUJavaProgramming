package bsu.rfe.java.group10.lab1.Zasemkova.varC10;

import bsu.rfe.java.group10.lab1.Zasemkova.varC10.food.Apple;
import bsu.rfe.java.group10.lab1.Zasemkova.varC10.food.Cheese;
import bsu.rfe.java.group10.lab1.Zasemkova.varC10.food.Food;
import bsu.rfe.java.group10.lab1.Zasemkova.varC10.food.Sandwich;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Comparator;

public class MainApplication {

    public static void main(String[] args) throws Exception {
        Food[] breakfast = new Food[20];

        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("-")) {
                if (args[i].equals("-calories")) {
                    calculateCalories(breakfast);
                } else if (args[i].equals("-sort")) {
                    sort(breakfast);
                }
            } else {
                String[] parts = args[i].split("/");

                try {
                    Class myClass = Class.forName("bsu.rfe.java.group10.lab1.Zasemkova.varC10.food." + parts[0]);
                    if (parts.length == 1) {
                        Constructor constructor = myClass.getConstructor();
                        breakfast[i] = (Food) constructor.newInstance();
                    } else if (parts.length == 2) {
                        Constructor constructor = myClass.getConstructor(String.class);
                        breakfast[i] = (Food) constructor.newInstance(parts[1]);
                    } else if (parts.length == 3) {
                        Constructor constructor = myClass.getConstructor(String.class, String.class);
                        breakfast[i] = (Food) constructor.newInstance(parts[1], parts[2]);
                    }
                } catch (ClassNotFoundException | NoSuchMethodException e) {
                    System.out.println("Sorry, you cannot eat this on the breakfast!");
                }
            }
        }

        for (Food food : breakfast) {
            if (food != null) {
                food.consume();
            } else {
                break;
            }
        }
        System.out.println("Have a nice day!\n");

        count(breakfast);
    }

    public static void calculateCalories(Food[] breakfast) {
        int calories = 0;

        for (Food food : breakfast) {
            if (food != null) {
                calories += food.getCalories();
            } else {
                break;
            }
        }
        System.out.println("Breakfast have " + calories + " kcal");
    }

    public static void sort(Food[] breakfast) {
        Arrays.sort(breakfast, new Comparator() {
            public int compare(Object o1, Object o2) {
                if (o1 == null) {
                    return 1;
                }
                if (o2 == null) {
                    return -1;
                }
                return ((Food) o1).getName().compareTo(((Food) o2).getName());
            }
        });
    }

    public static void count(Food[] breakfast) {
        Food smallApple = new Apple("small");
        int counterSmallApple = 0;
        Food middleApple = new Apple("middle");
        int counterMiddleApple = 0;
        Food bigApple = new Apple("big");
        int counterBigApple = 0;

        Food cheese = new Cheese();
        int counterCheese = 0;

        Food sandwichWithHamAndCheese = new Sandwich("ham", "cheese");
        int counterSandwichHamCheese = 0;
        Food sandwichWithBananaAndChocolate = new Sandwich("banana", "chocolate");
        int counterSandwichBananaChocolate = 0;

        for(Food food : breakfast) {
            if(food != null) {
                if (food.equals(smallApple)) {
                    counterSmallApple++;
                } else if (food.equals(middleApple)) {
                    counterMiddleApple++;
                } else if (food.equals(bigApple)) {
                    counterBigApple++;
                } else if (food.equals(cheese)) {
                    counterCheese++;
                } else if (food.equals(sandwichWithHamAndCheese)) {
                    counterSandwichHamCheese++;
                } else if (food.equals(sandwichWithBananaAndChocolate)) {
                    counterSandwichBananaChocolate++;
                }
            } else {
                break;
            }
        }

        System.out.println("Your breakfast was:");
        System.out.println(counterSmallApple + " small apple(s);");
        System.out.println(counterMiddleApple + " middle apple(s);");
        System.out.println(counterBigApple + " small apple(s);");
        System.out.println(counterCheese + " pieces of cheese");
        System.out.println(counterSandwichHamCheese + " sandwiches with ham and cheese");
        System.out.println(counterSandwichBananaChocolate + " sandwiches with banana and chocolate");

    }
}
