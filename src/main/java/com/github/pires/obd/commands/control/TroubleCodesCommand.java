package com.github.pires.obd.commands.control;

import java.io.IOException;
import java.io.InputStream;

import com.github.pires.obd.commands.ObdCommand;
import com.github.pires.obd.enums.AvailableCommandNames;

/**
 * It is not needed no know how many DTC are stored.
 * Because when no DTC are stored response will be NO DATA
 * And where are more messages it will be stored in frames that have 7 bytes.
 * In one frame are stored 3 DTC.
 * If we find out DTC P0000 that mean no message are we can end.
 */
public class TroubleCodesCommand extends ObdCommand {

    protected final static char[] dtcLetters = {'P', 'C', 'B', 'U'};
    protected final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    private StringBuffer codes = null;

    public TroubleCodesCommand() {
        super("01 03");

        codes = new StringBuffer();
    }

    /**
     * Copy ctor.
     *
     * @param other a {@link TroubleCodesCommand} object.
     */
    public TroubleCodesCommand(TroubleCodesCommand other) {
        super(other);
        codes = new StringBuffer();
    }

    @Override
    protected void fillBuffer() {
    }

    @Override
    protected void performCalculations() {

        String workingData = getResult().replaceAll("[\r\n]", "");

        int begin = 0; // start at 2nd byte

        for (int i = 0; begin < workingData.length(); i++) {
            begin += 2;

            for (int j = 0; j < 3; j++) {
                String dtc = "";

                byte b1 = hexStringToByteArray(workingData.charAt(begin));

                int ch1 = ((b1 & 0xC0) >> 6);
                int ch2 = ((b1 & 0x30) >> 4);

                dtc += dtcLetters[ch1];
                dtc += hexArray[ch2];

                begin++;

                dtc += workingData.substring(begin, begin + 3);

                if (dtc.equals("P0000")) {
                    return;
                }

                codes.append(dtc);
                codes.append('\n');
                begin += 3;
            }
        }
    }

    private byte hexStringToByteArray(char s) {
        return (byte) ((Character.digit(s, 16) << 4));
    }

    /**
     * @return the formatted result of this command in string representation.
     * @deprecated use #getCalculatedResult instead
     */
    public String formatResult() {
        return codes.toString();
    }

    @Override
    public String getCalculatedResult() {
        return String.valueOf(codes);
    }


    @Override
    protected void readRawData(InputStream in) throws IOException {
        byte b = 0;
        StringBuilder res = new StringBuilder();

        // read until '>' arrives OR end of stream reached (and skip ' ')
        char c;
        while(true)
        {
      	  b = (byte) in.read();
      	  if(b == -1) // -1 if the end of the stream is reached
      	  {
      		  break;
      	  }
      	  c = (char)b;
      	  if(c == '>') // read until '>' arrives
      	  {
      		  break;
      	  }
      	  if(c != ' ') // skip ' '
      	  {
      		  res.append(c);
      	  }
        }

        rawData = res.toString().trim();

    }

    @Override
    public String getFormattedResult() {
        return codes.toString();
    }

    @Override
    public String getName() {
        return AvailableCommandNames.TROUBLE_CODES.getValue();
    }

}
