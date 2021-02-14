package com.github.terrasearch.jviewmodel.convert;

import org.junit.jupiter.api.Test;

class LongValueConverterTest {
    private final ConverterTest<Long> converterTest = new ConverterTest<>(new LongValueConverter());
    @Test
    public void convertTest() {
        final Long expectedValue = 1L;
        converterTest.convert(expectedValue);
    }

    @Test
    public void convertNull() {
        final Long expectedValue = 0L;
        converterTest.convertNull(expectedValue);
    }

    @Test
    public void convertEmptyString() {
        final Long expectedValue = 0L;
        converterTest.convertEmptyString(expectedValue);
    }
}
