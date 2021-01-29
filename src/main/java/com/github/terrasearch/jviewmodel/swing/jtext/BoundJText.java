package com.github.terrasearch.jviewmodel.swing.jtext;

import com.github.terrasearch.jviewmodel.convert.IValueConverter;
import com.github.terrasearch.jviewmodel.property.IPropertyChangeListener;
import com.github.terrasearch.jviewmodel.property.Property;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.util.Objects;

public class BoundJText<T> {
    private final JTextComponentChangeWrapper textChangeListener;
    private final JTextComponent textComponent;

    private IValueConverter<T> valueConverter;
    private Property<T> boundProperty;

    private boolean readonly = true;

    private IPropertyChangeListener<T> propertyChangeListener;
    private PropertyChangeListener jTextChangedListener;

    public BoundJText(final JTextComponent textComponent) {
        this.textComponent = textComponent;
        this.textChangeListener = new JTextComponentChangeWrapper(Objects.requireNonNull(textComponent));
    }

    public void setBinding(@NotNull final Property<T> property, @NotNull final IValueConverter<T> valueConverter) {
        Objects.requireNonNull(property);
        Objects.requireNonNull(valueConverter);

        // Remove old binding
        removeBinding();
        this.boundProperty = property;
        this.valueConverter = valueConverter;

        // Set bindings
        setBinding();

        // Set text to initial property value
        textComponent.setText(String.valueOf(property.getValue()));
    }

    public void removeBinding() {
        removePropertyChangeListener();
        removeTextComponentChangeListener();
    }

    public void setReadOnly(final boolean readOnly) {
        this.readonly = readOnly;
    }

    private void setBinding() {
        setPropertyChangeListener();
        setTextComponentChangeListener();
    }

    private void setPropertyChangeListener() {
        propertyChangeListener = this::valueChanged;
        boundProperty.registerPropertyChangedListener(propertyChangeListener);
    }

    private void removePropertyChangeListener() {
        if (propertyChangeListener != null) {
            boundProperty.removePropertyChangedListener(propertyChangeListener);
            propertyChangeListener = null;
        }
    }

    private void setTextComponentChangeListener() {
        jTextChangedListener = this::textChanged;
        textChangeListener.addChangeListener(jTextChangedListener);
    }

    private void removeTextComponentChangeListener() {
        if (jTextChangedListener != null) {
            textChangeListener.removeChangeListener(jTextChangedListener);
            jTextChangedListener = null;
        }
    }

    private void textChanged(@NotNull final PropertyChangeEvent evt) {
        if (!readonly) {
            SwingUtilities.invokeLater(() -> {
                synchronized (textComponent) {
                    removePropertyChangeListener();
                    try {
                        final T value = valueConverter.convertToValue(textComponent.getText());
                        boundProperty.setValue(value);
                        textComponent.setBorder(BorderFactory.createEmptyBorder());
                    } catch (ParseException e) {
                        textComponent.setBorder(BorderFactory.createLineBorder(Color.RED));
                        removeTextComponentChangeListener();
                        textComponent.setText(String.valueOf(evt.getOldValue()));
                        setTextComponentChangeListener();
                    }
                    setPropertyChangeListener();
                }
            });
        }
    }

    private void valueChanged(@Nullable final T valueBefore, @Nullable final T valueAfter) {
        synchronized (textComponent) {
            removeTextComponentChangeListener();
            textComponent.setText(String.valueOf(valueAfter));
            setTextComponentChangeListener();
        }
    }
}
