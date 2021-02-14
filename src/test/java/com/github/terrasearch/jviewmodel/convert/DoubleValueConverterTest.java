package com.github.terrasearch.jviewmodel.convert;

import org.junit.jupiter.api.Test;

class DoubleValueConverterTest {
    private final ConverterTest<Double> converterTest = new ConverterTest<>(new DoubleValueConverter());

    @Test
    public void convertTest() {
        final Double expectedValue = 123.456;
        converterTest.convert(expectedValue);
    }

    @Test
    public void convertNull() {
        final Double expectedValue = 0.0;
        converterTest.convertNull(expectedValue);
    }

    @Test
    public void convertEmptyString() {
        final Double expectedValue = 0.0;
        converterTest.convertEmptyString(expectedValue);
    }
}
