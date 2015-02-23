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
package pt.lighthouselabs.obd.commands.control;

import pt.lighthouselabs.obd.commands.ObdCommand;
import pt.lighthouselabs.obd.commands.SystemOfUnits;
import pt.lighthouselabs.obd.enums.AvailableCommandNames;

/**
 * Distance traveled since codes cleared-up.
 */
public class DistanceTraveledSinceCodesClearedObdCommand extends ObdCommand
    implements SystemOfUnits {

  private int km = 0;

  /**
   * Default ctor.
   */
  public DistanceTraveledSinceCodesClearedObdCommand() {
    super("01 31");
  }

  /**
   * Copy ctor.
   *
   * @param other a {@link pt.lighthouselabs.obd.commands.control.DistanceTraveledSinceCodesClearedObdCommand} object.
   */
  public DistanceTraveledSinceCodesClearedObdCommand(
      DistanceTraveledSinceCodesClearedObdCommand other) {
    super(other);
  }

  @Override
  protected void performCalculations() {
    // ignore first two bytes [01 31] of the response
    km = buffer.get(2) * 256 + buffer.get(3);
  }

  @Override
  public String getFormattedResult() {
    return String.format("%.2f%s", (float)km, "km");
  }

  @Override
  public float getImperialUnit() {
    return new Double(km * 0.621371192).floatValue();
  }

  /**
   * @return a int.
   */
  public int getKm() {
    return km;
  }

  /**
   * <p>Setter for the field <code>km</code>.</p>
   *
   * @param km a int.
   */
  public void setKm(int km) {
    this.km = km;
  }

  @Override
  public String getName() {
    return AvailableCommandNames.DISTANCE_TRAVELED_AFTER_CODES_CLEARED
        .getValue();
  }

}
