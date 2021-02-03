package com.github.terrasearch.jviewmodel.convert;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LongValueConverterTest {
    @Test
    public void convertTest() {
        final Long doubleValue = 1L;
        final String stringValue = "1";
        final Long convertedDouble = new LongValueConverter().convertToValue(stringValue);
        assertEquals(doubleValue, convertedDouble);
    }

    @Test
    public void convertNull() {
        final Long doubleValue = 0L;
        final Long convertedDouble = new LongValueConverter().convertToValue(null);
        assertEquals(doubleValue, convertedDouble);
    }

    @Test
    public void convertEmptyString() {
        final Long doubleValue = 0L;
        final Long convertedDouble = new LongValueConverter().convertToValue("");
        assertEquals(doubleValue, convertedDouble);
    }
}
