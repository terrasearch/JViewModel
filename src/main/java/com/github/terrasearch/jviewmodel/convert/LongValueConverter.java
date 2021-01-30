package com.github.terrasearch.jviewmodel.convert;

import org.jetbrains.annotations.Nullable;

public class LongValueConverter implements IValueConverter<Long> {
    @Override
    public Long convertToValue(@Nullable String value) throws NumberFormatException {
        if (value != null && !value.isEmpty()) {
            return Long.parseLong(value);
        } else {
            return 0L;
        }
    }
}
