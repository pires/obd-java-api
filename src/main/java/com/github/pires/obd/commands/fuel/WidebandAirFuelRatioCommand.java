package com.github.pires.obd.commands.fuel;

import com.github.pires.obd.commands.ObdCommand;
import com.github.pires.obd.enums.AvailableCommandNames;

/**
 * Wideband AFR
 */
public class WidebandAirFuelRatioCommand extends ObdCommand {

    private float wafr = 0;

    public WidebandAirFuelRatioCommand() {
        super("01 34");
    }

    @Override
    protected void performCalculations() {
        // ignore first two bytes [01 44] of the response
        float A = buffer.get(2);
        float B = buffer.get(3);
        wafr = (((A * 256) + B) / 32768) * 14.7f;//((A*256)+B)/32768
    }

    @Override
    public String getFormattedResult() {
        return String.format("%.2f", getWidebandAirFuelRatio()) + ":1 AFR";
    }

    @Override
    public String getCalculatedResult() {
        return String.valueOf(getWidebandAirFuelRatio());
    }

    public double getWidebandAirFuelRatio() {
        return wafr;
    }

    @Override
    public String getName() {
        return AvailableCommandNames.WIDEBAND_AIR_FUEL_RATIO.getValue();
    }

}
