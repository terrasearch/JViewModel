package com.github.terrasearch.jviewmodel.convert;

import org.jetbrains.annotations.Nullable;

import java.text.NumberFormat;
import java.text.ParseException;

public class IntegerConverter implements IValueConverter<Integer> {
    @Override
    public Integer convertToValue(@Nullable final String value) throws ParseException {
        return (Integer) NumberFormat.getInstance().parse(value);
    }
}
