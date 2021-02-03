package com.github.terrasearch.jviewmodel.swing.jtext;

import com.github.terrasearch.jviewmodel.property.IPropertyChangeListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.*;
import javax.swing.event.DocumentEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class DocumentValueChangeBridgeTest {
    private final JTextField textField = new JTextField("");
    @Mock
    private IPropertyChangeListener<String> propertyChangedListener;
    @Mock
    private DocumentEvent documentEvent;

    @BeforeEach
    public void initProperty() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void changedUpdate() {
        final DocumentValueChangeBridge bridge = new DocumentValueChangeBridge(textField, propertyChangedListener);
        textField.setText("test");
        bridge.changedUpdate(documentEvent);
        verify(propertyChangedListener, times(1)).onPropertyChanged("", "test");
    }

    @Test
    void getChangeListener() {
        final DocumentValueChangeBridge bridge = new DocumentValueChangeBridge(textField, propertyChangedListener);
        assertEquals(bridge.getChangeListener(), propertyChangedListener);
    }
}