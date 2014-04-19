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

import org.powermock.core.classloader.annotations.PrepareForTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;

import pt.lighthouselabs.obd.commands.control.TroubleCodesObdCommand;
import pt.lighthouselabs.obd.commands.engine.ThrottlePositionObdCommand;

import static org.powermock.api.easymock.PowerMock.createMock;
import static org.powermock.api.easymock.PowerMock.expectLastCall;
import static org.powermock.api.easymock.PowerMock.replayAll;
import static org.powermock.api.easymock.PowerMock.verifyAll;
import static org.testng.Assert.assertEquals;

/**
 * Tests for ThrottlePositionObdCommand class.
 */
@PrepareForTest(InputStream.class)
public class TroubleCodesObdCommandTest {
  private TroubleCodesObdCommand command;
  private InputStream mockIn;

  /**
   * @throws Exception
   */
  @BeforeMethod
  public void setUp() throws Exception {
    command = new TroubleCodesObdCommand();
  }

  /**
   * Test for two frames with four dtc
   *
   * @throws java.io.IOException
   */
  @Test
  public void twoFramesWithFourDTC() throws IOException {
    // mock InputStream read
    mockIn = createMock(InputStream.class);
    mockIn.read();
    expectLastCall().andReturn((byte) '4');
    expectLastCall().andReturn((byte) '3');
    expectLastCall().andReturn((byte) ' ');
    expectLastCall().andReturn((byte) '0');
    expectLastCall().andReturn((byte) '0');
    expectLastCall().andReturn((byte) ' ');
    expectLastCall().andReturn((byte) '0');
    expectLastCall().andReturn((byte) '3');
    expectLastCall().andReturn((byte) ' ');
    expectLastCall().andReturn((byte) '5');
    expectLastCall().andReturn((byte) '1');
    expectLastCall().andReturn((byte) ' ');
    expectLastCall().andReturn((byte) '0');
    expectLastCall().andReturn((byte) '4');
    expectLastCall().andReturn((byte) ' ');
    expectLastCall().andReturn((byte) 'A');
    expectLastCall().andReturn((byte) '1');
    expectLastCall().andReturn((byte) ' ');
    expectLastCall().andReturn((byte) 'A');
    expectLastCall().andReturn((byte) 'B');
    expectLastCall().andReturn((byte) 13);
    expectLastCall().andReturn((byte) '4');
    expectLastCall().andReturn((byte) '3');
    expectLastCall().andReturn((byte) ' ');
    expectLastCall().andReturn((byte) 'F');
    expectLastCall().andReturn((byte) '1');
    expectLastCall().andReturn((byte) ' ');
    expectLastCall().andReturn((byte) '0');
    expectLastCall().andReturn((byte) '6');
    expectLastCall().andReturn((byte) ' ');
    expectLastCall().andReturn((byte) '0');
    expectLastCall().andReturn((byte) '0');
    expectLastCall().andReturn((byte) ' ');
    expectLastCall().andReturn((byte) '0');
    expectLastCall().andReturn((byte) '0');
    expectLastCall().andReturn((byte) ' ');
    expectLastCall().andReturn((byte) '0');
    expectLastCall().andReturn((byte) '0');
    expectLastCall().andReturn((byte) ' ');
    expectLastCall().andReturn((byte) '0');
    expectLastCall().andReturn((byte) '0');
    expectLastCall().andReturn((byte) '>');

    replayAll();
    String res = "P0003\n";
    res += "C1104\n";
    res += "B21AB\n";
    res += "U3106\n";

    // call the method to test
    command.readResult(mockIn);

    assertEquals(command.getFormattedResult(), res);

    verifyAll();
  }

