package com.github.pires.obd.commands.protocol;

import com.github.pires.obd.commands.PersistentCommand;
import com.github.pires.obd.enums.AvailableCommandNames;

/**
 * Retrieve available PIDs ranging from 41 to 60.
 */
public class AvailablePidsCommand_41_60 extends AvailablePidsCommand {

    /**
     * Default ctor.
     */
    public AvailablePidsCommand_41_60() {
        super("01 40");
    }

    /**
     * Copy ctor.
     *
     * @param other a {@link AvailablePidsCommand} object.
     */
    public AvailablePidsCommand_41_60(AvailablePidsCommand_41_60 other) {
        super(other);
    }

    @Override
    public String getName() {
        return AvailableCommandNames.PIDS_41_60.getValue();
    }
}
