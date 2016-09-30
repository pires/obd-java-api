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
package com.github.pires.obd.commands.pressure;

import com.github.pires.obd.enums.AvailableCommandNames;

/**
 * Barometric pressure.
 *
 */
public class BarometricPressureCommand extends PressureCommand {

    /**
     * <p>Constructor for BarometricPressureCommand.</p>
     */
    public BarometricPressureCommand() {
        super("01 33");
    }

    /**
     * <p>Constructor for BarometricPressureCommand.</p>
     *
     * @param other a {@link com.github.pires.obd.commands.pressure.PressureCommand} object.
     */
    public BarometricPressureCommand(PressureCommand other) {
        super(other);
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return AvailableCommandNames.BAROMETRIC_PRESSURE.getValue();
    }

}
