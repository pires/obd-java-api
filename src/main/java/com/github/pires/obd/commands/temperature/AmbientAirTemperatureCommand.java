package com.github.pires.obd.commands.temperature;

import com.github.pires.obd.enums.AvailableCommandNames;

/**
 * Ambient Air Temperature.
 *
 * @author pires
 * @version $Id: $Id
 */
public class AmbientAirTemperatureCommand extends TemperatureCommand {

    /**
     * <p>Constructor for AmbientAirTemperatureCommand.</p>
     */
    public AmbientAirTemperatureCommand() {
        super("01 46");
    }

    /**
     * <p>Constructor for AmbientAirTemperatureCommand.</p>
     *
     * @param other a {@link com.github.pires.obd.commands.temperature.TemperatureCommand} object.
     */
    public AmbientAirTemperatureCommand(TemperatureCommand other) {
        super(other);
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return AvailableCommandNames.AMBIENT_AIR_TEMP.getValue();
    }

}
