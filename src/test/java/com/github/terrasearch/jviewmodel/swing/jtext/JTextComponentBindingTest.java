package com.github.terrasearch.jviewmodel.swing.jtext;

import com.github.terrasearch.jviewmodel.convert.IntegerValueConverter;
import com.github.terrasearch.jviewmodel.property.IPropertyChangeListener;
import com.github.terrasearch.jviewmodel.property.Property;
import com.github.terrasearch.jviewmodel.swing.IParseErrorListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class JTextComponentBindingTest {
    @Mock
    private IPropertyChangeListener<Integer> propertyChangedListener;
    @Mock
    private IParseErrorListener listener;

    @BeforeEach
    public void initProperty() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void setErrorListener() {
        final JTextField textField = new JTextField();
        final JTextComponentBinding<Integer> binding = new JTextComponentBinding<>(textField);
        final Property<Integer> intProperty = new Property<>(0);
        intProperty.registerPropertyChangedListener(propertyChangedListener);
        binding.bind(intProperty, new IntegerValueConverter());
        binding.setReadOnly(false);
        binding.setErrorListener(listener);
        textField.setText("someText");
        verify(listener, times(1)).onParseError(any());
    }

    @Test
    void bindTwoWay() {
        final JTextField textField = new JTextField();
        final JTextComponentBinding<Integer> binding = new JTextComponentBinding<>(textField);
        final Property<Integer> intProperty = new Property<>(0);
        intProperty.registerPropertyChangedListener(propertyChangedListener);
        binding.bind(intProperty, new IntegerValueConverter());
        binding.setReadOnly(false);
        textField.setText("1");
        verify(propertyChangedListener, times(1)).onPropertyChanged(0, 1);
    }

    @Test
    void bindOneWay() {
        final JTextField textField = new JTextField();
        final JTextComponentBinding<Integer> binding = new JTextComponentBinding<>(textField);
        final Property<Integer> intProperty = new Property<>(0);
        binding.bind(intProperty, new IntegerValueConverter());
        intProperty.setValue(1);
        assertEquals("1", textField.getText());
    }

    @Test
    void unbind() {
        final JTextField textField = new JTextField();
        final JTextComponentBinding<Integer> binding = new JTextComponentBinding<>(textField);
        final Property<Integer> intProperty = new Property<>(0);
        binding.bind(intProperty, new IntegerValueConverter());
        binding.unbind();
        intProperty.setValue(1);
        assertEquals("0", textField.getText());
    }
}