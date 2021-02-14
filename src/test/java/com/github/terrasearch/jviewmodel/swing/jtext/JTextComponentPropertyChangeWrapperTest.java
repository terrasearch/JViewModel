package com.github.terrasearch.jviewmodel.swing.jtext;

import com.github.terrasearch.jviewmodel.property.IPropertyChangeListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class JTextComponentPropertyChangeWrapperTest {
    @Mock
    private IPropertyChangeListener<String> propertyChangedListener;

    @BeforeEach
    public void initProperty() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addChangeListener() {
        JTextComponentPropertyChangeWrapper wrapper = new JTextComponentPropertyChangeWrapper(new JTextField(""));
        wrapper.addChangeListener(propertyChangedListener);
        wrapper.getTextComponent().setText("test");
        verify(propertyChangedListener, times(1)).onPropertyChanged("", "test");
    }

    @Test
    void removeChangeListener() {
        JTextComponentPropertyChangeWrapper wrapper = new JTextComponentPropertyChangeWrapper(new JTextField(""));
        wrapper.addChangeListener(propertyChangedListener);
        wrapper.removeChangeListener(propertyChangedListener);
        wrapper.getTextComponent().setText("test");
        verify(propertyChangedListener, times(0)).onPropertyChanged("", "test");
    }

    @Test
    void getTextComponent() {
        final JTextField textField = new JTextField("");
        final JTextComponentPropertyChangeWrapper wrapper = new JTextComponentPropertyChangeWrapper(textField);
        assertEquals(wrapper.getTextComponent(), textField);
    }

    @Test
    void documentChanged() {
        JTextComponentPropertyChangeWrapper wrapper = new JTextComponentPropertyChangeWrapper(new JTextField(""));
        wrapper.addChangeListener(propertyChangedListener);
        wrapper.getTextComponent().setDocument(new JTextField("").getDocument());
        wrapper.getTextComponent().setText("test");
        verify(propertyChangedListener, times(1)).onPropertyChanged("", "test");

    }
}