package com.github.pires.obd.commands.protocol;

/**
 * Turn-off headers.
 *
 * @author pires
 * @version $Id: $Id
 */
public class HeadersOffCommand extends ObdProtocolCommand {

    /**
     * <p>Constructor for HeadersOffCommand.</p>
     */
    public HeadersOffCommand() {
        super("ATH0");
    }

    /**
     * <p>Constructor for HeadersOffCommand.</p>
     *
     * @param other a {@link com.github.pires.obd.commands.protocol.HeadersOffCommand} object.
     */
    public HeadersOffCommand(HeadersOffCommand other) {
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
        return "Headers disabled";
    }

}
