package bsu.rfe.java.group6.lab2.Murashkin.varB12;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")

public class MainFrame  extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 320;

    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;
    private JTextField textFieldResult;
    private JTextField memoryTextField;
    double sum = 0.0;

    private ButtonGroup radioButtons = new ButtonGroup();
    private ButtonGroup radioMemoryButtons = new ButtonGroup();

    private Box hboxFormulaType = Box.createHorizontalBox();
    private Box hBoxMemoryType = Box.createHorizontalBox();

    private int formulaId = 1;
    private int memoryId= 1;
    private Double mem1 = new Double(0);
    private Double mem2 = new Double(0);
    private Double mem3 = new Double(0);

    public Double calculate1(Double x, Double y, Double z) {
        return Math.pow(Math.cos(Math.exp(x)) + Math.log((1+y)*(1+y)) + Math.sqrt(Math.exp(Math.cos(x)) + Math.pow(Math.sin(Math.PI * z), 2) +
                Math.sqrt(1/x) + Math.cos(y*y)), Math.sin(z));
    }

    public Double calculate2(Double x, Double y, Double z) {
        return (1+ Math.sqrt(z*x)) / Math.pow(1 + x*x*x, 1/ y);
    }

    private void addRadioButton(String buttonName, final int formulaId) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                MainFrame.this.formulaId = formulaId;
               }
        });
        radioButtons.add(button);
        hboxFormulaType.add(button);
    }
    private void addMemoryRadioButton (String buttonName, final int memoryId)	{
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event)	{
                MainFrame.this.memoryId = memoryId;
                if (memoryId == 1)	memoryTextField.setText(mem1.toString());
                if (memoryId == 2)	memoryTextField.setText(mem2.toString());
                if (memoryId == 3)	memoryTextField.setText(mem3.toString());
            }
        });
        radioMemoryButtons.add(button);
        hBoxMemoryType.add(button);
    }

    public MainFrame() {
        super("Вычисление формулы");
        setSize(WIDTH,HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH)/2,
                (kit.getScreenSize().height - HEIGHT)/2);
        hboxFormulaType.add(Box.createHorizontalGlue());
        addRadioButton("Формула 1", 1);
        addRadioButton("Формула 2", 2);
        radioButtons.setSelected(
                radioButtons.getElements().nextElement().getModel(), true);
        hboxFormulaType.add(Box.createHorizontalGlue());
        hboxFormulaType.setBorder(
                BorderFactory.createLineBorder(Color.YELLOW));
        JLabel labelForX = new JLabel("X: ");
        textFieldX = new JTextField("0", 10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
        JLabel labelForY = new JLabel("Y: ");
        textFieldY = new JTextField("0", 10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());
        JLabel labelForZ = new JLabel("Z: ");
        textFieldZ  = new JTextField("0", 10);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());
        Box hboxVariables = Box.createHorizontalBox();
        hboxVariables.setBorder(
                BorderFactory.createLineBorder(Color.RED));

        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForX);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldX);

        hboxVariables.add(Box.createHorizontalStrut(100));
        hboxVariables.add(labelForY);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldY);
        hboxVariables.add(Box.createHorizontalGlue());

        hboxVariables.add(Box.createHorizontalStrut(100));
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForZ);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldZ);

        JLabel labelForResult = new JLabel("Результат: ");
        textFieldResult = new JTextField("0", 10);
        textFieldResult.setMaximumSize(textFieldResult.getPreferredSize());
        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        JButton buttonCalc = new JButton ("Вычислить");
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    double x = Double.parseDouble(textFieldX.getText());
                    double y = Double.parseDouble(textFieldY.getText());
                    double z = Double.parseDouble(textFieldZ.getText());
                    double result;
                    if (formulaId == 1)
                        result = calculate1(x,y,z);
                    else
                        result = calculate2(x,y,z);
                    textFieldResult.setText(String.valueOf(result));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this, "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        JButton buttonReset = new JButton ("Очистить поля");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldZ.setText("0");
                textFieldResult.setText("0");
            }
        });
        hBoxMemoryType.add(Box.createHorizontalGlue());
        addMemoryRadioButton("Память 1",1);
        addMemoryRadioButton("Память 2",2);
        addMemoryRadioButton("Память 3",3);
        radioMemoryButtons.setSelected(radioMemoryButtons.getElements().nextElement().getModel(), true);
        hBoxMemoryType.add(Box.createHorizontalGlue());
        JButton buttonMPlus = new JButton("M+");
        buttonMPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                try {
                    if (memoryId == 1) 	{
                        Double result = Double.parseDouble(textFieldX.getText());
                        mem1 += result;
                        textFieldX.setText(String.valueOf(mem1));}
                    if (memoryId == 2)	{
                        Double result = Double.parseDouble(textFieldY.getText());
                        mem2 += result;
                        textFieldY.setText(String.valueOf(mem2));}
                    if (memoryId == 3)	{
                        Double result = Double.parseDouble(textFieldZ.getText());
                        mem3 += result;
                        textFieldZ.setText(String.valueOf(mem3));}
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this, "Ошибка в формате числа с плавающей точкой", "Ошибочный формат числа", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton buttonMC = new JButton("MC");
        buttonMC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                if (memoryId == 1)	{
                    mem1 = (double) 0;
                    textFieldX.setText(String.valueOf(mem1));}
                if (memoryId == 2)	{
                    mem2 = (double) 0;
                    textFieldY.setText(String.valueOf(mem2));}
                if (memoryId == 3)	{
                    mem3 = (double) 0;
                    textFieldZ.setText(String.valueOf(mem3));}

            }
        });
        Box hboxmemoryResult = Box.createHorizontalBox();
        hboxmemoryResult.add(Box.createHorizontalGlue());
        hboxmemoryResult.add(buttonMC);
        hboxmemoryResult.add(Box.createHorizontalStrut(10));
        hboxmemoryResult.add(buttonMPlus);
        hboxmemoryResult.add(Box.createHorizontalGlue());
        hboxmemoryResult.setBorder(BorderFactory.createLineBorder(Color.PINK));

        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonReset);
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.setBorder(BorderFactory.createLineBorder(Color.GREEN));


        Box contentBox = Box.createVerticalBox();
        contentBox.add(hboxFormulaType);
        contentBox.add(hboxVariables);
        contentBox.add(hboxResult);
        contentBox.add(hboxmemoryResult);
        contentBox.add(hboxButtons);
        contentBox.add(hBoxMemoryType);
        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }


    public static void main(String[] args) {
            MainFrame frame = new MainFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        }
}


