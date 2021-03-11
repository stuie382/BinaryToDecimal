package com.stuart.converter;

import com.stuart.dto.ConversionResult;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DecimalToBinaryConverterTest {

    private Converter uut;

    @BeforeEach
    void setUp() {
        uut = new DecimalToBinaryConverter();
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void testUnsuccessfulForNullInput() {
        ConversionResult result = uut.convert(null);
        assertFalse(result.wasSuccessful());
        assertEquals("Input is not a valid number", result.getErrorMessage());
    }

    @Test
    void testUnsuccessfulForEmptyInput() {
        ConversionResult result = uut.convert("");
        assertFalse(result.wasSuccessful());
        assertEquals("Input is not a valid number", result.getErrorMessage());
    }

    @Test
    void testUnsuccessfulForNewLineInput() {
        ConversionResult result = uut.convert("\n");
        assertFalse(result.wasSuccessful());
        assertEquals("Input is not a valid number", result.getErrorMessage());
    }

    @Test
    void testUnsuccessfulForAlphaInput() {
        ConversionResult result = uut.convert("abc");
        assertFalse(result.wasSuccessful());
        assertEquals("Input is not a valid number", result.getErrorMessage());
    }

    @Test
    void testUnsuccessfulForNegativeInput() {
        ConversionResult result = uut.convert("-123");
        assertFalse(result.wasSuccessful());
        assertEquals("Input must be positive", result.getErrorMessage());
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
    void testOutput11111111WhenInput255() {
        ConversionResult result = uut.convert("255");
        assertTrue(result.wasSuccessful());;
        assertEquals("11111111", result.getConversionResult());
    }
}