package com.stuart;

public class ConversionResult {
    private final boolean wasSuccessful;
    private final String conversionResult;
    private final String errorMessage;

    public ConversionResult(boolean wasSuccessful, String conversionResult, String errorMessage) {
        this.wasSuccessful = wasSuccessful;
        this.conversionResult = conversionResult;
        this.errorMessage = errorMessage;
    }

    public boolean wasSuccessful() {
        return wasSuccessful;
    }

    public String getConversionResult() {
        return conversionResult;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
