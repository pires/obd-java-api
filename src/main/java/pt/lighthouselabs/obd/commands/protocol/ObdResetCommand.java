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

import java.io.IOException;
import java.io.InputStream;

/**
 * This method will reset the OBD connection.
 */
public class ObdResetCommand extends ObdProtocolCommand {

  public ObdResetCommand() {
    super("AT Z");
  }

  /**
   * @param other
   */
  public ObdResetCommand(ObdResetCommand other) {
    super(other);
  }

  /**
   * Reset command returns an empty string, so we must override the following
   * two methods.
   * 
   * @throws IOException
   */
  @Override
  public void readResult(InputStream in) throws IOException {
    // do nothing
    return;
  }

  @Override
  public String getResult() {
    return "";
  }

  @Override
  public String getFormattedResult() {
    return getResult();
  }

  @Override
  public String getName() {
    return "Reset OBD";
  }

}