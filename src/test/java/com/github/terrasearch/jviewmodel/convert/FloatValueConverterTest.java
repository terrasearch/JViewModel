package com.github.terrasearch.jviewmodel.convert;

import org.junit.jupiter.api.Test;

class FloatValueConverterTest {
    private final ConverterTest<Float> converterTest = new ConverterTest<>(new FloatValueConverter());

    @Test
    public void convert() {
        final Float expectedValue = 1.23f;
        converterTest.convert(expectedValue);
    }

    @Test
    public void convertNull() {
        final Float expectedValue = 0.0f;
        converterTest.convertNull(expectedValue);
    }

    @Test
    public void convertEmptyString() {
        final Float expectedValue = 0.0f;
        converterTest.convertEmptyString(expectedValue);
    }
}
