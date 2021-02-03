package com.github.terrasearch.jviewmodel.property;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class PropertyTest {
    private final int propertyInitialValue = 0;
    private final IProperty<Integer> property = new Property<>(propertyInitialValue);
    @Mock
    private IPropertyChangeListener<Integer> propertyChangedListener;

    @BeforeEach
    public void initProperty() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void propertyChanged() {
        property.setValue(1);
        assertEquals(1, property.getValue());
    }

    @Test
    public void propertyChangedListenerCalled() {
        property.registerPropertyChangedListener(propertyChangedListener);
        property.setValue(2);
        verify(propertyChangedListener, times(1)).onPropertyChanged(0, 2);
        verify(propertyChangedListener, times(0)).onPropertyChanged(0, 1);
    }
}
