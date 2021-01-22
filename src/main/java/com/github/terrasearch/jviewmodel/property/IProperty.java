package com.github.terrasearch.jviewmodel.property;

/**
 * Describes a property which notifies it's subscribers
 *
 * @param <T> DataType of the Property, which will be propagated through {@link IPropertyChangedListener#onPropertyChanged(T, T)}
 */
interface IProperty<T> {
    /**
     * @return value of the property
     */
    T getValue();

    /**
     * Will call {@link IPropertyChangedListener#onPropertyChanged(Object, Object)} of all registered
     * {@link IPropertyChangedListener} after the value has changed, which are registered through
     * {@link #registerPropertyChangedListener(IPropertyChangedListener)}
     *
     * @param value value of the property
     */
    void setValue(T value);

    /**
     * Registering a {@link IPropertyChangedListener}
     *
     * @param propertyChangedListener A {@link IPropertyChangedListener}, which will be called when {@link #setValue(T)} is called
     */
    void registerPropertyChangedListener(IPropertyChangedListener<T> propertyChangedListener);
}
