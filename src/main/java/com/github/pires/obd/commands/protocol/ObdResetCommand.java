package com.github.pires.obd.commands.protocol;

/**
 * Reset the OBD connection.
 *
 * @author pires
 * @version $Id: $Id
 */
public class ObdResetCommand extends ObdProtocolCommand {

    /**
     * <p>Constructor for ObdResetCommand.</p>
     */
    public ObdResetCommand() {
        super("AT Z");
    }

    /**
     * <p>Constructor for ObdResetCommand.</p>
     *
     * @param other a {@link com.github.pires.obd.commands.protocol.ObdResetCommand} object.
     */
    public ObdResetCommand(ObdResetCommand other) {
        super(other);
    }

    /** {@inheritDoc} */
    @Override
    public String getFormattedResult() {
        return getResult();
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return "Reset OBD";
    }

}
