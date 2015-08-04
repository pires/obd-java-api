package com.github.pires.obd.commands.engine;

import com.github.pires.obd.commands.ObdCommand;
import com.github.pires.obd.enums.AvailableCommandNames;

/**
 * Displays the current engine Oil temperature.
 */
public class OilTempCommand extends ObdCommand {

    private int oiltemp = -40;

    /**
     * Default ctor.
     */
    public OilTempCommand() {
        super("01 5C");
    }

    /**
     * Copy ctor.
     *
     * @param other a {@link OilTempCommand} object.
     */
    public OilTempCommand(OilTempCommand other) {
        super(other);
    }

    @Override
    protected void performCalculations() {
        // ignore first two bytes [41 0C] of the response((A*256)+B)/4
        int A = buffer.get(2);
        oiltemp = A - 40;
    }

    /**
     * @return the engine oil temp
     */
    @Override
    public String getFormattedResult() {
        return String.format("%d%s", oiltemp, getResultUnit());
    }

    @Override
    public String getCalculatedResult() {
        return String.valueOf(oiltemp);
    }

    @Override
    public String getResultUnit() {
        return "C";
    }

    @Override
    public String getName() {
        return AvailableCommandNames.ENGINE_OIL_TEMP.getValue();
    }

    /**
     * @return a int.
     */
    public int getOilTemp() {
        return oiltemp;
    }

}
