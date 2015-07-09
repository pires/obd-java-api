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
package pt.lighthouselabs.obd.commands.protocol;

import pt.lighthouselabs.obd.commands.PersistentObdCommand;
import pt.lighthouselabs.obd.enums.AvailableCommandNames;

public class AvailablePidsObdCommand extends PersistentObdCommand {

  /**
   * Default ctor.
   */
  public AvailablePidsObdCommand() {
    super("01 00");
  }

  /**
   * Copy ctor.
   *
   * @param other a {@link AvailablePidsObdCommand} object.
   */
  public AvailablePidsObdCommand(AvailablePidsObdCommand other) {
    super(other);
  }

  @Override
  protected void performCalculations() {

  }

  @Override
  public String getFormattedResult() {
    return String.valueOf(rawData);
  }

  @Override
  public String getName() {
    return AvailableCommandNames.PIDS.getValue();
  }

}
