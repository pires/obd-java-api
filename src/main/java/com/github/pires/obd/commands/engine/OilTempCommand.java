package com.github.pires.obd.commands.engine;

import com.github.pires.obd.commands.temperature.TemperatureCommand;
import com.github.pires.obd.enums.AvailableCommandNames;

/**
 * Displays the current engine Oil temperature.
 */
public class OilTempCommand extends TemperatureCommand {

    /**
     * Default ctor.
     */
    public OilTempCommand() {
        super("01 5C");
    }

    /**
     * Copy ctor.
     *
     * @param other a {@link OilTempCommand} object.
     */
    public OilTempCommand(OilTempCommand other) {
        super(other);
    }

    @Override
    public String getName() {
        return AvailableCommandNames.ENGINE_OIL_TEMP.getValue();
    }

}
