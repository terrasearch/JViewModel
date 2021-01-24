package com.github.terrasearch.jviewmodel.convert;

import java.text.NumberFormat;
import java.text.ParseException;

public class LongConverter implements IValueConverter<Long> {
    @Override
    public Long convertToValue(String value) throws ParseException {
        return (Long) NumberFormat.getInstance().parse(value);
    }
}
