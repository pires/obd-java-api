package com.github.pires.obd.commands.protocol;

/**
 * As per https://www.elmelectronics.com/help/obd/tips/#327_Commands:
 * <p>
 * If a connection is lost, you will need to tell the ELM327 to ‘close’
 * the current connection, with a Protocol Close command (AT PC).
 * This will ensure that the ELM327 starts from the beginning when
 * the next request is made. This is particularly important for the
 * ISO 9141 and ISO 14230 protocols, as they need to send a special
 * initiation sequence.
 * <p>
 * Once the protocol has been closed, it can be re-opened by making a
 * request such as 01 00 (do not send ATZ or AT SP0, as many do).
 */
public class CloseCommand extends ObdProtocolCommand {

    /**
     * <p>Constructor for CloseCommand.</p>
     */
    public CloseCommand() {
        super("AT PC");
    }

    /**
     * <p>Constructor for CloseCommand.</p>
     *
     * @param other a {@link CloseCommand} object.
     */
    public CloseCommand(CloseCommand other) {
        super(other);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFormattedResult() {
        return getResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "Protocol Close";
    }

}
