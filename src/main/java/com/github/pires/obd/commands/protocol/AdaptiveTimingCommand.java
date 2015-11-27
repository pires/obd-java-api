package com.github.pires.obd.commands.protocol;

/**
 * By default, Adaptive Timing option 1 (AT1) is enabled, and is the recommended setting.
 * AT0 is used to disable Adaptive timing (so the timeout is always as set by AT ST),
 * while AT2 is a more aggressive version of AT1 (the effect is more noticeable for very
 * slow connections – you may not see much difference with faster OBD systems.
 */
public class AdaptiveTimingCommand extends ObdProtocolCommand {

    public AdaptiveTimingCommand(int mode) {
        super("AT AT" + mode);
    }

    /**
     * <p>Constructor for AdaptiveTimingCommand.</p>
     *
     * @param other a {@link AdaptiveTimingCommand} object.
     */
    public AdaptiveTimingCommand(AdaptiveTimingCommand other) {
        super(other);
    }

    @Override
    public String getFormattedResult() {
        return getResult();
    }

    @Override
    public String getName() {
        return "Adaptive timing set";
    }

}
