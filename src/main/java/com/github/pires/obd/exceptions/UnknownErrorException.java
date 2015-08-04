package com.github.pires.obd.exceptions;

/**
 * Thrown when there is "ERROR" in the response
 */
public class UnknownErrorException extends ResponseException {

    public UnknownErrorException() {
        super("ERROR");
    }

}
