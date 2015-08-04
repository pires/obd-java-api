package com.github.pires.obd.exceptions;

/**
 * Thrown when there is a "NO DATA" message.
 */
public class NoDataException extends ResponseException {

    public NoDataException() {
        super("NO DATA");
    }

}
