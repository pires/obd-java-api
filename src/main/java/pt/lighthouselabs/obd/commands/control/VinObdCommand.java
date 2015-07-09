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

import pt.lighthouselabs.obd.commands.PersistentObdCommand;
import pt.lighthouselabs.obd.enums.AvailableCommandNames;

public class VinObdCommand extends PersistentObdCommand {

  String vin = "";

  /**
   * Default ctor.
   */
  public VinObdCommand() {
    super("09 02");
  }

  /**
   * Copy ctor.
   *
   * @param other a {@link VinObdCommand} object.
   */
  public VinObdCommand(VinObdCommand other) {
    super(other);
  }

  @Override
  protected void performCalculations() {
    // ignore first two bytes [01 31] of the response
    StringBuilder b = new StringBuilder();
    for (int i = 2; i < buffer.size(); i++) {
      b.append(Character.toChars(buffer.get(i)));
    }
    vin = b.toString();
  }

  @Override
  public String getFormattedResult() {
    return String.valueOf(rawData);
  }

  @Override
  public String getName() {
    return AvailableCommandNames.VIN.getValue();
  }

}
