package com.github.terrasearch.jviewmodel.examples.property;

import com.github.terrasearch.jviewmodel.property.IPropertyChangeListener;

import javax.swing.*;
import java.awt.*;

/**
 * The MainView, which demonstrates the updating mechanic via {@link IPropertyChangeListener}.
 * Go to {@link #bindings()} to see how to use the subscription
 */
class MainView {
    private static final JTextField valueBeforeChangeText = new JTextField("-", 5);
    private static final JTextField valueAfterChangeText = new JTextField("0", 5);
    private static final TimerViewModel viewModel = new TimerViewModel();

    public static void main(String[] args) {
        initGUI();
        bindings();
    }

    /**
     * Binds the View with the ViewModel
     */
    private static void bindings() {
        viewModel.registerPropertyChangedListener((valueBefore, valueAfter) -> {
            valueBeforeChangeText.setText(String.valueOf(valueBefore));
            valueAfterChangeText.setText(String.valueOf(valueAfter));
        });
    }

    /**
     * Initiates GUI components of the view
     */
    private static void initGUI() {
        final JFrame mainFrame = new JFrame("Example of an implemented ViewModel Property");
        final JPanel panel = new JPanel();
        final JLabel beforeChangeValueLabel = new JLabel("Value before change");
        final JLabel afterChangedValueLabel = new JLabel("Value after change");

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(300, 150);
        mainFrame.add(panel);

        valueBeforeChangeText.setEnabled(false);
        valueAfterChangeText.setEnabled(false);

        panel.setLayout(new GridLayout(2, 2));
        panel.add(beforeChangeValueLabel);
        panel.add(valueBeforeChangeText);
        panel.add(afterChangedValueLabel);
        panel.add(valueAfterChangeText);

        mainFrame.setVisible(true);
    }
}
