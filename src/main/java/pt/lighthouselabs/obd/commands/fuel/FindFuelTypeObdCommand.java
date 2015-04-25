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
package pt.lighthouselabs.obd.commands.fuel;

import pt.lighthouselabs.obd.commands.ObdCommand;
import pt.lighthouselabs.obd.enums.AvailableCommandNames;
import pt.lighthouselabs.obd.enums.FuelType;

/**
 * This command is intended to determine the vehicle fuel type.
 */
public class FindFuelTypeObdCommand extends ObdCommand {

  private int fuelType = 0;

  /**
   * Default ctor.
   */
  public FindFuelTypeObdCommand() {
    super("01 51");
  }

  /**
   * Copy ctor
   *
   * @param other a {@link pt.lighthouselabs.obd.commands.fuel.FindFuelTypeObdCommand} object.
   */
  public FindFuelTypeObdCommand(FindFuelTypeObdCommand other) {
    super(other);
  }

  @Override
  protected void performCalculations() {
    // ignore first two bytes [hh hh] of the response
    fuelType = buffer.get(2);
  }

  @Override
  public String getFormattedResult() {
    try {
      return FuelType.fromValue(fuelType).getDescription();
    } catch(NullPointerException e) {
      return "-";
    }
  }

  @Override
  public String getName() {
    return AvailableCommandNames.FUEL_TYPE.getValue();
  }

}
