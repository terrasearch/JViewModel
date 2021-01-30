package com.github.terrasearch.jviewmodel.convert;

import org.jetbrains.annotations.Nullable;

/**
 * Interface for value conversion from String to value
 * Make sure to override {@link Object#toString()} of {@link T}, as it's used for the other way around
 *
 * @param <T> Type of object which is converted
 */
public interface IValueConverter<T> {
    T convertToValue(@Nullable String value) throws IllegalArgumentException;
}
