package bsu.rfe.java.group10.lab3.Zasemkova.varC1;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("NO ARGUMENTS");
            System.exit(-1);
        }

        double[] coefficients = new double[args.length];
        double[] coefficientsDif = new double[args.length];
        int i = 0;
        try {
            for (String arg : args) {
                coefficients[i] = Double.parseDouble(arg);
                coefficientsDif[i] = Double.parseDouble(arg);
                i++;
            }
        } catch (NumberFormatException exception) {
            System.out.println("WRONG ARGUMENTS");
            System.exit(-2);
        }

        GornerTableFrame frame = new GornerTableFrame(coefficients, coefficientsDif);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
