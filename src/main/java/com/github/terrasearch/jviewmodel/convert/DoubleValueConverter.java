package com.github.terrasearch.jviewmodel.convert;

import org.jetbrains.annotations.Nullable;

public class DoubleValueConverter implements IValueConverter<Double> {
    @Override
    public Double convertToValue(@Nullable final String value) throws NumberFormatException {
        if (value != null && !value.isEmpty()) {
            return Double.parseDouble(value);
        } else {
            return 0.0;
        }
    }
}