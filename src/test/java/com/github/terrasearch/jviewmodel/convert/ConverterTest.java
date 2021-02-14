package com.github.terrasearch.jviewmodel.convert;

import org.apache.logging.log4j.util.Strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConverterTest<T> {
    private final IValueConverter<T> converter;

    public ConverterTest(final IValueConverter<T> converter) {
        this.converter = converter;
    }

    public void convert(final T expectedValue) {
        final T actualValue = converter.convertToValue(expectedValue.toString());
        assertEquals(expectedValue, actualValue, "Could not convert " + expectedValue + " correctly");
    }

    public void convertNull(final T expectedValue) {
        final T actualValue = converter.convertToValue(null);
        assertEquals(expectedValue, actualValue, "Could not convert null correctly");
    }

    public void convertEmptyString(final T expectedValue) {
        final T actualValue = converter.convertToValue(Strings.EMPTY);
        assertEquals(expectedValue, actualValue, "Could not convert empty String correctly");
    }
}
