package com.github.pires.obd.exceptions;

/**
 * Thrown when there are no numbers in the response and they are expected
 */
public class NonNumericResponseException extends RuntimeException {

    /**
     * @param message a {@link java.lang.String} object.
     */
    public NonNumericResponseException(String message) {
        super("Error reading response: " + message);
    }

}
