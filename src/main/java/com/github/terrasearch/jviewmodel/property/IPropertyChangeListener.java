package com.github.terrasearch.jviewmodel.property;

import org.jetbrains.annotations.Nullable;

/**
 * A listener, which listens to changes to a {@link Property}
 *
 * @param <T> type of the value of the property
 */
public interface IPropertyChangeListener<T> {
    /**
     * Will be called, after the property has changed.
     *
     * @param valueBefore value before the change
     * @param valueAfter  value after the change
     */
    void onPropertyChanged(@Nullable T valueBefore, @Nullable T valueAfter);
}
