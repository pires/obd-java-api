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
package com.github.pires.obd.commands.control;

import com.github.pires.obd.commands.ObdCommand;
import com.github.pires.obd.enums.AvailableCommandNames;

/**
 * This command will for now read MIL (check engine light) state and number of
 * diagnostic trouble codes currently flagged in the ECU.
 * <p>
 * Perhaps in the future we'll extend this to read the 3rd, 4th and 5th bytes of
 * the response in order to store information about the availability and
 * completeness of certain on-board tests.
 *
 */
public class DtcNumberCommand extends ObdCommand {

    private int codeCount = 0;
    private boolean milOn = false;

    /**
     * Default ctor.
     */
    public DtcNumberCommand() {
        super("01 01");
    }

    /**
     * Copy ctor.
     *
     * @param other a {@link com.github.pires.obd.commands.control.DtcNumberCommand} object.
     */
    public DtcNumberCommand(DtcNumberCommand other) {
        super(other);
    }

    /** {@inheritDoc} */
    @Override
    protected void performCalculations() {
        // ignore first two bytes [hh hh] of the response
        final int mil = buffer.get(2);
        milOn = (mil & 0x80) == 128;
        codeCount = mil & 0x7F;
    }

    /**
     * <p>getFormattedResult.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getFormattedResult() {
        final String res = milOn ? "MIL is ON" : "MIL is OFF";
        return res + codeCount + " codes";
    }

    /** {@inheritDoc} */
    @Override
    public String getCalculatedResult() {
        return String.valueOf(codeCount);
    }

    /**
     * <p>getTotalAvailableCodes.</p>
     *
     * @return the number of trouble codes currently flaggd in the ECU.
     */
    public int getTotalAvailableCodes() {
        return codeCount;
    }

    /**
     * <p>Getter for the field <code>milOn</code>.</p>
     *
     * @return the state of the check engine light state.
     */
    public boolean getMilOn() {
        return milOn;
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return AvailableCommandNames.DTC_NUMBER.getValue();
    }

}
