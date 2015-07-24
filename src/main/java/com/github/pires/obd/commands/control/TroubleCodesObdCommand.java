/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
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
public class TroubleCodesObdCommand extends ObdCommand {

    protected final static char[] dtcLetters = {'P', 'C', 'B', 'U'};
    protected final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    private StringBuffer codes = null;

    public TroubleCodesObdCommand() {
        super("03");

        codes = new StringBuffer();
    }

    /**
     * Copy ctor.
     *
     * @param other a {@link com.github.pires.obd.commands.control.TroubleCodesObdCommand} object.
     */
    public TroubleCodesObdCommand(TroubleCodesObdCommand other) {
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

        // read until '>' arrives
        while ((char) (b = (byte) in.read()) != '>') {
            if ((char) b != ' ') {
                res.append((char) b);
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
