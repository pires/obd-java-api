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
package com.github.pires.obd.commands.engine;

import com.github.pires.obd.commands.ObdCommand;
import com.github.pires.obd.enums.AvailableCommandNames;

/**
 * Fuel systems that use conventional oxygen sensor display the commanded open
 * loop equivalence ratio while the system is in open loop. Should report 100%
 * when in closed loop fuel.
 * <p>
 * To obtain the actual air/fuel ratio being commanded, multiply the
 * stoichiometric A/F ratio by the equivalence ratio. For example, gasoline,
 * stoichiometric is 14.64:1 ratio. If the fuel control system was commanded an
 * equivalence ratio of 0.95, the commanded A/F ratio to the engine would be
 * 14.64 * 0.95 = 13.9 A/F.
 */
public class EngineFuelRate extends ObdCommand {

    // Equivalent ratio (L/h)
    private double fuelrate = 0.00;

    /**
     * Default ctor.
     */
    public EngineFuelRate() {
        super("01 5E");
    }

    /**
     * Copy ctor.
     *
     * @param other a {@link EngineFuelRate} object.
     */
    public EngineFuelRate(EngineFuelRate other) {
        super(other);
    }

    @Override
    protected void performCalculations() {
        // ignore first two bytes [hh hh] of the response
        int a = buffer.get(2);
        int b = buffer.get(3);
        fuelrate = ((a * 256) + b) * 0.05;
    }

    /**
     *
     */
    @Override
    public String getFormattedResult() {
        return String.format("%.1f%s", fuelrate, getResultUnit());
    }

    @Override
    public String getCalculatedResult() {
        return String.valueOf(fuelrate);
    }

    @Override
    public String getResultUnit() {
        return "L/h";
    }

    /**
     * @return a double.
     */
    public double getVoltage() {
        return fuelrate;
    }

    @Override
    public String getName() {
        return AvailableCommandNames.ENGINE_FUEL_RATE.getValue();
    }

}
