package com.github.pires.obd.commands.fuel;

import com.github.pires.obd.commands.ObdCommand;
import com.github.pires.obd.enums.AvailableCommandNames;

/**
 * Wideband AFR
 *
 * @author pires
 * @version $Id: $Id
 */
public class WidebandAirFuelRatioCommand extends ObdCommand {

    private float wafr = 0;

    /**
     * <p>Constructor for WidebandAirFuelRatioCommand.</p>
     */
    public WidebandAirFuelRatioCommand() {
        super("01 34");
    }

    /** {@inheritDoc} */
    @Override
    protected void performCalculations() {
        // ignore first two bytes [01 44] of the response
        float A = buffer.get(2);
        float B = buffer.get(3);
        wafr = (((A * 256) + B) / 32768) * 14.7f;//((A*256)+B)/32768
    }

    /** {@inheritDoc} */
    @Override
    public String getFormattedResult() {
        return String.format("%.2f", getWidebandAirFuelRatio()) + ":1 AFR";
    }

    /** {@inheritDoc} */
    @Override
    public String getCalculatedResult() {
        return String.valueOf(getWidebandAirFuelRatio());
    }

    /**
     * <p>getWidebandAirFuelRatio.</p>
     *
     * @return a double.
     */
    public double getWidebandAirFuelRatio() {
        return wafr;
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return AvailableCommandNames.WIDEBAND_AIR_FUEL_RATIO.getValue();
    }

}
