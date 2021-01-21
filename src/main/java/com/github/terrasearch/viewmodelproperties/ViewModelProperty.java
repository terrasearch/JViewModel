package com.github.terrasearch.viewmodelproperties;

import java.util.ArrayList;
import java.util.List;

public class ViewModelProperty<T> implements IViewModelProperty<T> {
    private List<IPropertyChangedListener<T>> propertyChangedListenerList = new ArrayList<>();
    private T value;

    public ViewModelProperty(T initialValue) {
        value = initialValue;
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public void setValue(T value) {
        this.value = value;
        propertyChangedListenerList.forEach(propertyChangedListener -> {
            propertyChangedListener.onPropertyChanged();
            propertyChangedListener.onPropertyChanged(value);
        });
    }

    @Override
    public void registerPropertyChangedListener(IPropertyChangedListener<T> propertyChangedListener) {
        propertyChangedListenerList.add(propertyChangedListener);
    }
}
