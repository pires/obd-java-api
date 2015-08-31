package com.github.pires.obd.commands.control;

import com.github.pires.obd.commands.ObdCommand;
import com.github.pires.obd.commands.SystemOfUnits;
import com.github.pires.obd.enums.AvailableCommandNames;

/**
 * Distance traveled since codes cleared-up.
 */
public class DistanceSinceCCCommand extends ObdCommand
        implements SystemOfUnits {

    private int km = 0;

    /**
     * Default ctor.
     */
    public DistanceSinceCCCommand() {
        super("01 31");
    }

    /**
     * Copy ctor.
     *
     * @param other a {@link DistanceSinceCCCommand} object.
     */
    public DistanceSinceCCCommand(
            DistanceSinceCCCommand other) {
        super(other);
    }

    @Override
    protected void performCalculations() {
        // ignore first two bytes [01 31] of the response
        km = buffer.get(2) * 256 + buffer.get(3);
    }

    public String getFormattedResult() {
        return useImperialUnits ? String.format("%.2f%s", getImperialUnit(), getResultUnit())
                : String.format("%d%s", km, getResultUnit());
    }

    @Override
    public String getCalculatedResult() {
        return useImperialUnits ? String.valueOf(getImperialUnit()) : String.valueOf(km);
    }

    @Override
    public String getResultUnit() {
        return useImperialUnits ? "m" : "km";
    }

    @Override
    public float getImperialUnit() {
        return Double.valueOf(km * 0.621371192).floatValue();
    }

    /**
     * @return a int.
     */
    public int getKm() {
        return km;
    }

    /**
     * <p>Setter for the field <code>km</code>.</p>
     *
     * @param km a int.
     */
    public void setKm(int km) {
        this.km = km;
    }

    @Override
    public String getName() {
        return AvailableCommandNames.DISTANCE_TRAVELED_AFTER_CODES_CLEARED
                .getValue();
    }

}
