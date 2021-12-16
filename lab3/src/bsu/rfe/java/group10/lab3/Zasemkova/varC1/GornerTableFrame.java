package bsu.rfe.java.group10.lab3.Zasemkova.varC1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class GornerTableFrame extends JFrame {

    private static final int WIDTH = 1080;
    private static final int HEIGHT = 620;

    private double[] coefficients;
    private double[] coefficientsDif;


    private JFileChooser fileChooser = null;

    private JMenuItem saveToTxtMI;
    private JMenuItem saveToBinMI;
    private JMenuItem saveToCsvMI;
    private JMenuItem searchValueMIFromDiapazon;
    private JMenuItem searchValueMI;
    private JMenuItem showInfoMI;

    private JTextField xBegTF;
    private JTextField xEndTF;
    private JTextField stepTF;

    private Box hResultTableBox;

    private GornerTableCellRenderer renderer = new GornerTableCellRenderer();

    private GornerTableModel dataTable;

    GornerTableFrame(double[] coefficients, double[] coefficientsDif) {
        super("Табулирование по схеме Горнера");
        this.coefficients = coefficients;
        this.coefficientsDif = coefficientsDif;
        setSize(WIDTH, HEIGHT);

        Toolkit tk = Toolkit.getDefaultToolkit();
        setLocation((tk.getScreenSize().width - WIDTH) / 2, (tk.getScreenSize().height - HEIGHT) / 2);

        constructMenu();
        constructTop();
        constructMid();
        constructBot();
    }

    private void constructMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("Файл");
        JMenu tableMenu = new JMenu("Таблица");
        JMenu infoMenu = new JMenu("Справка");

        menuBar.add(fileMenu);
        menuBar.add(tableMenu);
        menuBar.add(infoMenu);

        Action saveToTxtAction = new AbstractAction("Сохранить в .txt") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileChooser == null) {
                    fileChooser = new JFileChooser();
                    fileChooser.setCurrentDirectory(new File("."));
                }
                if (fileChooser.showSaveDialog(GornerTableFrame.this) == JFileChooser.APPROVE_OPTION) {
                    saveToTxt(new File(fileChooser.getSelectedFile().getName().concat(".txt")));
                }
            }
        };
        saveToTxtMI = fileMenu.add(saveToTxtAction);
        saveToTxtMI.setEnabled(false);

        Action saveToBinAction = new AbstractAction("Сохранить в .bin") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileChooser == null) {
                    fileChooser = new JFileChooser();
                    fileChooser.setCurrentDirectory(new File("."));
                }
                if (fileChooser.showSaveDialog(GornerTableFrame.this) == JFileChooser.APPROVE_OPTION) {
                    saveToBin(new File(fileChooser.getSelectedFile().getName().concat(".bin")));
                }
            }
        };
        saveToBinMI = fileMenu.add(saveToBinAction);
        saveToBinMI.setEnabled(false);

        Action saveToCsvAction = new AbstractAction("Сохранить в .csv") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileChooser == null) {
                    fileChooser = new JFileChooser();
                    fileChooser.setCurrentDirectory(new File("."));
                }
                if (fileChooser.showSaveDialog(GornerTableFrame.this) == JFileChooser.APPROVE_OPTION) {
                    saveToCsv(new File(fileChooser.getSelectedFile().getName().concat(".csv")));
                }
            }
        };
        saveToCsvMI = fileMenu.add(saveToCsvAction);
        saveToCsvMI.setEnabled(false);

        Action searchDiapazonValueAction = new AbstractAction("Найти из диапазона") {
            @Override
            public void actionPerformed(ActionEvent e) {
                renderer.setNeedle(null);
                String value1 = JOptionPane.showInputDialog(GornerTableFrame.this, "From: ", "Поиск", JOptionPane.QUESTION_MESSAGE);
                String value2 = JOptionPane.showInputDialog(GornerTableFrame.this, "To: ", "Поиск", JOptionPane.QUESTION_MESSAGE);

                renderer.setNeedle1(value1, value2);
                getContentPane().repaint();
            }
        };
        searchValueMIFromDiapazon = tableMenu.add(searchDiapazonValueAction);
        searchValueMIFromDiapazon.setEnabled(false);

        Action searchValueAction = new AbstractAction("Найти значение многочлена") {
            public void actionPerformed(ActionEvent event) {
                // Запросить пользователя ввести искомую строку
                String value = JOptionPane.showInputDialog(GornerTableFrame.this, "Введите значение для поиска", "Поиск значения", JOptionPane.QUESTION_MESSAGE);
                // Установить введенное значение в качестве иголки
                renderer.setNeedle(value);
                // Обновить таблицу
                getContentPane().repaint();

            }
        };

        searchValueMI = tableMenu.add(searchValueAction);
        searchValueMI.setEnabled(false);


        Action showInfoAction = new AbstractAction("О программе") {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon icon = new ImageIcon(new ImageIcon(GornerTableFrame.class.getResource("cat.jpg")).getImage().getScaledInstance(240, 200, Image.SCALE_DEFAULT));
                JOptionPane.showMessageDialog(GornerTableFrame.this, "Засемкова Юлия \n 2 курс \n 10 группа", "Справка", JOptionPane.PLAIN_MESSAGE, icon);
            }
        };
        showInfoMI = infoMenu.add(showInfoAction);

        setJMenuBar(menuBar);
    }

    private void constructTop() {
        JLabel xBegLabel = new JLabel("X от: ");
        xBegTF = new JTextField("0.0", 15);
        xBegTF.setMaximumSize(xBegTF.getPreferredSize());

        JLabel xEndLabel = new JLabel("до: ");
        xEndTF = new JTextField("1.0", 15);
        xEndTF.setMaximumSize(xEndTF.getPreferredSize());

        JLabel stepLabel = new JLabel("с шагом: ");
        stepTF = new JTextField("0.1", 15);
        stepTF.setMaximumSize(stepTF.getPreferredSize());

        Box hBoxTextFields = Box.createHorizontalBox();
        //hBoxTextFields.setBorder(BorderFactory.createBevelBorder(1));
        hBoxTextFields.add(Box.createHorizontalGlue());
        hBoxTextFields.add(xBegLabel);
        hBoxTextFields.add(Box.createHorizontalStrut(10));
        hBoxTextFields.add(xBegTF);
        hBoxTextFields.add(Box.createHorizontalStrut(30));
        hBoxTextFields.add(xEndLabel);
        hBoxTextFields.add(Box.createHorizontalStrut(10));
        hBoxTextFields.add(xEndTF);
        hBoxTextFields.add(Box.createHorizontalStrut(30));
        hBoxTextFields.add(stepLabel);
        hBoxTextFields.add(Box.createHorizontalStrut(10));
        hBoxTextFields.add(stepTF);
        hBoxTextFields.add(Box.createHorizontalGlue());

        hBoxTextFields.setPreferredSize(new Dimension(
                (int) hBoxTextFields.getMaximumSize().getWidth(),
                (int) hBoxTextFields.getMinimumSize().getHeight() * 2));

        add(hBoxTextFields, BorderLayout.NORTH);
    }

    private void constructMid() {
        hResultTableBox = Box.createHorizontalBox();
        hResultTableBox.add(new JPanel());
        add(hResultTableBox, BorderLayout.CENTER);
    }

    private void constructBot() {
        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double xBeg = Double.parseDouble(xBegTF.getText());
                    double xEnd = Double.parseDouble(xEndTF.getText());
                    double step = Double.parseDouble(stepTF.getText());

                    double xBegF = Double.parseDouble(xBegTF.getText());
                    double xEndF = Double.parseDouble(xEndTF.getText());
                    double stepF = Double.parseDouble(stepTF.getText());

                    if (xBeg > xEnd) {
                        double tmp = xBeg;
                        xBeg = xEnd;
                        xEnd = tmp;
                        xBegTF.setText(String.valueOf(xBeg));
                        xEndTF.setText(String.valueOf(xEnd));
                    }

                    if (step <= 0) {
                        throw new NumberFormatException();
                    }

                    dataTable = new GornerTableModel(xBeg, xEnd, step, coefficients, coefficientsDif, xBegF, xEndF, stepF);
                    JTable table = new JTable(dataTable);
                    table.setDefaultRenderer(Double.class, renderer);
                    table.setRowHeight(30);
                    hResultTableBox.removeAll();
                    hResultTableBox.add(new JScrollPane(table));
                    validate();

                    saveToTxtMI.setEnabled(true);
                    saveToBinMI.setEnabled(true);
                    saveToCsvMI.setEnabled(true);
                    searchValueMIFromDiapazon.setEnabled(true);
                    searchValueMI.setEnabled(true);
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(GornerTableFrame.this, "Неверные данные", "Ошибка", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton resetButton = new JButton("Очистить");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xBegTF.setText("0.0");
                xEndTF.setText("1.0");
                stepTF.setText("0.1");
                hResultTableBox.removeAll();
                hResultTableBox.add(new JPanel());
                saveToTxtMI.setEnabled(false);
                saveToBinMI.setEnabled(false);
                saveToCsvMI.setEnabled(false);
                searchValueMIFromDiapazon.setEnabled(false);
                searchValueMI.setEnabled(false);
                renderer.setNeedle(null);
                renderer.setNeedle1(null, null);

                getContentPane().validate();
            }
        });

        Box hBoxButtons = Box.createHorizontalBox();
        //hBoxButtons.setBorder(BorderFactory.createBevelBorder(1));
        hBoxButtons.add(Box.createHorizontalGlue());
        hBoxButtons.add(buttonCalc);
        hBoxButtons.add(Box.createHorizontalStrut(30));
        hBoxButtons.add(resetButton);
        hBoxButtons.add(Box.createHorizontalGlue());
        hBoxButtons.setPreferredSize(new Dimension(
                (int) hBoxButtons.getMaximumSize().getWidth(),
                (int) hBoxButtons.getMinimumSize().getHeight() * 2
        ));

        add(hBoxButtons, BorderLayout.SOUTH);
    }

    void saveToTxt(File file) {
        try {
            PrintStream out = new PrintStream(file);
            out.println("Результаты табулирования по схеме Горнера");
            out.print("Многочлен: ");
            for (int i = 0; i < coefficients.length; i++) {
                out.print(coefficients[i] + "*x^" + i);
                if (i != coefficients.length - 1) {
                    out.print(" + ");
                }
            }
            out.println("");
            out.println("Интервал от " + renderer.getFormatter().format(dataTable.getFrom()) + " до " + renderer.getFormatter().format(dataTable.getTo()) + " с шагом " + renderer.getFormatter().format(dataTable.getStep()));
            out.println("\n\n");
            for (int i = 0; i < dataTable.getRowCount(); i++) {
                out.println("F(" + renderer.getFormatter().format(dataTable.getValueAt(i, 0)) + ") = " + renderer.getFormatter().format(dataTable.getValueAt(i, 1)) + " или " + renderer.getFormatter().format(dataTable.getValueAt(i, 2)) + ", с разницей " + renderer.getFormatter().format(dataTable.getValueAt(i, 3)));
            }
            out.close();
        } catch (FileNotFoundException ignore) {

        }
    }

    void saveToBin(File file) {
        try {
            DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
            for (int i = 0; i < dataTable.getRowCount(); i++) {
                out.writeDouble((Double) dataTable.getValueAt(i, 0));
                out.writeDouble((Double) dataTable.getValueAt(i, 1));
            }
            out.close();
        } catch (Exception ignore) {

        }
    }

    void saveToCsv(File file) {
        try {
            PrintStream out = new PrintStream(file);
            for (int i = 0; i < dataTable.getRowCount(); i++) {
                out.print(dataTable.getValueAt(i, 0) + ";");
                out.print(dataTable.getValueAt(i, 1) + ";");
                out.print(dataTable.getValueAt(i, 2) + ";");
                out.println(dataTable.getValueAt(i, 3));
            }
            out.close();
        } catch (FileNotFoundException ignore) {

        }
    }
}
