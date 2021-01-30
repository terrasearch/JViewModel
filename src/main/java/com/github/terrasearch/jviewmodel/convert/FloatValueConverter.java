package com.github.terrasearch.jviewmodel.convert;

import org.jetbrains.annotations.Nullable;

public class FloatValueConverter implements IValueConverter<Float> {
    @Override
    public Float convertToValue(@Nullable String value) throws NumberFormatException {
        if (value != null && !value.isEmpty()) {
            return Float.parseFloat(value);
        } else {
            return 0.0f;
        }
    }
}
