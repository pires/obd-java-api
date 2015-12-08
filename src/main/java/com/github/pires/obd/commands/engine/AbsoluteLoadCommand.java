package com.github.pires.obd.commands.engine;

import com.github.pires.obd.commands.PercentageObdCommand;
import com.github.pires.obd.enums.AvailableCommandNames;

/**
 * <p>AbsoluteLoadCommand class.</p>
 *
 * @author pires
 * @version $Id: $Id
 */
public class AbsoluteLoadCommand extends PercentageObdCommand {

    /**
     * Default ctor.
     */
    public AbsoluteLoadCommand() {
        super("01 43");
    }

    /**
     * Copy ctor.
     *
     * @param other a {@link com.github.pires.obd.commands.engine.AbsoluteLoadCommand} object.
     */
    public AbsoluteLoadCommand(AbsoluteLoadCommand other) {
        super(other);
    }

    /** {@inheritDoc} */
    @Override
    protected void performCalculations() {
        // ignore first two bytes [hh hh] of the response
        int a = buffer.get(2);
        int b = buffer.get(3);
        percentage = (a * 256 + b) * 100 / 255;
    }

    /**
     * <p>getRatio.</p>
     *
     * @return a double.
     */
    public double getRatio() {
        return percentage;
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return AvailableCommandNames.ABS_LOAD.getValue();
    }

}
