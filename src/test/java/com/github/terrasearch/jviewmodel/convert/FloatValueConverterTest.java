package com.github.terrasearch.jviewmodel.convert;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FloatValueConverterTest {
    @Test
    public void convertTest() {
        final Float floatValue = 1.0f;
        final String stringValue = "1.0";
        final Float convertedFloat = new FloatValueConverter().convertToValue(stringValue);
        assertEquals(floatValue, convertedFloat);
    }

    @Test
    public void convertNull() {
        final Float floatValue = 0.0f;
        final Float convertedFloat = new FloatValueConverter().convertToValue(null);
        assertEquals(floatValue, convertedFloat);
    }

    @Test
    public void convertEmptyString() {
        final Float floatValue = 0.0f;
        final Float convertedFloat = new FloatValueConverter().convertToValue("");
        assertEquals(floatValue, convertedFloat);
    }
}
