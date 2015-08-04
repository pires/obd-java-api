package com.github.pires.obd.commands.protocol;

import com.github.pires.obd.commands.ObdCommand;

public abstract class ObdProtocolCommand extends ObdCommand {
    /**
     * Default ctor to use
     *
     * @param command the command to send
     */
    public ObdProtocolCommand(String command) {
        super(command);
    }

    /**
     * Copy ctor.
     *
     * @param other the ObdCommand to copy.
     */
    public ObdProtocolCommand(ObdProtocolCommand other) {
        this(other.cmd);
    }

    protected void performCalculations() {
        // ignore
    }

    protected void fillBuffer() {
        // settings commands don't return a value appropriate to place into the
        // buffer, so do nothing
    }

    @Override
    public String getCalculatedResult() {
        return String.valueOf(getResult());
    }
}
