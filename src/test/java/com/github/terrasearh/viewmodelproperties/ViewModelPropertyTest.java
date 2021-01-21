package com.github.terrasearh.viewmodelproperties;

import com.github.terrasearch.viewmodelproperties.IPropertyChangedListener;
import com.github.terrasearch.viewmodelproperties.IViewModelProperty;
import com.github.terrasearch.viewmodelproperties.ViewModelProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ViewModelPropertyTest {
    private final int propertyInitialValue = 0;
    private final IViewModelProperty<Integer> property = new ViewModelProperty<>(propertyInitialValue);
    @Mock
    private IPropertyChangedListener<Integer> propertyChangedListener;

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
        verify(propertyChangedListener, times(1)).onPropertyChanged();
        verify(propertyChangedListener, times(1)).onPropertyChanged(2);
        verify(propertyChangedListener, times(0)).onPropertyChanged(1);
    }
}
