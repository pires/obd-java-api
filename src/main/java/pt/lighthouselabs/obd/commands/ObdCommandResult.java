package pt.lighthouselabs.obd.commands;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by andreas on 03.06.2015.
 */
public class ObdCommandResult implements Parcelable {

    private String name;
    private String clazz;
    private long time;
    private long duration;
    private String resultFormatted;
    private String resultUnit;
    private String result;
    private int state;

    public static final int STATE_NEW = 0;
    public static final int STATE_RUNNING = 1;
    public static final int STATE_FINISHED = 2;
    public static final int STATE_EXECUTION_ERROR = 3;
    public static final int STATE_QUEUE_ERROR = 4;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
        out.writeLong(time);
        out.writeLong(duration);
        out.writeString(resultFormatted);
        out.writeString(resultUnit);
        out.writeString(result);
        out.writeInt(state);
        out.writeString(clazz);
    }

    public static final Parcelable.Creator<ObdCommandResult> CREATOR = new Parcelable.Creator<ObdCommandResult>() {
        public ObdCommandResult createFromParcel(Parcel in) {
            return new ObdCommandResult(in);
        }

        public ObdCommandResult[] newArray(int size) {
            return new ObdCommandResult[size];
        }
    };

    private ObdCommandResult(Parcel in) {
        name = in.readString();
        time = in.readLong();
        duration = in.readLong();
        resultFormatted = in.readString();
        resultUnit = in.readString();
        result = in.readString();
        state = in.readInt();
        clazz = in.readString();
    }

    public ObdCommandResult(ObdCommand base, int state) {
        name = base.getName();
        time = base.getStart();
        duration = base.getEnd() - base.getStart();
        name = base.getName();
        resultFormatted = base.getFormattedResult();
        resultUnit = base.getResultUnit();
        result = base.getCalculatedResult();
        this.state = state;
        clazz = base.getClass().getSimpleName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getResultFormatted() {
        return resultFormatted;
    }

    public void setResultFormatted(String resultFormatted) {
        this.resultFormatted = resultFormatted;
    }

    public String getResultUnit() {
        return resultUnit;
    }

    public void setResultUnit(String resultUnit) {
        this.resultUnit = resultUnit;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
}