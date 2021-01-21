package com.github.terrasearch.viewmodelproperties;

public interface IPropertyChangedListener<T> {
    void onPropertyChanged();

    void onPropertyChanged(T value);
}
