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
