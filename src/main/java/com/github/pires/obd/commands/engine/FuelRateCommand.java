package com.github.pires.obd.commands.engine;

import com.github.pires.obd.commands.ObdCommand;
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
 */
public class FuelRateCommand extends ObdCommand {

    // Equivalent ratio (L/h)
    private double fuelrate = 0.00;

    /**
     * Default ctor.
     */
    public FuelRateCommand() {
        super("01 5E");
    }

    /**
     * Copy ctor.
     *
     * @param other a {@link FuelRateCommand} object.
     */
    public FuelRateCommand(FuelRateCommand other) {
        super(other);
    }

    @Override
    protected void performCalculations() {
        // ignore first two bytes [hh hh] of the response
        int a = buffer.get(2);
        int b = buffer.get(3);
        fuelrate = ((a * 256) + b) * 0.05;
    }

    /**
     *
     */
    @Override
    public String getFormattedResult() {
        return String.format("%.1f%s", fuelrate, getResultUnit());
    }

    @Override
    public String getCalculatedResult() {
        return String.valueOf(fuelrate);
    }

    @Override
    public String getResultUnit() {
        return "L/h";
    }

    /**
     * @return a double.
     */
    public double getVoltage() {
        return fuelrate;
    }

    @Override
    public String getName() {
        return AvailableCommandNames.ENGINE_FUEL_RATE.getValue();
    }

}
