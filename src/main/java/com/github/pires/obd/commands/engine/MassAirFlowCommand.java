package com.github.pires.obd.commands.engine;

import com.github.pires.obd.commands.ObdCommand;
import com.github.pires.obd.enums.AvailableCommandNames;

/**
 * Mass Air Flow (MAF)
 *
 * @author pires
 * @version $Id: $Id
 */
public class MassAirFlowCommand extends ObdCommand {

    private float maf = -1.0f;

    /**
     * Default ctor.
     */
    public MassAirFlowCommand() {
        super("01 10");
    }

    /**
     * Copy ctor.
     *
     * @param other a {@link com.github.pires.obd.commands.engine.MassAirFlowCommand} object.
     */
    public MassAirFlowCommand(MassAirFlowCommand other) {
        super(other);
    }

    /** {@inheritDoc} */
    @Override
    protected void performCalculations() {
        // ignore first two bytes [hh hh] of the response
        maf = (buffer.get(2) * 256 + buffer.get(3)) / 100.0f;
    }

    /** {@inheritDoc} */
    @Override
    public String getFormattedResult() {
        return String.format("%.2f%s", maf, getResultUnit());
    }

    /** {@inheritDoc} */
    @Override
    public String getCalculatedResult() {
        return String.valueOf(maf);
    }

    /** {@inheritDoc} */
    @Override
    public String getResultUnit() {
        return "g/s";
    }

    /**
     * <p>getMAF.</p>
     *
     * @return MAF value for further calculus.
     */
    public double getMAF() {
        return maf;
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return AvailableCommandNames.MAF.getValue();
    }

}
