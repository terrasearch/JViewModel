package com.github.terrasearch.jviewmodel.convert;

import org.junit.jupiter.api.Test;

class IntegerValueConverterTest {
    private final ConverterTest<Integer> converterTest = new ConverterTest<>(new IntegerValueConverter());
    @Test
    public void convertTest() {
        final Integer expectedValue = 1;
        converterTest.convert(expectedValue);
    }

    @Test
    public void convertNull() {
        final Integer expectedValue = 0;
        converterTest.convertNull(expectedValue);
    }

    @Test
    public void convertEmptyString() {
        final Integer expectedValue = 0;
        converterTest.convertEmptyString(expectedValue);
    }
}
