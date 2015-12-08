package com.github.pires.obd.commands.control;

import com.github.pires.obd.commands.PercentageObdCommand;
import com.github.pires.obd.enums.AvailableCommandNames;

/**
 * Fuel systems that use conventional oxygen sensor display the commanded open
 * loop equivalence ratio while the system is in open loop. Should report 100%
 * when in closed loop fuel.
 * <p>
 * To obtain the actual air/fuel ratio being commanded, multiply the
 * stoichiometric A/F ratio by the equivalence ratio. For example, gasoline,
 * stoichiometric is 14.64:1 ratio. If the fuel control system was commanded an
 * equivalence ratio of 0.95, the commanded A/F ratio to the engine would be
 * 14.64 * 0.95 = 13.9 A/F.
 *
 * @author pires
 * @version $Id: $Id
 */
public class EquivalentRatioCommand extends PercentageObdCommand {


    /**
     * Default ctor.
     */
    public EquivalentRatioCommand() {
        super("01 44");
    }

    /**
     * Copy ctor.
     *
     * @param other a {@link com.github.pires.obd.commands.control.EquivalentRatioCommand} object.
     */
    public EquivalentRatioCommand(EquivalentRatioCommand other) {
        super(other);
    }

    /** {@inheritDoc} */
    @Override
    protected void performCalculations() {
        // ignore first two bytes [hh hh] of the response
        int a = buffer.get(2);
        int b = buffer.get(3);
        percentage = (a * 256 + b) / 32768;
    }


    /**
     * <p>getRatio.</p>
     *
     * @return a double.
     */
    public double getRatio() {
        return (double) percentage;
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return AvailableCommandNames.EQUIV_RATIO.getValue();
    }

}
