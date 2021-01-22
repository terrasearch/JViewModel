package com.github.terrasearch.jviewmodel.property.examples;

import com.github.terrasearch.jviewmodel.property.Property;

import javax.swing.*;

/**
 * Example of a ViewModel as a property
 */
class TimerViewModel extends Property<Integer> {
    public TimerViewModel() {
        super(0);
        setTimer();
    }

    /**
     * Creates an timer which increases the value by 1
     */
    private void setTimer() {
        new Timer(1000, event -> setValue(getValue() + 1)).start();
    }
}
