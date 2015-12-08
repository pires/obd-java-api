package com.github.pires.obd.commands.temperature;

import com.github.pires.obd.enums.AvailableCommandNames;

/**
 * Engine Coolant Temperature.
 *
 * @author pires
 * @version $Id: $Id
 */
public class EngineCoolantTemperatureCommand extends TemperatureCommand {

    /**
     * <p>Constructor for EngineCoolantTemperatureCommand.</p>
     */
    public EngineCoolantTemperatureCommand() {
        super("01 05");
    }

    /**
     * <p>Constructor for EngineCoolantTemperatureCommand.</p>
     *
     * @param other a {@link com.github.pires.obd.commands.temperature.TemperatureCommand} object.
     */
    public EngineCoolantTemperatureCommand(TemperatureCommand other) {
        super(other);
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return AvailableCommandNames.ENGINE_COOLANT_TEMP.getValue();
    }

}
