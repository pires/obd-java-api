package com.github.pires.obd.commands.protocol;

/**
 * Turn-off echo.
 */
public class EchoOffCommand extends ObdProtocolCommand {

    public EchoOffCommand() {
        super("AT E0");
    }

    /**
     * <p>Constructor for EchoOffCommand.</p>
     *
     * @param other a {@link EchoOffCommand} object.
     */
    public EchoOffCommand(EchoOffCommand other) {
        super(other);
    }

    @Override
    public String getFormattedResult() {
        return getResult();
    }

    @Override
    public String getName() {
        return "Echo Off";
    }

}
