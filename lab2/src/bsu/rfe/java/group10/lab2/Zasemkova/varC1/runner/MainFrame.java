package bsu.rfe.java.group10.lab2.Zasemkova.varC1.runner;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import javax.swing.*;


@SuppressWarnings("serial")
public class MainFrame extends JFrame {

    private static final int WIDTH = 1300;
    private static final int HEIGHT = 500;

    private JLabel image;

    private static Double mem1 = 0.0;
    private static Double mem2 = 0.0;
    private static Double mem3 = 0.0;

    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;
    private JTextField textFieldResult;
    private JTextField memoryTextField;

    private ButtonGroup formulaRadioButtons = new ButtonGroup();
    private ButtonGroup memoryRadioButtons = new ButtonGroup();

    private Box hBoxFormulaType = Box.createHorizontalBox();
    private Box hBoxMemoryType = Box.createHorizontalBox();

    private int formulaId = 1;
    private int memoryId = 1;

    public Double f1(Double x, Double y, Double z) {
        return Math.sin(Math.log(y) + Math.sin(Math.PI * y * y)) * Math.pow(x * x + Math.sin(z) + Math.exp(Math.cos(z)), 0.25);
    }

    public Double f2(Double x, Double y, Double z) {
        return Math.pow(Math.cos(Math.exp(x)) + 2 * Math.log(1 + y) + Math.sqrt(Math.exp(Math.cos(x)) + Math.pow(Math.sin(Math.PI * z), 2)) + Math.sqrt(1 / x) + Math.cos(y * y), Math.sin(z));
    }

