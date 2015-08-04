package com.github.pires.obd.exceptions;

/**
 * Sent when there is a "STOPPED" message.
 */
public class StoppedException extends ResponseException {

    public StoppedException() {
        super("STOPPED");
    }

}
