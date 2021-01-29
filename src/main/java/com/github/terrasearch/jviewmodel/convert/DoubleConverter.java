package com.github.terrasearch.jviewmodel.convert;

import org.jetbrains.annotations.Nullable;

import java.text.NumberFormat;
import java.text.ParseException;

public class DoubleConverter implements IValueConverter<Double> {
    @Override
    public Double convertToValue(@Nullable final String value) throws ParseException {
        return (Double) NumberFormat.getInstance().parse(value);
    }
}