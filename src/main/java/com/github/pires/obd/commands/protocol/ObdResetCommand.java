package com.github.pires.obd.commands.protocol;

/**
 * Reset the OBD connection.
 */
public class ObdResetCommand extends ObdProtocolCommand {

    public ObdResetCommand() {
        super("AT Z");
    }

    /**
     * @param other a {@link com.github.pires.obd.commands.protocol.ObdResetCommand} object.
     */
    public ObdResetCommand(ObdResetCommand other) {
        super(other);
    }

    @Override
    public String getFormattedResult() {
        return getResult();
    }

    @Override
    public String getName() {
        return "Reset OBD";
    }

}
