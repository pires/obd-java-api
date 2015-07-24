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
package com.github.pires.obd.commands.control;

import com.github.pires.obd.commands.ObdCommand;
import com.github.pires.obd.commands.SystemOfUnits;
import com.github.pires.obd.enums.AvailableCommandNames;

public class DistanceTraveledWithMILOnObdCommand extends ObdCommand
        implements SystemOfUnits {

    private int km = 0;

    /**
     * Default ctor.
     */
    public DistanceTraveledWithMILOnObdCommand() {
        super("01 21");
    }

    /**
     * Copy ctor.
     *
     * @param other a {@link DistanceTraveledWithMILOnObdCommand} object.
     */
    public DistanceTraveledWithMILOnObdCommand(
            DistanceTraveledWithMILOnObdCommand other) {
        super(other);
    }

    @Override
    protected void performCalculations() {
        // ignore first two bytes [01 31] of the response
        km = buffer.get(2) * 256 + buffer.get(3);
    }

    public String getFormattedResult() {
        return useImperialUnits ? String.format("%.2f%s", getImperialUnit(), getResultUnit())
                : String.format("%d%s", km, getResultUnit());
    }

    @Override
    public String getCalculatedResult() {
        return useImperialUnits ? String.valueOf(getImperialUnit()) : String.valueOf(km);
    }

    @Override
    public String getResultUnit() {
        return useImperialUnits ? "m" : "km";
    }

    @Override
    public float getImperialUnit() {
        return Double.valueOf(km * 0.621371192).floatValue();
    }

    /**
     * @return a int.
     */
    public int getKm() {
        return km;
    }

    @Override
    public String getName() {
        return AvailableCommandNames.DISTANCE_TRAVELED_MIL_ON
                .getValue();
    }

}
