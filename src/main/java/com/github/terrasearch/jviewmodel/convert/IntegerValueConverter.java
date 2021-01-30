package com.github.terrasearch.jviewmodel.convert;

import org.jetbrains.annotations.Nullable;

public class IntegerValueConverter implements IValueConverter<Integer> {
    @Override
    public Integer convertToValue(@Nullable final String value) throws NumberFormatException {
        if (value != null && !value.isEmpty()) {
            return Integer.parseInt(value);
        } else {
            return 0;
        }
    }
}
