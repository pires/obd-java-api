package com.github.pires.obd.commands.control;

import com.github.pires.obd.commands.PersistentCommand;
import com.github.pires.obd.enums.AvailableCommandNames;

import javax.xml.bind.DatatypeConverter;

public class VinCommand extends PersistentCommand {

    String vin = "";

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
        final String result = getResult();
        String workingData;
        if (result.contains(":")) {//CAN(ISO-15765) protocol.
            workingData = result.replaceAll(".:", "").substring(9);//9 is xxx490201, xxx is bytes of information to follow.
        } else {//ISO9141-2, KWP2000 Fast and KWP2000 5Kbps (ISO15031) protocols.
            workingData = result.replaceAll("49020.", "");
        }
        byte[] bytes = DatatypeConverter.parseHexBinary(workingData);
        vin = new String(bytes).replaceAll("[\u0000-\u001f]", "");
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

    @Override
    protected void fillBuffer() {
    }
}


