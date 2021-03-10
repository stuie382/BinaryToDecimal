package com.stuart.converter;

import com.stuart.dto.ConversionResult;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class BinaryToDecimalConverterTest {

    private Converter uut;

    @BeforeEach
    void setUp() {
        uut = new BinaryToDecimalConverter();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testUnsuccessfulForNullInput() {
        ConversionResult result = uut.convert(null);
        assertFalse(result.wasSuccessful());
    }

    @Test
    void testUnsuccessfulForEmptyInput() {
        ConversionResult result = uut.convert("");
        assertFalse(result.wasSuccessful());
    }

    @Test
    void testUnsuccessfulForNewLineInput() {
        ConversionResult result = uut.convert("\\n");
        assertFalse(result.wasSuccessful());
    }

    @Test
    void testUnsuccessfulForNonBinaryNumberInput() {
        ConversionResult result = uut.convert("2");
        assertFalse(result.wasSuccessful());
    }

    @Test
    void testUnsuccessfulForAlphaInput() {
        ConversionResult result = uut.convert("abc");
        assertFalse(result.wasSuccessful());
    }

    @Test
    void testOutputZeroWhenInputZero() {
        ConversionResult result = uut.convert("0");
        assertTrue(result.wasSuccessful());
        assertEquals("0", result.getConversionResult());
    }

    @Test
    void testOutputOneWhenInputOne() {
        ConversionResult result = uut.convert("1");
        assertTrue(result.wasSuccessful());
        assertEquals("1", result.getConversionResult());
    }

    @Test
    void testOutput255WhenInputMax() {
        ConversionResult result = uut.convert("11111111");
        assertTrue(result.wasSuccessful());
        assertEquals("255", result.getConversionResult());
    }
}