package com.github.terrasearch.jviewmodel.convert;

import org.jetbrains.annotations.Nullable;

import java.text.NumberFormat;
import java.text.ParseException;

public class LongConverter implements IValueConverter<Long> {
    @Override
    public Long convertToValue(@Nullable String value) throws ParseException {
        return (Long) NumberFormat.getInstance().parse(value);
    }
}
