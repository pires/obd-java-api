package com.github.pires.obd.commands.protocol;

import com.github.pires.obd.commands.ObdCommand;

/**
 * Reset trouble codes.
 */
public class ResetTroubleCodesCommand extends ObdCommand {

    public ResetTroubleCodesCommand() {
        super("04");
    }

    @Override
    protected void performCalculations() {

    }

    @Override
    public String getFormattedResult() {
        return getResult();
    }

    @Override
    public String getCalculatedResult() {
        return getResult();
    }


    @Override
    public String getName() {
        return getResult();
    }

}
