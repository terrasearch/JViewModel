package com.github.terrasearch.jviewmodel.convert;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoubleValueConverterTest {
    @Test
    public void convertTest() {
        final Double doubleValue = 1.0;
        final String stringValue = "1.0";
        final Double convertedDouble = new DoubleValueConverter().convertToValue(stringValue);
        assertEquals(doubleValue, convertedDouble);
    }

    @Test
    public void convertNull() {
        final Double doubleValue = 0.0;
        final Double convertedDouble = new DoubleValueConverter().convertToValue(null);
        assertEquals(doubleValue, convertedDouble);
    }

    @Test
    public void convertEmptyString() {
        final Double doubleValue = 0.0;
        final Double convertedDouble = new DoubleValueConverter().convertToValue("");
        assertEquals(doubleValue, convertedDouble);
    }
}
