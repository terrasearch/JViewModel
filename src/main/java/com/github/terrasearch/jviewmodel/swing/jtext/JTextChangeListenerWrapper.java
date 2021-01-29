package com.github.terrasearch.jviewmodel.swing.jtext;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;

class JTextChangeListenerWrapper {
    private final JTextComponent textComponent;
    private DocumentListener textChangeListener;
    private PropertyChangeListener documentChangeListener;

    /**
     * @param textComponent any text component, such as a {@link JTextField}
     *                      or {@link JTextArea}
     */
    public JTextChangeListenerWrapper(JTextComponent textComponent) {
        this.textComponent = Objects.requireNonNull(textComponent);
    }

    private static PropertyChangeListener createDocumentChangeHandler(DocumentListener textChangeListener) {
        return (PropertyChangeEvent e) -> {
            Document oldDocument = (Document) e.getOldValue();
            Document newDocument = (Document) e.getNewValue();
            if (oldDocument != null) oldDocument.removeDocumentListener(textChangeListener);
            if (newDocument != null) newDocument.addDocumentListener(textChangeListener);
            textChangeListener.changedUpdate(null);
        };
    }

    public void addChangeListener(PropertyChangeListener changeListener) {
        Objects.requireNonNull(changeListener);

        textChangeListener = new DocumentTextChangeListener(textComponent, changeListener);
        documentChangeListener = createDocumentChangeHandler(textChangeListener);

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
