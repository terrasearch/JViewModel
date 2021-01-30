package com.github.terrasearch.jviewmodel.swing.jtext;

import com.github.terrasearch.jviewmodel.property.IPropertyChangeListener;

import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Wraps a {@link JTextComponent} to use {@link IPropertyChangeListener<String>} to propagate changes
 * Use {@link #addChangeListener(IPropertyChangeListener)} to add a change listener
 * Use {@link #removeChangeListener(IPropertyChangeListener)} to remove a change listener
 */
class TextChangeJTextComponentWrapper {
    private static final String documentPropertyName = "document";
    private final JTextComponent textComponent;
    private final List<DocumentValueChangeListener> textChangeListeners = new ArrayList<>();

    public TextChangeJTextComponentWrapper(final JTextComponent textComponent) {
        this.textComponent = textComponent;
        textComponent.addPropertyChangeListener(documentPropertyName, this::documentChanged);
    }

    public void addChangeListener(final IPropertyChangeListener<String> changeListener) {
        final DocumentValueChangeListener textChangeListener = new DocumentValueChangeListener(textComponent, Objects.requireNonNull(changeListener));
        textChangeListeners.add(textChangeListener);

        final Document document = textComponent.getDocument();
        if (document != null) document.addDocumentListener(textChangeListener);
    }

    public void removeChangeListener(final IPropertyChangeListener<String> changeListener) {
        Objects.requireNonNull(changeListener);
        textChangeListeners.forEach(textChangeListener -> {
            if (changeListener == textChangeListener.getChangeListener()) {
                textComponent.getDocument().removeDocumentListener(textChangeListener);
            }
        });
    }

    public JTextComponent getTextComponent() {
        return textComponent;
    }

    private void documentChanged(PropertyChangeEvent e) {
        final Document oldDocument = (Document) e.getOldValue();
        if (oldDocument != null) textChangeListeners.forEach(oldDocument::removeDocumentListener);

        final Document newDocument = (Document) e.getNewValue();
        if (newDocument != null) textChangeListeners.forEach(newDocument::addDocumentListener);
    }
}
