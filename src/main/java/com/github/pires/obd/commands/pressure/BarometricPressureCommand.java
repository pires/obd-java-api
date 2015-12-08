package com.github.pires.obd.commands.pressure;

import com.github.pires.obd.enums.AvailableCommandNames;

/**
 * Barometric pressure.
 *
 * @author pires
 * @version $Id: $Id
 */
public class BarometricPressureCommand extends PressureCommand {

    /**
     * <p>Constructor for BarometricPressureCommand.</p>
     */
    public BarometricPressureCommand() {
        super("01 33");
    }

    /**
     * <p>Constructor for BarometricPressureCommand.</p>
     *
     * @param other a {@link com.github.pires.obd.commands.pressure.PressureCommand} object.
     */
    public BarometricPressureCommand(PressureCommand other) {
        super(other);
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return AvailableCommandNames.BAROMETRIC_PRESSURE.getValue();
    }

}
