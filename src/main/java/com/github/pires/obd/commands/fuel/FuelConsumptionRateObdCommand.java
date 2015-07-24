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
 */
package com.github.pires.obd.commands.fuel;

import com.github.pires.obd.commands.ObdCommand;
import com.github.pires.obd.enums.AvailableCommandNames;

/**
 * Fuel Consumption Rate per hour.
 */
public class FuelConsumptionRateObdCommand extends ObdCommand {

    private float fuelRate = -1.0f;

    public FuelConsumptionRateObdCommand() {
        super("01 5E");
    }

    /**
     * <p>Constructor for FuelConsumptionRateObdCommand.</p>
     *
     * @param other a {@link com.github.pires.obd.commands.fuel.FuelConsumptionRateObdCommand} object.
     */
    public FuelConsumptionRateObdCommand(FuelConsumptionRateObdCommand other) {
        super(other);
    }

    @Override
    protected void performCalculations() {
        // ignore first two bytes [hh hh] of the response
        fuelRate = (buffer.get(2) * 256 + buffer.get(3)) * 0.05f;
    }

    @Override
    public String getFormattedResult() {
        return String.format("%.1f%s", fuelRate, getResultUnit());
    }

    @Override
    public String getCalculatedResult() {
        return String.valueOf(fuelRate);
    }

    @Override
    public String getResultUnit() {
        return "L/h";
    }

    /**
     * @return a float.
     */
    public float getLitersPerHour() {
        return fuelRate;
    }

    @Override
    public String getName() {
        return AvailableCommandNames.FUEL_CONSUMPTION.getValue();
    }

}
