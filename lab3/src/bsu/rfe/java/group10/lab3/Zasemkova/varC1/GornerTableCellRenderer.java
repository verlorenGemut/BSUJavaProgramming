package bsu.rfe.java.group10.lab3.Zasemkova.varC1;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

public class GornerTableCellRenderer implements TableCellRenderer {

    private String needle = null;
    private String needleX = null;
    private String needleY = null;

    private JPanel panel = new JPanel();
    private JLabel label = new JLabel();

    private DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance();

    public GornerTableCellRenderer() {
        formatter.setMaximumFractionDigits(6);
        formatter.setGroupingUsed(false);
        DecimalFormatSymbols curSymbol = formatter.getDecimalFormatSymbols();
        curSymbol.setDecimalSeparator('.');
        formatter.setDecimalFormatSymbols(curSymbol);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        String formattedValue = formatter.format(value);

        label.setText(formattedValue);
        panel.add(label);

        if ((column + row) % 2 == 0) {
            panel.setBackground(Color.black);
            label.setForeground(Color.white);
        } else {
            panel.setBackground(Color.white);
            label.setForeground(Color.black);
        }

        if (needleX != null && needleY != null) {
            Double coefficients1 = null;
            Double coefficients2 = null;
            Double coefficients3 = null;
            try {
                coefficients1 = Double.parseDouble(needleX);
                coefficients2 = Double.parseDouble(needleY);
                coefficients3 = Double.parseDouble(formattedValue);
            } catch (NullPointerException e) {
                panel.setBackground(Color.BLUE);
            }
            if (column >= 1 && column <= 3 && (coefficients3 <= coefficients2) && (coefficients3 >= coefficients1)) {
                panel.setBackground(Color.CYAN);
                label.setForeground(Color.BLACK);
            }
        }
        if (column >= 1 && needle != null && needle.equals(formattedValue)) {
            panel.setBackground(Color.BLUE);
            label.setForeground(Color.BLACK);
        }
        return panel;
    }

    public void setNeedle1(String needleX, String needleY) {
        this.needleX = needleX;
        this.needleY = needleY;
    }

    public void setNeedle(String needle) {
        this.needle = needle;
    }

    public DecimalFormat getFormatter() {
        return formatter;
    }
}
