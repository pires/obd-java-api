package com.github.pires.obd.commands.protocol;

import com.github.pires.obd.commands.ObdCommand;
import com.github.pires.obd.enums.AvailableCommandNames;
import com.github.pires.obd.enums.ObdProtocols;

/**
 * Describe the Protocol by Number.
 * It returns a number which represents the current
 * obdProtocol. If the automatic search function is also
 * enabled, the number will be preceded with the letter
 * ‘A’. The number is the same one that is used with the
 * set obdProtocol and test obdProtocol commands.
 *
 * @author pires
 * @version $Id: $Id
 * @since 1.0-RC12
 */
public class DescribeProtocolNumberCommand extends ObdCommand {

    private ObdProtocols obdProtocol = ObdProtocols.AUTO;

    /**
     * <p>Constructor for DescribeProtocolNumberCommand.</p>
     */
    public DescribeProtocolNumberCommand() {
        super("AT DPN");
    }

    /**
     * {@inheritDoc}
     *
     * This method exists so that for each command, there must be a method that is
     * called only once to perform calculations.
     */
    @Override
    protected void performCalculations() {
        String result = getResult();
        char protocolNumber;
        if (result.length() == 2) {//the obdProtocol was set automatic and its format A#
            protocolNumber = result.charAt(1);
        } else protocolNumber = result.charAt(0);
        ObdProtocols[] protocols = ObdProtocols.values();
        for (ObdProtocols protocol : protocols) {
            if (protocol.getValue() == protocolNumber) {
                this.obdProtocol = protocol;
                break;
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public String getFormattedResult() {
        return getResult();
    }

    /** {@inheritDoc} */
    @Override
    public String getCalculatedResult() {
        return obdProtocol.name();
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return AvailableCommandNames.DESCRIBE_PROTOCOL_NUMBER.getValue();
    }

    /**
     * <p>Getter for the field <code>obdProtocol</code>.</p>
     *
     * @return a {@link com.github.pires.obd.enums.ObdProtocols} object.
     */
    public ObdProtocols getObdProtocol() {
        return obdProtocol;
    }
}
