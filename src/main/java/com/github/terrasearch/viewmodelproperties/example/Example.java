package com.github.terrasearch.viewmodelproperties.example;

import javax.swing.*;

public class Example {
    private static JFrame mainFrame;
    private static JLabel beforeChangeValueLabel;
    private static JTextField beforeChangedValueText;
    private static JLabel afterChangeValueLabel;
    private static JTextField afterChangedValueText;

    public static void main(String[] args) {
        mainFrame.add(mainFrame);
        System.exit(0);
    }

    private static void initGUI() {
        mainFrame = new JFrame("Example of a implemented ViewModelProperty");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(300, 150);

        beforeChangeValueLabel = new JLabel("Value before change");
        mainFrame.add(beforeChangeValueLabel);

        beforeChangedValueText = new JTextField("-");
        beforeChangedValueText.setEnabled(false);

    }
}
