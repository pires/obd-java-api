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

import static org.powermock.api.easymock.PowerMock.createMock;
import static org.powermock.api.easymock.PowerMock.expectLastCall;
import static org.powermock.api.easymock.PowerMock.replayAll;
import static org.powermock.api.easymock.PowerMock.verifyAll;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;

import org.powermock.core.classloader.annotations.PrepareForTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pt.lighthouselabs.obd.commands.fuel.FuelTrimObdCommand;
import pt.lighthouselabs.obd.enums.FuelTrim;

/**
 * Tests for FuelTrimObdCommand class.
 */
@PrepareForTest(InputStream.class)
public class FuelTrimObdCommandTest {

  private FuelTrimObdCommand command;
  private InputStream mockIn;
  private FuelTrim fuelTrimBank = FuelTrim.LONG_TERM_BANK_1;

  /**
   * @throws Exception
   */
  @BeforeMethod
  public void setUp() throws Exception {
    command = new FuelTrimObdCommand(FuelTrim.LONG_TERM_BANK_1);
  }

  /**
   * Test for valid InputStream read, 99.22%
   * 
   * @throws IOException
   */
  @Test(enabled = false)
  public void testMaxFuelTrimValue() throws IOException {
    // mock InputStream read
    mockIn = createMock(InputStream.class);
    mockIn.read();
    expectLastCall().andReturn(0x41);
    expectLastCall().andReturn(fuelTrimBank.getValue());
    expectLastCall().andReturn(0xFF);
    expectLastCall().andReturn(0x3E); // '>'

    replayAll();

    // call the method to test
    command.readResult(mockIn);
    assertEquals(command.getFormattedResult(), "99.22%");

    verifyAll();
  }

  /**
   * Test for valid InputStream read, 56.25%
   * 
   * @throws IOException
   */
  @Test(enabled = false)
  public void testSomeValue() throws IOException {
    // mock InputStream read
    mockIn = createMock(InputStream.class);
    mockIn.read();
    expectLastCall().andReturn(0x41);
    expectLastCall().andReturn(0x20);
    expectLastCall().andReturn(fuelTrimBank.getValue());
    expectLastCall().andReturn(0x20);
    expectLastCall().andReturn(0xC8);
    expectLastCall().andReturn(0x20);
    expectLastCall().andReturn(0x3E); // '>'

    replayAll();

    // call the method to test
    command.readResult(mockIn);
    assertEquals(command.getFormattedResult(), "56.25%");

    verifyAll();
  }

  /**
   * Test for valid InputStream read, -100.00%
   * 
   * @throws IOException
   */
  @Test(enabled = false)
  public void testMinFuelTrimValue() throws IOException {
    // mock InputStream read
    mockIn = createMock(InputStream.class);
    mockIn.read();
    expectLastCall().andReturn(0x41);
    expectLastCall().andReturn(0x20);
    expectLastCall().andReturn(fuelTrimBank.getValue());
    expectLastCall().andReturn(0x20);
    expectLastCall().andReturn(0x00);
    expectLastCall().andReturn(0x20);
    expectLastCall().andReturn(0x3E); // '>'

    replayAll();

    // call the method to test
    command.readResult(mockIn);
    assertEquals(command.getFormattedResult(), "-100.00%");

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