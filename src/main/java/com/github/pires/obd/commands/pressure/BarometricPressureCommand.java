package com.github.pires.obd.commands.pressure;

import com.github.pires.obd.enums.AvailableCommandNames;

/**
 * Barometric pressure.
 */
public class BarometricPressureCommand extends PressureCommand {

    public BarometricPressureCommand() {
        super("01 33");
    }

    /**
     * @param other a {@link PressureCommand} object.
     */
    public BarometricPressureCommand(PressureCommand other) {
        super(other);
    }

    @Override
    public String getName() {
        return AvailableCommandNames.BAROMETRIC_PRESSURE.getValue();
    }

}
