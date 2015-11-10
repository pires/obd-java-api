package com.github.pires.obd.commands.control;

import com.github.pires.obd.commands.ObdCommand;
import com.github.pires.obd.enums.AvailableCommandNames;

public class ModuleVoltageCommand extends ObdCommand {

    // Equivalent ratio (V)
    private double voltage = 0.00;

    /**
     * Default ctor.
     */
    public ModuleVoltageCommand() {
        super("01 42");
    }

    /**
     * Copy ctor.
     *
     * @param other a {@link ModuleVoltageCommand} object.
     */
    public ModuleVoltageCommand(ModuleVoltageCommand other) {
        super(other);
    }

    @Override
    protected void performCalculations() {
        // ignore first two bytes [hh hh] of the response
        int a = buffer.get(2);
        int b = buffer.get(3);
        voltage = (a * 256 + b) / 1000;
    }

    /**
     * @return
     */
    @Override
    public String getFormattedResult() {
        return String.format("%.1f%s", voltage, getResultUnit());
    }

    @Override
    public String getResultUnit() {
        return "V";
    }

    @Override
    public String getCalculatedResult() {
        return String.valueOf(voltage);
    }

    /**
     * @return a double.
     */
    public double getVoltage() {
        return voltage;
    }

    @Override
    public String getName() {
        return AvailableCommandNames.CONTROL_MODULE_VOLTAGE.getValue();
    }

}
