package com.github.terrasearch.jviewmodel.swing.jtext;

import com.github.terrasearch.jviewmodel.property.IPropertyChangeListener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * A bridge from {@link DocumentListener} to {@link IPropertyChangeListener}.
 */
class DocumentValueChangeBridge implements DocumentListener {
    private final JTextComponent textComponent;
    private final IPropertyChangeListener<String> changeListener;
    private String lastText;

    /**
     * Installs a listener to receive notification when the text of any
     * {@link JTextComponent} is changed. Internally, it installs a
     * {@link IPropertyChangeListener} on the text component's {@link Document}
     *
     * @param textComponent  the {@link JTextComponent} which is listened to
     * @param changeListener a {@link IPropertyChangeListener} to receive change notifications
     */
    public DocumentValueChangeBridge(@NotNull final JTextComponent textComponent, @NotNull final IPropertyChangeListener<String> changeListener) {
        this.textComponent = Objects.requireNonNull(textComponent);
        this.changeListener = Objects.requireNonNull(changeListener);
        lastText = textComponent.getText();
    }

    @Override
    public void insertUpdate(@Nullable final DocumentEvent e) {
        changedUpdate(e);
    }

    @Override
    public void removeUpdate(@Nullable final DocumentEvent e) {
        changedUpdate(e);
    }

    @Override
    public void changedUpdate(@Nullable final DocumentEvent e) {
        try {
            SwingUtilities.invokeAndWait(this::announceChange);
        } catch (InterruptedException | InvocationTargetException interruptedException) {
            // TODO: Log
        }
    }

    public IPropertyChangeListener<String> getChangeListener() {
        return changeListener;
    }

    private synchronized void announceChange() {
        if (valueHasChanged()) {
            changeListener.onPropertyChanged(lastText, textComponent.getText());
            lastText = textComponent.getText();
        }
    }

    private boolean valueHasChanged() {
        final boolean valueStayedNull = lastText == null && textComponent.getText() == null;
        if (valueStayedNull) return false;
        // If last value is null, value has changed because both can't be null anymore
        return lastText == null || !lastText.equals(textComponent.getText());
    }
}
