package com.stuart.converter;

import com.stuart.dto.ConversionResult;

/**
 * Interface to allow conversions.
 */
public interface Converter {
    /**
     * Take the input {@link String} and convert it.
     *
     * @param input The input {@link String} to convert
     * @return The {@link ConversionResult} for the conversion attempt.
     */
    ConversionResult convert(final String input);

}

