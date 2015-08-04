package com.github.pires.obd.exceptions;

/**
 * Thrown when there is a "UNABLE TO CONNECT" message.
 */
public class UnableToConnectException extends ResponseException {

    public UnableToConnectException() {
        super("UNABLE TO CONNECT");
    }

}
