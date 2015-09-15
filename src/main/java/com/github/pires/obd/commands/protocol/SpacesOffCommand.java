package com.github.pires.obd.commands.protocol;

import android.os.Parcel;

/**
 * Turn-off spaces.
 */
public class SpacesOffCommand extends ObdProtocolCommand {

    public SpacesOffCommand() {
        super("ATS0");
    }

    /**
     * <p>Constructor for SpacesOffCommand.</p>
     *
     * @param other a {@link SpacesOffCommand} object.
     */
    public SpacesOffCommand(SpacesOffCommand other) {
        super(other);
    }

    @Override
    public String getFormattedResult() {
        return getResult();
    }

    @Override
    public String getName() {
        return "Spaces Off";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
