package com.github.pires.obd.commands.protocol;

/**
 * Turn-off headers.
 */
public class HeadersOffCommand extends ObdProtocolCommand {

    public HeadersOffCommand() {
        super("ATH0");
    }

    /**
     * <p>Constructor for HeadersOffCommand.</p>
     *
     * @param other a {@link HeadersOffCommand} object.
     */
    public HeadersOffCommand(HeadersOffCommand other) {
        super(other);
    }

    @Override
    public String getFormattedResult() {
        return getResult();
    }

    @Override
    public String getName() {
        return "Headers disabled";
    }

}
