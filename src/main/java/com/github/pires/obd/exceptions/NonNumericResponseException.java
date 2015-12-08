package com.github.pires.obd.exceptions;

/**
 * Thrown when there are no numbers in the response and they are expected
 *
 * @author pires
 * @version $Id: $Id
 */
public class NonNumericResponseException extends RuntimeException {

    /**
     * <p>Constructor for NonNumericResponseException.</p>
     *
     * @param message a {@link java.lang.String} object.
     */
    public NonNumericResponseException(String message) {
        super("Error reading response: " + message);
    }

}
