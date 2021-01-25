package com.github.terrasearch.jviewmodel.examples.swing;

import com.github.terrasearch.jviewmodel.convert.IntegerConverter;
import com.github.terrasearch.jviewmodel.swing.BoundJText;

import javax.swing.*;
import java.awt.*;

public class MainView {
    private static final JTextField boundText = new JTextField();
    private static final JTextField propertyValueText = new JTextField();
    private static final SwingViewModel viewModel = new SwingViewModel();

    public static void main(String[] args) {
        initGUI();
        bindings();
    }

    /**
     * Initiates GUI components of the view
     */
    private static void initGUI() {
        final JFrame mainFrame = new JFrame("Example of an implemented Swing element");
        final JPanel panel = new JPanel();
        final JLabel boundSwingElementLabel = new JLabel("Bound Swing element, Integer");
        final JLabel valueOfPropertyLabel = new JLabel("Value of property");

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(500, 150);
        mainFrame.add(panel);

        propertyValueText.setEnabled(false);

        panel.setLayout(new GridLayout(2, 2));
        panel.add(boundSwingElementLabel);
        panel.add(boundText);
        panel.add(valueOfPropertyLabel);
        panel.add(propertyValueText);

        mainFrame.setVisible(true);
    }

    private static void bindings() {
        BoundJText<Integer> boundTextWrapper = new BoundJText<>(boundText);
        boundTextWrapper.setBoundProperty(viewModel, new IntegerConverter());
        boundTextWrapper.setReadOnly(false);

        BoundJText<Integer> propertyValueTextWrapper = new BoundJText<>(propertyValueText);
        boundTextWrapper.setBoundProperty(viewModel, new IntegerConverter());
        boundTextWrapper.setReadOnly(true);
    }
}
