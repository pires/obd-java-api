package com.github.pires.obd.commands.protocol;

/**
 * Turns off line-feed.
 */
public class LineFeedOffCommand extends ObdProtocolCommand {

    public LineFeedOffCommand() {
        super("AT L0");
    }

    /**
     * @param other a {@link LineFeedOffCommand} object.
     */
    public LineFeedOffCommand(LineFeedOffCommand other) {
        super(other);
    }

    @Override
    public String getFormattedResult() {
        return getResult();
    }

    @Override
    public String getName() {
        return "Line Feed Off";
    }

}
