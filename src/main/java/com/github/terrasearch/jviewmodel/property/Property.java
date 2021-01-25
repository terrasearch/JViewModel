package com.github.terrasearch.jviewmodel.property;

import java.util.ArrayList;
import java.util.List;

/**
 * A property, which announces it's changes to all subscribers. You can subscribe to change notification's through
 * {@link #registerPropertyChangedListener(IPropertyChangeListener)}
 *
 * @param <T> Type of the property, which will be stored. Using a JavaBeans is recommended.
 */
public class Property<T> implements IProperty<T> {
    private final List<IPropertyChangeListener<T>> propertyChangedListenerList = new ArrayList<>();
    private T value;

    /**
     * Creates an instance of a property
     *
     * @param initialValue the initial value of the property
     */
    public Property(final T initialValue) {
        value = initialValue;
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public void setValue(final T value) {
        final T oldValue = this.value;
        this.value = value;
        propertyChangedListenerList.forEach(propertyChangedListener -> propertyChangedListener.onPropertyChanged(oldValue, value));
    }

    @Override
    public void registerPropertyChangedListener(final IPropertyChangeListener<T> propertyChangedListener) {
        propertyChangedListenerList.add(propertyChangedListener);
    }

    public void removePropertyChangedListener(final IPropertyChangeListener<T> propertyChangedListener) {
        propertyChangedListenerList.remove(propertyChangedListener);
    }
}
