package com.stuart.converter;

import com.stuart.dto.ConversionResult;

/**
 * Converter implementation that will try to convert values into binary representation
 */
public class BinaryToDecimalConverter implements Converter {

    /**
     * Attempt to convert the supplied {@code input} into it's binary representation.
     * Note: this method will return null
     *
     * @param input - The input {@link String} to convert
     * @return - A {@link ConversionResult} for the attempted conversion
     */
    @Override
    public ConversionResult convert(final String input) {
        if (!validateBinaryInput(input)) {
            return new ConversionResult(false,
                    "",
                    "Input can only contain 1 or 0");
        }

        int answer = 0;
        // adjust value to count from 0
        final int length = input.length() - 1;
        // set variable to increment by power of 2 for each pass through the loop
        int incrementedValue = (int) Math.pow(2, length);
        for (int count = 0; count <= length; count++) {
            // set through binary string
            if (input.charAt(count) == 49) {
                answer += incrementedValue; // add power of answer to the answer
            }
            incrementedValue /= 2;
        }
        return new ConversionResult(true, String.valueOf(answer), "");
    }

    private boolean validateBinaryInput(final String binary) {
        if (null == binary) {
            return false;
        }
        if (binary.isEmpty() || binary.isBlank()) {
            return false;
        }
        char[] letters = binary.toCharArray();
        for (char c : letters) {
            if ((c != '0') && (c != '1')) {
                return false;
            }
        }
        return true;
    }
}
