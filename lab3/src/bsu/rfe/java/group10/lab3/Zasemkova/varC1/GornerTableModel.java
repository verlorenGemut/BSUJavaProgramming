package bsu.rfe.java.group10.lab3.Zasemkova.varC1;

import javax.swing.table.AbstractTableModel;

public class GornerTableModel extends AbstractTableModel {

    private double[] coefficients;
    private double[] coefficientsDif;

    private double from;
    private double to;
    private double step;

    private double fromF;
    private double toF;
    private double stepF;

    public GornerTableModel(double from, double to, double step, double[] coefficients, double[] coefficientsDif, double fromF, double toF, double stepF) {
        this.from = from;
        this.to = to;
        this.step = step;
        this.coefficients = coefficients;
        this.coefficientsDif = coefficientsDif;
        this.fromF = fromF;
        this.toF = toF;
        this.stepF = stepF;
    }

    @Override
    public int getRowCount() {
        return (int) (Math.ceil((to - from) / step) + 1);
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        double x = from + rowIndex * step;
        double result = 0;
        double xF = toF + rowIndex * stepF;
        double resultDif = 0;

        if (columnIndex == 1 || columnIndex == 3) {
            int i = coefficients.length - 1;
            result = coefficients[i--];
            while (i >= 0) {
                result = result * x + coefficients[i--];
            }
        }

        if (columnIndex == 2 || columnIndex == 3) {
            int i = coefficientsDif.length - 1;
            resultDif = coefficientsDif[i--];
            while (i >= 0) {
                resultDif = resultDif * xF + coefficientsDif[i--];
            }
        }

        switch (columnIndex) {
            case 0: {
                return x;
            }
            case 1: {
                return result;
            }
            case 2: {
                return resultDif;
            }
            case 3: {
                return Math.abs(result - resultDif);
            }
            default: {
                return 0;
            }
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: {
                return "Значение X";
            }
            case 1: {
                return "Значение многочлена";
            }
            case 2: {
                return "Значение revert многочлена";
            }
            case 3: {
                return "Разница";
            }
            default: {
                return "";
            }
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Double.class;
    }

    public double getFrom() {
        return from;
    }

    public double getTo() {
        return to;
    }

    public double getStep() {
        return step;
    }
}
