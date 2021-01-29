package com.github.terrasearch.jviewmodel.swing.jtext;

import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class JTextComponentChangeWrapper {
    private final JTextComponent textComponent;
    private final List<JTextComponentChangeListener> textChangeListeners = new ArrayList<>();

    /**
     * A JText wrapper, which uses {@link PropertyChangeListener} to propagate changes
     *
     * @param textComponent any text component, such as a {@link JTextField} or {@link JTextArea}
     */
    public JTextComponentChangeWrapper(JTextComponent textComponent) {
        this.textComponent = Objects.requireNonNull(textComponent);
        textComponent.addPropertyChangeListener("document", getDocumentChangeHandler());
    }

    public void addChangeListener(PropertyChangeListener changeListener) {
        Objects.requireNonNull(changeListener);
        final JTextComponentChangeListener textChangeListener = new JTextComponentChangeListener(textComponent, changeListener);
        textChangeListeners.add(textChangeListener);

        final Document document = textComponent.getDocument();
        if (document != null) document.addDocumentListener(textChangeListener);
    }

    public void removeChangeListener(PropertyChangeListener changeListener) {
        Objects.requireNonNull(changeListener);
        textChangeListeners.forEach(textChangeListener -> {
            if (textChangeListener.getChangeListener() == changeListener) {
                textComponent.getDocument().removeDocumentListener(textChangeListener);
            }
        });
    }

    private PropertyChangeListener getDocumentChangeHandler() {
        return (PropertyChangeEvent e) -> textChangeListeners.forEach(textChangeListener -> {
            Document oldDocument = (Document) e.getOldValue();
            Document newDocument = (Document) e.getNewValue();
            if (oldDocument != null) oldDocument.removeDocumentListener(textChangeListener);
            if (newDocument != null) newDocument.addDocumentListener(textChangeListener);
            textChangeListener.changedUpdate(null);
        });
    }
}
