package com.github.terrasearch.jviewmodel.convert;

import org.jetbrains.annotations.Nullable;

import java.text.ParseException;

public class IntegerConverter implements IValueConverter<Integer> {
    @Override
    public Integer convertToValue(@Nullable final String value) throws ParseException {
        try {
            if (value != null && !"".equals(value)) {
                return Integer.parseInt(value);
            } else {
                return 0;
            }
        } catch (NumberFormatException nfe) {
            throw new ParseException(value, 0);
        }
    }
}
