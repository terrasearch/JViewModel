package com.github.terrasearch.jviewmodel.swing.jtext;

import com.github.terrasearch.jviewmodel.convert.IValueConverter;
import com.github.terrasearch.jviewmodel.property.IPropertyChangeListener;
import com.github.terrasearch.jviewmodel.property.Property;
import com.github.terrasearch.jviewmodel.swing.IParseErrorListener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.Objects;

/**
 * Performs a binding to a JText, which shows the value of the property.
 * If it is not readonly, it also writes the text to the property.
 *
 * @param <T> type of bound property
 */
public class JTextComponentBinding<T> {
    private static final Object lock = new Object();
    private final JTextComponentPropertyChangeWrapper listenableTextComponent;

    private IValueConverter<T> valueConverter;
    private Property<T> boundProperty;
    private IParseErrorListener errorListener;

    private boolean readonly = true;

    private IPropertyChangeListener<T> propertyChangeListener;
    private IPropertyChangeListener<String> textChangedListener;

    public JTextComponentBinding(final JTextComponent textComponent) {
        this.listenableTextComponent = new JTextComponentPropertyChangeWrapper(Objects.requireNonNull(textComponent));
    }

    /**
     * read only means, only the Property updates the JText. When false, the JText can update the Property also.
     */
    public void setReadOnly(final boolean readOnly) {
        this.readonly = readOnly;
    }

    /**
     * Sets a listener, which will be called on success and error after parsing.
     * When the listener is not set, the {@link JTextComponent}'s border is set to red on error.
     */
    public void setErrorListener(IParseErrorListener errorListener) {
        this.errorListener = errorListener;
    }

    /**
     * Binds Property to JText. Removes any existing binding of this Binding beforehand. Sets text to initial value.
     *
     * @param property       which will be bound
     * @param valueConverter how to convert a {@link String} to {@link T}
     */
    public void bind(@NotNull final Property<T> property, @NotNull final IValueConverter<T> valueConverter) {
        Objects.requireNonNull(property);
        Objects.requireNonNull(valueConverter);
        // Remove old binding
        unbind();
        this.boundProperty = property;
        this.valueConverter = valueConverter;
        // Set text to initial property value
        listenableTextComponent.getTextComponent().setText(String.valueOf(property.getValue()));
        bind();
    }

    /**
     * Unbinds Property and JText
     */
    public void unbind() {
        removePropertyChangeListener();
        removeTextComponentChangeListener();
    }

    private void bind() {
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
        textChangedListener = this::textChanged;
        listenableTextComponent.addChangeListener(textChangedListener);
    }

    private void removeTextComponentChangeListener() {
        if (textChangedListener != null) {
            listenableTextComponent.removeChangeListener(textChangedListener);
            textChangedListener = null;
        }
    }

    private void textChanged(@Nullable final String valueBefore, @Nullable final String valueAfter) {
        if (!readonly) {
            try {
                final T value = valueConverter.convertToValue(listenableTextComponent.getTextComponent().getText());
                removePropertyChangeListener();
                boundProperty.setValue(value);
                setPropertyChangeListener();
                onSuccess();
            } catch (final IllegalArgumentException iae) {
                onError(iae);
            }
        }
    }

    private void onSuccess() {
        if (errorListener != null) {
            errorListener.onParseSuccess();
        } else {
            listenableTextComponent.getTextComponent().setBorder(BorderFactory.createEmptyBorder());
        }
    }

    private void onError(final IllegalArgumentException iae) {
        if (errorListener != null) {
            errorListener.onParseError(iae);
        } else {
            listenableTextComponent.getTextComponent().setBorder(BorderFactory.createLineBorder(Color.RED));
        }
    }

    private void valueChanged(@Nullable final T valueBefore, @Nullable final T valueAfter) {
        removeTextComponentChangeListener();
        listenableTextComponent.getTextComponent().setText(String.valueOf(valueAfter));
        setTextComponentChangeListener();
    }
}
