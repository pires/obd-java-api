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
package com.github.pires.obd.commands;

import com.github.pires.obd.commands.fuel.FuelTrimCommand;
import com.github.pires.obd.enums.FuelTrim;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.powermock.api.easymock.PowerMock.*;
import static org.testng.Assert.assertEquals;

/**
 * Tests for FuelTrimCommand class.
 * <p>
 * TODO replace integer values in expected values with strings, like in other
 * tests.
 */
@PrepareForTest(InputStream.class)
public class FuelTrimCommandTest {

    private FuelTrimCommand command;
    private InputStream mockIn;
    private FuelTrim fuelTrimBank = FuelTrim.LONG_TERM_BANK_1;

    /**
     * @throws Exception
     */
    @BeforeMethod
    public void setUp() throws Exception {
        command = new FuelTrimCommand(FuelTrim.LONG_TERM_BANK_1);
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
        assertEquals(command.getValue(), 99.22f);

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
        assertEquals(command.getValue(), 56.25f);

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
        assertEquals(command.getValue(), -100f);

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
