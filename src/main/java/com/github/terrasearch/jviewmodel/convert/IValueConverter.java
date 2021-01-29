package com.github.terrasearch.jviewmodel.convert;

import org.jetbrains.annotations.Nullable;

import java.text.ParseException;

public interface IValueConverter<T> {
    T convertToValue(@Nullable String value) throws ParseException;
}
