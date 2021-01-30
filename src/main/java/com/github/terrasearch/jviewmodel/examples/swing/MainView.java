package com.github.terrasearch.jviewmodel.examples.swing;

import com.github.terrasearch.jviewmodel.convert.IntegerValueConverter;
import com.github.terrasearch.jviewmodel.swing.IParseErrorListener;
import com.github.terrasearch.jviewmodel.swing.jtext.JTextComponentBinding;

import javax.swing.*;
import java.awt.*;

public class MainView {
    private static final JTextField boundText = new JTextField();
    private static final JTextField propertyValueText = new JTextField();
    private static final SwingViewModel viewModel = new SwingViewModel();
    private static final JLabel errorMessage = new JLabel(" ");

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
        errorMessage.setForeground(Color.RED);

        final GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        final GridBagConstraints constraints = new GridBagConstraints();

        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.gridwidth = GridBagConstraints.RELATIVE;
        layout.setConstraints(boundSwingElementLabel, constraints);
        panel.add(boundSwingElementLabel);

        constraints.gridwidth = GridBagConstraints.REMAINDER;
        layout.setConstraints(boundText, constraints);
        panel.add(boundText);

        constraints.gridwidth = GridBagConstraints.RELATIVE;
        layout.setConstraints(valueOfPropertyLabel, constraints);
        panel.add(valueOfPropertyLabel);

        constraints.gridwidth = GridBagConstraints.REMAINDER;
        layout.setConstraints(propertyValueText, constraints);
        panel.add(propertyValueText);

        layout.setConstraints(errorMessage, constraints);
        panel.add(errorMessage);

        mainFrame.setVisible(true);
    }

    private static void bindings() {
        final JTextComponentBinding<Integer> boundTextBinding = new JTextComponentBinding<>(boundText);
        boundTextBinding.setReadOnly(false);
        boundTextBinding.setErrorListener(
                new IParseErrorListener() {
                    private boolean isOnError;

                    @Override
                    public void onParseError(IllegalArgumentException iae) {
                        boundText.setForeground(Color.RED);
                        errorMessage.setText("Error " + iae.getLocalizedMessage());
                        isOnError = true;
                    }

                    @Override
                    public void onParseSuccess() {
                        if (isOnError) {
                            boundText.setForeground(Color.BLACK);
                            errorMessage.setText(" ");
                            isOnError = false;
                        }
                    }
                }
        );
        boundTextBinding.bind(viewModel, new IntegerValueConverter());

        final JTextComponentBinding<Integer> propertyValueTextBinding = new JTextComponentBinding<>(propertyValueText);
        propertyValueTextBinding.setReadOnly(false);
        propertyValueTextBinding.bind(viewModel, new IntegerValueConverter());
    }
}