    private void addFormulaRadioButton(String buttonName, final int formulaId) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                MainFrame.this.formulaId = formulaId;
            }
        });
        formulaRadioButtons.add(button);
        hBoxFormulaType.add(button);
    }

    private void addMemoryRadioButton(String buttonName, final int memoryId) {
        JRadioButton button = new JRadioButton(buttonName);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                MainFrame.this.memoryId= memoryId;
                if (memoryId == 1) {
                    memoryTextField.setText(mem1.toString());
                } else if (memoryId == 2) {
                    memoryTextField.setText(mem2.toString());
                } else if (memoryId == 3) {
                    memoryTextField.setText(mem3.toString());
                }
            }
        });

        memoryRadioButtons.add(button);
        hBoxMemoryType.add(button);
    }

    public MainFrame() {
        super("Formula calculation");

        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH) / 2, (kit.getScreenSize().height - HEIGHT) / 2);

        Box picture = Box.createHorizontalBox();
        picture.add(Box.createVerticalGlue());
        picture.add(Box.createHorizontalGlue());
        image = new JLabel(new ImageIcon("src/bsu/rfe/java/group10/lab2/Zasemkova/varC1/res/f1.bmp"));
        picture.add(image);
        picture.add(Box.createHorizontalGlue());
        picture.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        hBoxFormulaType.add(Box.createHorizontalGlue());
        addFormulaRadioButton("Formula 1", 1);
        addFormulaRadioButton("Formula 2", 2);
        formulaRadioButtons.setSelected(formulaRadioButtons.getElements().nextElement().getModel(), true);
        hBoxFormulaType.add(Box.createHorizontalGlue());
        hBoxFormulaType.setBorder(BorderFactory.createLineBorder(Color.YELLOW));

        JLabel labelForX = new JLabel("X:");
        textFieldX = new JTextField("0", 10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());

        JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("0", 10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());

        JLabel labelForZ = new JLabel("Z:");
        textFieldZ = new JTextField("0", 10);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());

        Box hBoxVariables = Box.createHorizontalBox();
        hBoxVariables.setBorder(BorderFactory.createLineBorder(Color.RED));
        hBoxVariables.add(Box.createHorizontalGlue());

        hBoxVariables.add(labelForX);
        hBoxVariables.add(Box.createHorizontalStrut(10));
        hBoxVariables.add(textFieldX);
        hBoxVariables.add(Box.createHorizontalStrut(100));

        hBoxVariables.add(labelForY);
        hBoxVariables.add(Box.createHorizontalStrut(10));
        hBoxVariables.add(textFieldY);
        hBoxVariables.add(Box.createHorizontalStrut(100));

        hBoxVariables.add(labelForZ);
        hBoxVariables.add(Box.createHorizontalStrut(10));
        hBoxVariables.add(textFieldZ);

        hBoxVariables.add(Box.createHorizontalGlue());

        JLabel labelForResult = new JLabel("Result:");
        textFieldResult = new JTextField("0", 10);
        textFieldResult.setMaximumSize(textFieldResult.getPreferredSize());

        Box hBoxResult = Box.createHorizontalBox();
        hBoxResult.add(Box.createHorizontalGlue());
        hBoxResult.add(labelForResult);
        hBoxResult.add(Box.createHorizontalStrut(10));
        hBoxResult.add(textFieldResult);
        hBoxResult.add(Box.createHorizontalGlue());
        hBoxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        JButton buttonCalc = new JButton("Calculate");
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    double result;
                    if (formulaId == 1) {
                        result = f1(x, y, z);
                    } else {
                        result = f2(x, y, z);
                    }
                    textFieldResult.setText(String.format("%.2f", result));
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Error in the format of writing a floating point number", "Wrong number format",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton buttonReset = new JButton("Clean the fields");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldZ.setText("0");
                textFieldResult.setText("0");
            }
        });

        Box hBoxButtons = Box.createHorizontalBox();
        hBoxButtons.add(Box.createHorizontalGlue());
        hBoxButtons.add(buttonCalc);
        hBoxButtons.add(Box.createHorizontalStrut(100));
        hBoxButtons.add(buttonReset);
        hBoxButtons.add(Box.createHorizontalGlue());
        hBoxButtons.setBorder(BorderFactory.createLineBorder(Color.GREEN));

        hBoxMemoryType.add(Box.createHorizontalGlue());
        addMemoryRadioButton("Mem 1", 1);
        addMemoryRadioButton("Mem 2", 2);
        addMemoryRadioButton("Mem 3", 3);
        memoryRadioButtons.setSelected(memoryRadioButtons.getElements().nextElement().getModel(), true);
        hBoxMemoryType.add(Box.createHorizontalGlue());

        Box hBoxMemoryResult = Box.createHorizontalBox();
        hBoxMemoryResult.add(Box.createHorizontalGlue());
        JButton buttonMC = new JButton("MC");
        buttonMC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                if (memoryId == 1) {
                    mem1 = 0.0;
                } else if (memoryId == 2) {
                    mem2 = 0.0;
                } else if (memoryId == 3) {
                    mem3 = 0.0;
                }
            }
        });

        JButton buttonMPlus = new JButton("M+");
        buttonMPlus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    Double memoryResult = Double.parseDouble(textFieldResult.getText());

                    if (memoryId == 1) {
                        mem1 += memoryResult;
                        memoryTextField.setText(mem1.toString());
                    } else if (memoryId == 2) {
                        mem2 += memoryResult;
                        memoryTextField.setText(mem2.toString());
                    } else if (memoryId == 3) {
                        mem3 += memoryResult;
                        memoryTextField.setText(mem3.toString());
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Error in the format of writing a floating point number", "Wrong number format",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        memoryTextField = new JTextField("0.0", 15);
        memoryTextField.setMaximumSize(memoryTextField.getPreferredSize());

        hBoxMemoryResult.add(buttonMC);
        hBoxMemoryResult.add(Box.createHorizontalStrut(10));
        hBoxMemoryResult.add(memoryTextField);
        hBoxMemoryResult.add(Box.createHorizontalStrut(10));
        hBoxMemoryResult.add(buttonMPlus);
        hBoxMemoryResult.add(Box.createHorizontalGlue());

        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(picture);
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hBoxFormulaType);
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hBoxVariables);
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hBoxResult);
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hBoxButtons);
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hBoxMemoryType);
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hBoxMemoryResult);
        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
