package com.github.terrasearch.jviewmodel.swing.jtext;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;


public class JTextComponentChangeListener implements DocumentListener {
    private final JTextComponent textComponent;
    private final PropertyChangeListener changeListener;
    private String lastText;

    /**
     * Installs a listener to receive notification when the text of any
     * {@code JTextComponent} is changed. Internally, it installs a
     * {@link DocumentListener} on the text component's {@link Document},
     * and a {@link PropertyChangeListener} on the text component to detect
     * if the {@code Document} itself is replaced.
     *
     * @param changeListener a listener to receive {@link ChangeEvent}s
     *                       when the text is changed; the source object for the events
     *                       will be the text component
     * @throws NullPointerException if either parameter is null
     */
    public JTextComponentChangeListener(@NotNull final JTextComponent textComponent,
                                        @NotNull final PropertyChangeListener changeListener) {
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
        SwingUtilities.invokeLater(this::announceChange);
    }

    public PropertyChangeListener getChangeListener() {
        return changeListener;
    }

    private synchronized void announceChange() {
        if (valueHasChanged()) {
            changeListener.propertyChange(new PropertyChangeEvent(textComponent, "text", lastText,
                    textComponent.getText()));
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
