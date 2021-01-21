package com.github.terrasearch.viewmodelproperties;

/**
 * A Property
 *
 * @param <T> DataType of the Property
 */
public interface IViewModelProperty<T> {
    T getValue();

    void setValue(T value);

    void registerPropertyChangedListener(IPropertyChangedListener<T> propertyChangedListener);
}
