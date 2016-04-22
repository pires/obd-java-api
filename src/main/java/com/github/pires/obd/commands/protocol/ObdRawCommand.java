package com.github.pires.obd.commands.protocol;

/**
 * This class allows for an unspecified command to be sent.
 */
public class ObdRawCommand extends ObdProtocolCommand {

    /**
     * <p>Constructor for ObdRawCommand.</p>
     *
     * @param command a {@link java.lang.String} object.
     */
    public ObdRawCommand(String command) {
        super(command);
    }

    /** {@inheritDoc} */
    @Override
    public String getFormattedResult() {
        return getResult();
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return "Custom command " + getCommandPID();
    }

}
