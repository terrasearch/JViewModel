package com.github.terrasearch.jviewmodel.swing;

import com.github.terrasearch.jviewmodel.convert.IValueConverter;
import com.github.terrasearch.jviewmodel.property.IPropertyChangeListener;
import com.github.terrasearch.jviewmodel.property.Property;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.text.ParseException;
import java.util.Objects;

public class BoundJText<T> {
    private final JTextSimpleChangeListenerWrapper textComponent;
    private IValueConverter<T> valueConverter;
    private Property<T> boundProperty;
    private boolean readonly = true;

    private IPropertyChangeListener<T> boundPropertyChangedListener;

    private int lastChange = 0, lastNotifiedChange = 0;

    public BoundJText(final JTextComponent textComponent) {
        this.textComponent = new JTextSimpleChangeListenerWrapper(Objects.requireNonNull(textComponent));
    }

    public void setBoundProperty(final Property<T> boundProperty, final IValueConverter<T> valueConverter) {
        Objects.requireNonNull(boundProperty);
        this.valueConverter = Objects.requireNonNull(valueConverter);

        if (this.boundPropertyChangedListener != null) {
            boundProperty.removePropertyChangedListener(boundPropertyChangedListener);
        }

        this.boundProperty = boundProperty;
        textComponent.getTextComponent().setText(String.valueOf(this.boundProperty.getValue()));

        boundPropertyChangedListener = (valueBefore, valueAfter) -> textComponent.getTextComponent()
                .setText(String.valueOf(valueAfter));

        boundProperty.registerPropertyChangedListener(boundPropertyChangedListener);
        registerTextComponentChangeListener();
    }

    public void setReadOnly(boolean readOnly) {
        this.readonly = readOnly;
    }

    private void registerTextComponentChangeListener() {
        textComponent.addChangeListener(evt -> {
            if (!readonly) {
                lastChange++;
                SwingUtilities.invokeLater(() -> {
                    if (lastNotifiedChange != lastChange) {
                        try {
                            lastNotifiedChange = lastChange;
                            final T value = valueConverter.convertToValue(textComponent.getTextComponent().getText());
                            boundProperty.setValue(value);
                            textComponent.getTextComponent().setBorder(BorderFactory.createEmptyBorder());
                        } catch (ParseException e) {
                            textComponent.getTextComponent().setBorder(BorderFactory.createLineBorder(Color.RED));
                            lastNotifiedChange++;
                            textComponent.getTextComponent().setText(String.valueOf(evt.getOldValue()));
                        }
                    }
                });
            }
        });
    }
}
