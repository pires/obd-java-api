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
package pt.lighthouselabs.obd.commands.pressure;

import pt.lighthouselabs.obd.enums.AvailableCommandNames;

public class FuelPressureObdCommand extends PressureObdCommand {

  public FuelPressureObdCommand() {
    super("010A");
  }

  /**
   * @param other a {@link pt.lighthouselabs.obd.commands.pressure.FuelPressureObdCommand} object.
   */
  public FuelPressureObdCommand(FuelPressureObdCommand other) {
    super(other);
  }

  /**
   * {@inheritDoc}
   *
   * TODO describe of why we multiply by 3
   */
  @Override
  protected final int preparePressureValue() {
    return buffer.get(2) * 3;
  }

  @Override
  public String getName() {
    return AvailableCommandNames.FUEL_PRESSURE.getValue();
  }

}