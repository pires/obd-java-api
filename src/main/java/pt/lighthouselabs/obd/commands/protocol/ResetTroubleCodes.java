package com.github.pires.obd.commands.protocol;

import com.github.pires.obd.commands.ObdCommand;

/**
 * Created by Shaun on 2015/07/20.
 */
public class ResetTroubleCodes extends ObdCommand {

    public ResetTroubleCodes() {
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
