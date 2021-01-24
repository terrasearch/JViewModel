package com.github.terrasearch.jviewmodel.property;

/**
 * Describes a property which notifies it's subscribers
 *
 * @param <T> DataType of the Property, which will be propagated through {@link IPropertyChangeListener#onPropertyChanged(T, T)}
 */
interface IProperty<T> {
    /**
     * @return value of the property
     */
    T getValue();

    /**
     * Will call {@link IPropertyChangeListener#onPropertyChanged(Object, Object)} of all registered
     * {@link IPropertyChangeListener} after the value has changed, which are registered through
     * {@link #registerPropertyChangedListener(IPropertyChangeListener)}
     *
     * @param value value of the property
     */
    void setValue(T value);

    /**
     * Registering a {@link IPropertyChangeListener}
     *
     * @param propertyChangedListener A {@link IPropertyChangeListener}, which will be called when {@link #setValue(T)} is called
     */
    void registerPropertyChangedListener(IPropertyChangeListener<T> propertyChangedListener);
}
