package com.github.terrasearch.jviewmodel.swing;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;

class JTextSimpleChangeListenerWrapper {
    private final JTextComponent textComponent;
    private DocumentListener textChangeListener;
    private PropertyChangeListener documentChangeListener;

    /**
     * @param textComponent any text component, such as a {@link JTextField}
     *                      or {@link JTextArea}
     */
    public JTextSimpleChangeListenerWrapper(JTextComponent textComponent) {
        this.textComponent = Objects.requireNonNull(textComponent);
    }

    private static PropertyChangeListener createDocumentChangeHandler(DocumentListener textChangeListener) {
        return (PropertyChangeEvent e) -> {
            Document d1 = (Document) e.getOldValue();
            Document d2 = (Document) e.getNewValue();
            if (d1 != null) d1.removeDocumentListener(textChangeListener);
            if (d2 != null) d2.addDocumentListener(textChangeListener);
            textChangeListener.changedUpdate(null);
        };
    }

    private static DocumentListener createDocumentTextChangeHandler(final JTextComponent textComponent,
                                                                    final PropertyChangeListener changeListener) {
        return new DocumentListener() {
            private int lastChange = 0, lastNotifiedChange = 0;
            private String lastText = textComponent.getText();

            @Override
            public void insertUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                lastChange++;
                SwingUtilities.invokeLater(() -> {
                    if (lastNotifiedChange != lastChange) {
                        lastNotifiedChange = lastChange;

                        changeListener.propertyChange(new PropertyChangeEvent(textComponent, "text",
                                lastText, textComponent.getText()));
                        lastText = textComponent.getText();
                    }
                });
            }
        };
    }

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
    public void addChangeListener(PropertyChangeListener changeListener) {
        Objects.requireNonNull(changeListener);

        final DocumentListener textChangeListener = createDocumentTextChangeHandler(textComponent, changeListener);
        final PropertyChangeListener documentChangeListener = createDocumentChangeHandler(textChangeListener);

        textComponent.addPropertyChangeListener("document", documentChangeListener);
        final Document document = textComponent.getDocument();
        if (document != null) document.addDocumentListener(textChangeListener);
    }

    public void removeChangeListener() {
        if (textChangeListener != null && documentChangeListener != null) {
            textComponent.getDocument().removeDocumentListener(textChangeListener);
            textComponent.removePropertyChangeListener("document", documentChangeListener);
        }
    }

    public JTextComponent getTextComponent() {
        return textComponent;
    }
}
