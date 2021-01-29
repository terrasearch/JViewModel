package com.github.terrasearch.jviewmodel.swing.jtext;

import com.github.terrasearch.jviewmodel.convert.IValueConverter;
import com.github.terrasearch.jviewmodel.property.IPropertyChangeListener;
import com.github.terrasearch.jviewmodel.property.Property;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.text.ParseException;
import java.util.Objects;

public class BoundJText<T> {
    private final JTextChangeListenerWrapper textComponent;
    private IValueConverter<T> valueConverter;
    private Property<T> boundProperty;
    private boolean readonly = true;

    private IPropertyChangeListener<T> boundPropertyChangedListener;

    private int lastChange = 0, lastNotifiedChange = 0;

    public BoundJText(final JTextComponent textComponent) {
        this.textComponent = new JTextChangeListenerWrapper(Objects.requireNonNull(textComponent));
    }

    public void setBoundProperty(@NotNull final Property<T> boundProperty,
                                 @NotNull final IValueConverter<T> valueConverter) {
        Objects.requireNonNull(boundProperty);
        this.valueConverter = Objects.requireNonNull(valueConverter);

        if (boundPropertyChangedListener != null) {
            this.boundProperty.removePropertyChangedListener(boundPropertyChangedListener);
        }

        this.boundProperty = boundProperty;
        textComponent.getTextComponent().setText(String.valueOf(this.boundProperty.getValue()));

        boundPropertyChangedListener = (valueBefore, valueAfter) -> textComponent.getTextComponent()
                .setText(String.valueOf(valueAfter));

        boundProperty.registerPropertyChangedListener(boundPropertyChangedListener);
        registerTextComponentChangeListener();
    }

    public void setReadOnly(final boolean readOnly) {
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
