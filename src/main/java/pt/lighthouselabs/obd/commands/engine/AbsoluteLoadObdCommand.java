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
 * Fuel systems that use conventional oxygen sensor display the commanded open
 * loop equivalence ratio while the system is in open loop. Should report 100%
 * when in closed loop fuel.
 * <p>
 * To obtain the actual air/fuel ratio being commanded, multiply the
 * stoichiometric A/F ratio by the equivalence ratio. For example, gasoline,
 * stoichiometric is 14.64:1 ratio. If the fuel control system was commanded an
 * equivalence ratio of 0.95, the commanded A/F ratio to the engine would be
 * 14.64 * 0.95 = 13.9 A/F.
 */
public class AbsoluteLoadObdCommand extends PercentageObdCommand {


  /**
   * Default ctor.
   */
  public AbsoluteLoadObdCommand() {
    super("01 43");
  }

  /**
   * Copy ctor.
   *
   * @param other a {@link AbsoluteLoadObdCommand} object.
   */
  public AbsoluteLoadObdCommand(AbsoluteLoadObdCommand other) {
    super(other);
  }

  @Override
  protected void performCalculations() {
    // ignore first two bytes [hh hh] of the response
    int a = buffer.get(2);
    int b = buffer.get(3);
    percentage = (a * 256 + b) *100/255;

//((A*256)+B)*100/255
  }

  /**
   * @return a double.
   */
  public double getRatio() {
    return percentage;
  }

  @Override
  public String getName() {
    return AvailableCommandNames.EQUIV_RATIO.getValue();
  }

}
