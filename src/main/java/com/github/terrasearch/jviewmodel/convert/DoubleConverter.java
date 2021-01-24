package com.github.terrasearch.jviewmodel.convert;

import java.text.NumberFormat;
import java.text.ParseException;

public class DoubleConverter implements IValueConverter<Double> {
    @Override
    public Double convertToValue(String value) throws ParseException {
        return (Double) NumberFormat.getInstance().parse(value);
    }
}