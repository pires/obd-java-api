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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import pt.lighthouselabs.obd.commands.ObdCommand;
import pt.lighthouselabs.obd.commands.SpeedObdCommand;
import pt.lighthouselabs.obd.enums.AvailableCommandNames;

/**
 * TODO put description
 */
public class FuelEconomyObdCommand extends ObdCommand {

  protected float kml = -1.0f;

  /**
   * Default ctor.
   */
  public FuelEconomyObdCommand() {
    super("");
  }

  @Override
  protected void performCalculations() {
    // do nothing as we're running a fake command
  }

  /**
   * As it's a fake command, neither do we need to send request or read
   * response.
   */
  @Override
  public void run(InputStream in, OutputStream out) throws IOException,
      InterruptedException {
    // get consumption liters per hour
    final FuelConsumptionRateObdCommand fuelConsumptionCommand = new FuelConsumptionRateObdCommand();
    fuelConsumptionCommand.run(in, out);

    // get metric speed
    final SpeedObdCommand speedCommand = new SpeedObdCommand();
    speedCommand.run(in, out);

    // get l/100km
    kml = (100 / speedCommand.getMetricSpeed())
        * fuelConsumptionCommand.getLitersPerHour();
  }

  @Override
  public String getFormattedResult() {
    return useImperialUnits ? String.format("%.1f %s", getMilesPerUKGallon(),
        "mpg") : String.format("%.1f %s", kml, "l/100km");
  }

  /**
   * @return a float.
   */
  public float getLitersPer100Km() {
    return kml;
  }

  /**
   * @return a float.
   */
  public float getMilesPerUSGallon() {
    return 235.2f / kml;
  }

  /**
   * @return a float.
   */
  public float getMilesPerUKGallon() {
    return 282.5f / kml;
  }

  @Override
  public String getName() {
    return AvailableCommandNames.FUEL_ECONOMY.getValue();
  }

}