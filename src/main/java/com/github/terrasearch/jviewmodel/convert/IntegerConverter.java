package com.github.terrasearch.jviewmodel.convert;

import java.text.ParseException;

public class IntegerConverter implements IValueConverter<Integer> {
    @Override
    public Integer convertToValue(String value) throws ParseException {
        try {
            return Integer.parseInt(value);
        } catch (final NumberFormatException nfe) {
            throw new ParseException(nfe.getMessage(), 0);
        }
    }
}
