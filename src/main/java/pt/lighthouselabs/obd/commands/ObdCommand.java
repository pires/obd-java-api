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
package pt.lighthouselabs.obd.commands;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Base OBD command.
 */
public abstract class ObdCommand {

  protected static final String NODATA = "NODATA";

  protected ArrayList<Integer> buffer = null;
  protected String cmd = null;
  protected boolean useImperialUnits = false;
  protected String rawData = null;

  /**
   * Default ctor to use
   * 
   * @param command
   *          the command to send
   */
  public ObdCommand(String command) {
    this.cmd = command;
    this.buffer = new ArrayList<Integer>();
  }

  /**
   * Prevent empty instantiation
   */
  private ObdCommand() {
  }

  /**
   * Copy ctor.
   * 
   * @param other
   *          the ObdCommand to copy.
   */
  public ObdCommand(ObdCommand other) {
    this(other.cmd);
  }

  /**
   * Sends the OBD-II request and deals with the response.
   * 
   * This method CAN be overriden in fake commands.
   */
  public void run(InputStream in, OutputStream out) throws IOException,
      InterruptedException {
    sendCommand(out);
    readResult(in);
  }

  /**
   * Sends the OBD-II request.
   * 
   * This method may be overriden in subclasses, such as ObMultiCommand or
   * TroubleCodesObdCommand.
   * 
   * @param out
   *          The output stream.
   */
  protected void sendCommand(OutputStream out) throws IOException,
      InterruptedException {
    // add the carriage return char
    cmd += "\r";

    // write to OutputStream (i.e.: a BluetoothSocket)
    out.write(cmd.getBytes());
    out.flush();

    /*
     * HACK GOLDEN HAMMER ahead!!
     * 
     * Due to the time that some systems may take to respond, let's give it
     * 200ms.
     */
    Thread.sleep(200);
  }

  /**
   * Resends this command.
   */
  protected void resendCommand(OutputStream out) throws IOException,
      InterruptedException {
    out.write("\r".getBytes());
    out.flush();
  }

  /**
   * Reads the OBD-II response.
   * <p>
   * This method may be overriden in subclasses, such as ObdMultiCommand.
   */
  protected void readResult(InputStream in) throws IOException {
    readRawData(in);
    fillBuffer();
    performCalculations();
  }

  /**
   * This method exists so that for each command, there must be a method that is
   * called only once to perform calculations.
   */
  protected abstract void performCalculations();

  /**
   * 
   */
  protected void fillBuffer() {
    // read string each two chars
    buffer.clear();
    int begin = 0;
    int end = 2;
    while (end <= rawData.length()) {
      buffer.add(Integer.decode("0x" + rawData.substring(begin, end)));
      begin = end;
      end += 2;
    }
  }

  protected void readRawData(InputStream in) throws IOException {
    byte b = 0;
    StringBuilder res = new StringBuilder();

    // read until '>' arrives
    while ((char) (b = (byte) in.read()) != '>')
      if ((char) b != ' ')
        res.append((char) b);

    /*
     * Imagine the following response 41 0c 00 0d.
     * 
     * ELM sends strings!! So, ELM puts spaces between each "byte". And pay
     * attention to the fact that I've put the word byte in quotes, because 41
     * is actually TWO bytes (two chars) in the socket. So, we must do some more
     * processing..
     */
    rawData = res.toString().trim();

    /*
     * Data may have echo or informative text like "INIT BUS..." or similar.
     * The response ends with two carriage return characters. So we need to take
     * everything from the last carriage return before those two (trimmed above).
     */
    rawData = rawData.substring(rawData.lastIndexOf(13) + 1);
  }

  /**
   * @return the raw command response in string representation.
   */
  public String getResult() {
    rawData = rawData.contains("SEARCHING") || rawData.contains("DATA") ? NODATA
        : rawData;

    return rawData;
  }

  /**
   * @return a formatted command response in string representation.
   */
  public abstract String getFormattedResult();

  /**
   * @return a list of integers
   */
  protected ArrayList<Integer> getBuffer() {
    return buffer;
  }

  /**
   * @return true if imperial units are used, or false otherwise
   */
  public boolean useImperialUnits() {
    return useImperialUnits;
  }

  /**
   * Set to 'true' if you want to use imperial units, false otherwise. By
   * default this value is set to 'false'.
   * 
   * @param isImperial
   */
  public void useImperialUnits(boolean isImperial) {
    this.useImperialUnits = isImperial;
  }

  /**
   * @return the OBD command name.
   */
  public abstract String getName();

}
