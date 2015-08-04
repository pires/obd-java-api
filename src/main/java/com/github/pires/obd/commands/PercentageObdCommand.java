package com.github.pires.obd.commands;

/**
 * Abstract class for percentage commands.
 */
public abstract class PercentageObdCommand extends ObdCommand {

    protected float percentage = 0f;

    /**
     * @param command a {@link java.lang.String} object.
     */
    public PercentageObdCommand(String command) {
        super(command);
    }

    /**
     * @param other a {@link com.github.pires.obd.commands.PercentageObdCommand} object.
     */
    public PercentageObdCommand(PercentageObdCommand other) {
        super(other);
    }

    @Override
    protected void performCalculations() {
        // ignore first two bytes [hh hh] of the response
        percentage = (buffer.get(2) * 100.0f) / 255.0f;
    }

    /**
     *
     */
    @Override
    public String getFormattedResult() {
        return String.format("%.1f%s", percentage, getResultUnit());
    }

    /**
     * @return a float.
     */
    public float getPercentage() {
        return percentage;
    }

    @Override
    public String getResultUnit() {
        return "%";
    }

    @Override
    public String getCalculatedResult() {
        return String.valueOf(percentage);
    }

}
