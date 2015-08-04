package com.github.pires.obd.commands.fuel;

import com.github.pires.obd.commands.ObdCommand;
import com.github.pires.obd.enums.AvailableCommandNames;

/**
 * AFR
 */
public class AirFuelRatioCommand extends ObdCommand {

    private float afr = 0;

    public AirFuelRatioCommand() {
        super("01 44");
    }

    @Override
    protected void performCalculations() {
        // ignore first two bytes [01 44] of the response
        float A = buffer.get(2);
        float B = buffer.get(3);
        afr = (((A * 256) + B) / 32768) * 14.7f;//((A*256)+B)/32768
    }

    @Override
    public String getFormattedResult() {
        return String.format("%.2f", getAirFuelRatio()) + ":1 AFR";
    }

    @Override
    public String getCalculatedResult() {
        return String.valueOf(getAirFuelRatio());
    }

    public double getAirFuelRatio() {
        return afr;
    }

    @Override
    public String getName() {
        return AvailableCommandNames.AIR_FUEL_RATIO.getValue();
    }

}
