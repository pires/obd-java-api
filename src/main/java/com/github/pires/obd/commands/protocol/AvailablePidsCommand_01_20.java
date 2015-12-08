package com.github.pires.obd.commands.protocol;

import com.github.pires.obd.enums.AvailableCommandNames;

/**
 * Retrieve available PIDs ranging from 01 to 20.
 *
 * @author pires
 * @version $Id: $Id
 */
public class AvailablePidsCommand_01_20 extends AvailablePidsCommand {

    /**
     * Default ctor.
     */
    public AvailablePidsCommand_01_20() {
        super("01 00");
    }

    /**
     * Copy ctor.
     *
     * @param other a {@link com.github.pires.obd.commands.protocol.AvailablePidsCommand} object.
     */
    public AvailablePidsCommand_01_20(AvailablePidsCommand_01_20 other) {
        super(other);
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return AvailableCommandNames.PIDS_01_20.getValue();
    }
}
