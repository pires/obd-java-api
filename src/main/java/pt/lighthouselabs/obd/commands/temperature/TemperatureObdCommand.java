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
package pt.lighthouselabs.obd.commands.temperature;

import pt.lighthouselabs.obd.commands.ObdCommand;
import pt.lighthouselabs.obd.commands.SystemOfUnits;

/**
 * Abstract temperature command.
 */
public abstract class TemperatureObdCommand extends ObdCommand implements
    SystemOfUnits {

  private float temperature = 0.0f;

  /**
   * Default ctor.
   *
   * @param cmd a {@link java.lang.String} object.
   */
  public TemperatureObdCommand(String cmd) {
    super(cmd);
  }

  /**
   * Copy ctor.
   *
   * @param other a {@link pt.lighthouselabs.obd.commands.temperature.TemperatureObdCommand} object.
   */
  public TemperatureObdCommand(TemperatureObdCommand other) {
    super(other);
  }

  @Override
  protected void performCalculations() {
    // ignore first two bytes [hh hh] of the response
    temperature = buffer.get(2) - 40;
  }

  /**
   * {@inheritDoc}
   *
   * Get values from 'buff', since we can't rely on char/string for
   * calculations.
   */
  @Override
  public String getFormattedResult() {
    return useImperialUnits ? String.format("%.1f%s", getImperialUnit(), "F")
        : String.format("%.0f%s", temperature, "C");
  }

  /**
   * @return the temperature in Celsius.
   */
  public float getTemperature() {
    return temperature;
  }

  /**
   * @return the temperature in Fahrenheit.
   */
  public float getImperialUnit() {
    return temperature * 1.8f + 32;
  }

  /**
   * @return the temperature in Kelvin.
   */
  public float getKelvin() {
    return temperature + 273.15f;
  }

  /**
   * @return the OBD command name.
   */
  public abstract String getName();

}
