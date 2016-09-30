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
package com.github.pires.obd.commands.fuel;

import com.github.pires.obd.commands.PercentageObdCommand;
import com.github.pires.obd.enums.FuelTrim;

/**
 * Fuel Trim.
 *
 */
public class FuelTrimCommand extends PercentageObdCommand {

    private final FuelTrim bank;

    /**
     * Default ctor.
     * <p>
     * Will read the bank from parameters and construct the command accordingly.
     * Please, see FuelTrim enum for more details.
     *
     * @param bank a {@link com.github.pires.obd.enums.FuelTrim} object.
     */
    public FuelTrimCommand(final FuelTrim bank) {
        super(bank.buildObdCommand());
        this.bank = bank;
    }

    /**
     * <p>Constructor for FuelTrimCommand.</p>
     */
    public FuelTrimCommand() {
        this(FuelTrim.SHORT_TERM_BANK_1);
    }

    /**
     * @param value
     * @return
     */
    private float prepareTempValue(final int value) {
        return (value - 128) * (100.0F / 128);
    }

    /**
     * <p>performCalculations.</p>
     */
    protected void performCalculations() {
        // ignore first two bytes [hh hh] of the response
        percentage = prepareTempValue(buffer.get(2));
    }

    /**
     * <p>getValue.</p>
     *
     * @return the readed Fuel Trim percentage value.
     * @deprecated use #getCalculatedResult()
     */
    public final float getValue() {
        return percentage;
    }

    /**
     * <p>Getter for the field <code>bank</code>.</p>
     *
     * @return the name of the bank in string representation.
     */
    public final String getBank() {
        return bank.getBank();
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return bank.getBank();
    }

}
