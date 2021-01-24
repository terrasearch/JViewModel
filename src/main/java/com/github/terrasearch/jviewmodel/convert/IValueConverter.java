package com.github.terrasearch.jviewmodel.convert;

import java.text.ParseException;

public interface IValueConverter<T> {
    T convertToValue(String value) throws ParseException;
}
