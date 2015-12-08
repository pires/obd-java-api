package com.github.pires.obd.commands.protocol;

/**
 * This class allows for an unspecified command to be sent.
 *
 * @author pires
 * @version $Id: $Id
 */
public class OdbRawCommand extends ObdProtocolCommand {

    /**
     * <p>Constructor for OdbRawCommand.</p>
     *
     * @param command a {@link java.lang.String} object.
     */
    public OdbRawCommand(String command) {
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
