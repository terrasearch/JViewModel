package com.github.terrasearch.jviewmodel.convert;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegerValueConverterTest {
    @Test
    public void convertTest() {
        final Integer integerValue = 1;
        final String stringValue = "1";
        final Integer convertedInteger = new IntegerValueConverter().convertToValue(stringValue);
        assertEquals(integerValue, convertedInteger);
    }

    @Test
    public void convertNull() {
        final Integer integerValue = 0;
        final Integer convertedInteger = new IntegerValueConverter().convertToValue(null);
        assertEquals(integerValue, convertedInteger);
    }

    @Test
    public void convertEmptyString() {
        final Integer integerValue = 0;
        final Integer convertedInteger = new IntegerValueConverter().convertToValue("");
        assertEquals(integerValue, convertedInteger);
    }
}
