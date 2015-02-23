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
import pt.lighthouselabs.obd.enums.AvailableCommandNames;

/**
 * This command will for now read MIL (check engine light) state and number of
 * diagnostic trouble codes currently flagged in the ECU.
 * <p>
 * Perhaps in the future we'll extend this to read the 3rd, 4th and 5th bytes of
 * the response in order to store information about the availability and
 * completeness of certain on-board tests.
 */
public class DtcNumberObdCommand extends ObdCommand {

  private int codeCount = 0;
  private boolean milOn = false;

  /**
   * Default ctor.
   */
  public DtcNumberObdCommand() {
    super("01 01");
  }

  /**
   * Copy ctor.
   *
   * @param other a {@link pt.lighthouselabs.obd.commands.control.DtcNumberObdCommand} object.
   */
  public DtcNumberObdCommand(DtcNumberObdCommand other) {
    super(other);
  }

  @Override
  protected void performCalculations() {
    // ignore first two bytes [hh hh] of the response
    final int mil = buffer.get(2);
    milOn = (mil & 0x80) == 128;
    codeCount = mil & 0x7F;
  }

  /**
   * @return a {@link java.lang.String} object.
   */
  public String getFormattedResult() {
    final String res = milOn ? "MIL is ON" : "MIL is OFF";
    return new StringBuilder().append(res).append(codeCount).append(" codes")
        .toString();
  }

  /**
   * @return the number of trouble codes currently flaggd in the ECU.
   */
  public int getTotalAvailableCodes() {
    return codeCount;
  }

  /**
   * 
   * @return the state of the check engine light state.
   */
  public boolean getMilOn() {
    return milOn;
  }

  @Override
  public String getName() {
    return AvailableCommandNames.DTC_NUMBER.getValue();
  }

}
