package com.github.pires.obd.commands.pressure;

import com.github.pires.obd.enums.AvailableCommandNames;

/**
 * Intake Manifold Pressure
 *
 * @author pires
 * @version $Id: $Id
 */
public class IntakeManifoldPressureCommand extends PressureCommand {

    /**
     * Default ctor.
     */
    public IntakeManifoldPressureCommand() {
        super("01 0B");
    }

    /**
     * Copy ctor.
     *
     * @param other a {@link com.github.pires.obd.commands.pressure.IntakeManifoldPressureCommand} object.
     */
    public IntakeManifoldPressureCommand(IntakeManifoldPressureCommand other) {
        super(other);
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return AvailableCommandNames.INTAKE_MANIFOLD_PRESSURE.getValue();
    }

}
