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

import pt.lighthouselabs.obd.enums.AvailableCommandNames;

/**
 * Engine Coolant Temperature.
 */
public class EngineCoolantTemperatureObdCommand extends TemperatureObdCommand {

  /**
	 * 
	 */
  public EngineCoolantTemperatureObdCommand() {
    super("01 05");
  }

  /**
   * @param other a {@link pt.lighthouselabs.obd.commands.temperature.TemperatureObdCommand} object.
   */
  public EngineCoolantTemperatureObdCommand(TemperatureObdCommand other) {
    super(other);
  }

  @Override
  public String getName() {
    return AvailableCommandNames.ENGINE_COOLANT_TEMP.getValue();
  }

}