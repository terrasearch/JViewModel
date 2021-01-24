package com.github.terrasearch.jviewmodel.swing;

import com.github.terrasearch.jviewmodel.convert.IValueConverter;
import com.github.terrasearch.jviewmodel.property.Property;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.text.ParseException;

public class BoundJTextComponent<T> {
    private final IValueConverter<T> valueConverter;
    private final Property<T> boundProperty;
    private int lastChange = 0, lastNotifiedChange = 0;

    public BoundJTextComponent(JTextComponent textComponent, Property<T> boundProperty, IValueConverter<T> valueConverter, boolean readonly) {
        this.boundProperty = boundProperty;
        this.valueConverter = valueConverter;
        textComponent.setText(String.valueOf(boundProperty.getValue()));
        boundProperty.registerPropertyChangedListener((valueBefore, valueAfter) -> textComponent.setText(String.valueOf(valueAfter)));
        if (!readonly) {
            registerTextComponentChangeListener(textComponent);
        }
    }

    private void registerTextComponentChangeListener(JTextComponent textComponent) {
        DocumentChangeListener.addChangeListener(textComponent, evt -> {
            lastChange++;
            SwingUtilities.invokeLater(() -> {
                if (lastNotifiedChange != lastChange) {
                    try {
                        lastNotifiedChange = lastChange;
                        final T value = valueConverter.convertToValue(textComponent.getText());
                        boundProperty.setValue(value);
                        textComponent.setBorder(BorderFactory.createEmptyBorder());
                    } catch (ParseException e) {
                        textComponent.setBorder(BorderFactory.createLineBorder(Color.RED));
                        lastNotifiedChange++;
                        textComponent.setText(String.valueOf(evt.getOldValue()));
                    }
                }
            });
        });
    }
}
