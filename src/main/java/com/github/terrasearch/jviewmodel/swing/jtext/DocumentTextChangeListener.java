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


public class DocumentTextChangeListener implements DocumentListener {
    private final JTextComponent textComponent;
    private final PropertyChangeListener changeListener;
    private int lastChange = 0, lastNotifiedChange = 0;
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
    public DocumentTextChangeListener(@NotNull final JTextComponent textComponent,
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
        synchronized (this) {
            lastChange++;
            SwingUtilities.invokeLater(() -> {
                synchronized (DocumentTextChangeListener.this) {
                    if (lastNotifiedChange != lastChange) {
                        lastNotifiedChange = lastChange;
                        changeListener.propertyChange(new PropertyChangeEvent(textComponent, "text", lastText,
                                textComponent.getText()));
                        lastText = textComponent.getText();
                    }
                }
            });
        }
    }
}
