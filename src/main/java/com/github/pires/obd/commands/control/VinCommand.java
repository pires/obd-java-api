package com.github.pires.obd.commands.control;

import com.github.pires.obd.commands.PersistentCommand;
import com.github.pires.obd.enums.AvailableCommandNames;

public class VinCommand extends PersistentCommand {

    String vin = "";
    int[] bufferUse = new int[]{
            2, 3, 4, 5, 6,
            9, 10, 11, 12, 13,
            16, 17, 18, 19, 20,
            23, 24, 25, 26, 27,
            30, 31, 32, 33, 34
    };

    /**
     * Default ctor.
     */
    public VinCommand() {
        super("09 02");
    }

    /**
     * Copy ctor.
     *
     * @param other a {@link VinCommand} object.
     */
    public VinCommand(VinCommand other) {
        super(other);
    }

    @Override
    protected void performCalculations() {
        // ignore first two bytes [01 31] of the response
        StringBuilder b = new StringBuilder();
        for (int i : bufferUse) {
            b.append(Character.toString((char) buffer.get(i).intValue()));
        }
        vin = b.toString().replaceAll("[\u0000-\u001f]", "");
    }

    @Override
    public String getFormattedResult() {
        return String.valueOf(vin);
    }

    @Override
    public String getName() {
        return AvailableCommandNames.VIN.getValue();
    }

    @Override
    public String getCalculatedResult() {
        return String.valueOf(vin);
    }

}