  /**
   * Test for one frame with three dtc
   *
   * @throws java.io.IOException
   */
  @Test
  public void oneFrameWithThreeDTC() throws IOException {
    // mock InputStream read
    mockIn = createMock(InputStream.class);
    mockIn.read();
    expectLastCall().andReturn((byte) '4');
    expectLastCall().andReturn((byte) '3');
    expectLastCall().andReturn((byte) ' ');
    expectLastCall().andReturn((byte) '0');
    expectLastCall().andReturn((byte) '1');
    expectLastCall().andReturn((byte) ' ');
    expectLastCall().andReturn((byte) '0');
    expectLastCall().andReturn((byte) '3');
    expectLastCall().andReturn((byte) ' ');
    expectLastCall().andReturn((byte) '0');
    expectLastCall().andReturn((byte) '1');
    expectLastCall().andReturn((byte) ' ');
    expectLastCall().andReturn((byte) '0');
    expectLastCall().andReturn((byte) '4');
    expectLastCall().andReturn((byte) ' ');
    expectLastCall().andReturn((byte) '0');
    expectLastCall().andReturn((byte) '1');
    expectLastCall().andReturn((byte) ' ');
    expectLastCall().andReturn((byte) '0');
    expectLastCall().andReturn((byte) '5');
    expectLastCall().andReturn((byte) '>');

    replayAll();
    String res = "P0103\n";
    res += "P0104\n";
    res += "P0105\n";

    // call the method to test
    command.readResult(mockIn);

    assertEquals(command.getFormattedResult(), res);

    verifyAll();
  }

  /**
   * Test for one frame with two dtc
   *
   * @throws java.io.IOException
   */
  @Test
  public void oneFrameWithTwoDTC() throws IOException {
    // mock InputStream read
    mockIn = createMock(InputStream.class);
    mockIn.read();
    expectLastCall().andReturn((byte) '4');
    expectLastCall().andReturn((byte) '3');
    expectLastCall().andReturn((byte) ' ');
    expectLastCall().andReturn((byte) '0');
    expectLastCall().andReturn((byte) '1');
    expectLastCall().andReturn((byte) ' ');
    expectLastCall().andReturn((byte) '0');
    expectLastCall().andReturn((byte) '3');
    expectLastCall().andReturn((byte) ' ');
    expectLastCall().andReturn((byte) '0');
    expectLastCall().andReturn((byte) '1');
    expectLastCall().andReturn((byte) ' ');
    expectLastCall().andReturn((byte) '0');
    expectLastCall().andReturn((byte) '4');
    expectLastCall().andReturn((byte) ' ');
    expectLastCall().andReturn((byte) '0');
    expectLastCall().andReturn((byte) '0');
    expectLastCall().andReturn((byte) ' ');
    expectLastCall().andReturn((byte) '0');
    expectLastCall().andReturn((byte) '0');
    expectLastCall().andReturn((byte) '>');

    replayAll();
    String res = "P0103\n";
    res += "P0104\n";

    // call the method to test
    command.readResult(mockIn);

    assertEquals(command.getFormattedResult(), res);

    verifyAll();
  }

  /**
   * Test for no data
   *
   * @throws java.io.IOException
   */
  @Test
  public void noData() throws IOException {
    // mock InputStream read
    mockIn = createMock(InputStream.class);
    mockIn.read();
    expectLastCall().andReturn((byte) '4');
    expectLastCall().andReturn((byte) '3');
    expectLastCall().andReturn((byte) ' ');
    expectLastCall().andReturn((byte) 'N');
    expectLastCall().andReturn((byte) 'O');
    expectLastCall().andReturn((byte) ' ');
    expectLastCall().andReturn((byte) 'D');
    expectLastCall().andReturn((byte) 'A');
    expectLastCall().andReturn((byte) 'T');
    expectLastCall().andReturn((byte) 'A');
    expectLastCall().andReturn((byte) '>');

    replayAll();
    String res = "NODATA";

    // call the method to test
    command.readResult(mockIn);

    assertEquals(command.getFormattedResult(), res);

    verifyAll();
  }

  /**
   * Clear resources.
   */
  @AfterClass
  public void tearDown() {
    command = null;
    mockIn = null;
  }

}