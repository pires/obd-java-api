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
package com.github.pires.obd.commands.temperature;

import com.github.pires.obd.commands.ObdCommand;
import com.github.pires.obd.commands.SystemOfUnits;

/**
 * Abstract temperature command.
 *
 */
public abstract class TemperatureCommand extends ObdCommand implements
        SystemOfUnits {

    private float temperature = 0.0f;

    /**
     * Default ctor.
     *
     * @param cmd a {@link java.lang.String} object.
     */
    public TemperatureCommand(String cmd) {
        super(cmd);
    }

    /**
     * Copy ctor.
     *
     * @param other a {@link com.github.pires.obd.commands.temperature.TemperatureCommand} object.
     */
    public TemperatureCommand(TemperatureCommand other) {
        super(other);
    }

    /** {@inheritDoc} */
    @Override
    protected void performCalculations() {
        // ignore first two bytes [hh hh] of the response
        temperature = buffer.get(2) - 40;
    }


    /**
     * {@inheritDoc}
     * <p>
     * Get values from 'buff', since we can't rely on char/string for
     * calculations.
     */
    @Override
    public String getFormattedResult() {
        return useImperialUnits ? String.format("%.1f%s", getImperialUnit(), getResultUnit())
                : String.format("%.0f%s", temperature, getResultUnit());
    }

    /** {@inheritDoc} */
    @Override
    public String getCalculatedResult() {
        return useImperialUnits ? String.valueOf(getImperialUnit()) : String.valueOf(temperature);
    }

    /** {@inheritDoc} */
    @Override
    public String getResultUnit() {
        return useImperialUnits ? "F" : "C";
    }

    /**
     * <p>Getter for the field <code>temperature</code>.</p>
     *
     * @return the temperature in Celsius.
     */
    public float getTemperature() {
        return temperature;
    }

    /**
     * <p>getImperialUnit.</p>
     *
     * @return the temperature in Fahrenheit.
     */
    public float getImperialUnit() {
        return temperature * 1.8f + 32;
    }

    /**
     * <p>getKelvin.</p>
     *
     * @return the temperature in Kelvin.
     */
    public float getKelvin() {
        return temperature + 273.15f;
    }

    /**
     * <p>getName.</p>
     *
     * @return the OBD command name.
     */
    public abstract String getName();

}
