package com.stuart.converter;

import com.stuart.dto.ConversionResult;

/**
 * Converter implementation that will attempt to convert the input into it's decimal representation.
 */
public class DecimalToBinaryConverter implements Converter {

    /**
     * Attempt to convert the {@code input} into a decimal string.
     *
     * For this to happen, the {@code input} string must consist of numeric characters and it must be
     * positive (or 0).
     *
     * @param input The input {@link String} to convert
     * @return {@link ConversionResult} for the attempted conversion.
     */
    @Override
    public ConversionResult convert(String input) {
        try {
            int decimalInput = Integer.parseInt(input);
            if (decimalInput < 0) {
                return new ConversionResult(false,
                        "",
                        "Input must be positive");
            }
            StringBuilder convertedOutput = new StringBuilder();
            do {
                int answer = decimalInput % 2;
                convertedOutput.insert(0, answer + "");
                decimalInput /= 2;
            } while (decimalInput != 0);

            return new ConversionResult(true, convertedOutput.toString(), "");

        } catch (NumberFormatException nfe) {
            return new ConversionResult(false,
                    "",
                    "Input is not a valid number");
        }
    }
}
