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
package pt.lighthouselabs.obd.commands.engine;

import pt.lighthouselabs.obd.commands.PercentageObdCommand;
import pt.lighthouselabs.obd.enums.AvailableCommandNames;

/**
 * Read the throttle position in percentage.
 */
public class ThrottlePositionObdCommand extends PercentageObdCommand {

  /**
   * Default ctor.
   */
  public ThrottlePositionObdCommand() {
    super("01 11");
  }

  /**
   * Copy ctor.
   *
   * @param other a {@link pt.lighthouselabs.obd.commands.engine.ThrottlePositionObdCommand} object.
   */
  public ThrottlePositionObdCommand(ThrottlePositionObdCommand other) {
    super(other);
  }

  /**
	 * 
	 */
  @Override
  public String getName() {
    return AvailableCommandNames.THROTTLE_POS.getValue();
  }

}