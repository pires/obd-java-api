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

import com.github.pires.obd.commands.engine.MassAirFlowCommand;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.powermock.api.easymock.PowerMock.*;
import static org.testng.Assert.assertEquals;

/**
 * Tests for MassAirFlowCommand class.
 */
@PrepareForTest(InputStream.class)
public class MassAirFlowCommandTest {
    private MassAirFlowCommand command;
    private InputStream mockIn;

    /**
     * @throws Exception
     */
    @BeforeMethod
    public void setUp() throws Exception {
        command = new MassAirFlowCommand();
    }

    /**
     * Test for valid InputStream read, maximum value of 655.35g/s
     *
     * @throws IOException
     */
    @Test
    public void testMaxMAFValue() throws IOException {
        // mock InputStream read
        mockIn = createMock(InputStream.class);
        mockIn.read();
        expectLastCall().andReturn((byte) '4');
        expectLastCall().andReturn((byte) '1');
        expectLastCall().andReturn((byte) ' ');
        expectLastCall().andReturn((byte) '1');
        expectLastCall().andReturn((byte) '0');
        expectLastCall().andReturn((byte) ' ');
        expectLastCall().andReturn((byte) 'F');
        expectLastCall().andReturn((byte) 'F');
        expectLastCall().andReturn((byte) ' ');
        expectLastCall().andReturn((byte) 'F');
        expectLastCall().andReturn((byte) 'F');
        expectLastCall().andReturn((byte) '>');

        replayAll();

        // call the method to test
        command.readResult(mockIn);
        assertEquals(command.getMAF(), 655.3499755859375d);

        verifyAll();
    }

    /**
     * Test for valid InputStream read, 381.61g/s
     *
     * @throws IOException
     */
    @Test
    public void testSomeMAFValue() throws IOException {
        // mock InputStream read
        mockIn = createMock(InputStream.class);
        mockIn.read();
        expectLastCall().andReturn((byte) '4');
        expectLastCall().andReturn((byte) '1');
        expectLastCall().andReturn((byte) ' ');
        expectLastCall().andReturn((byte) '1');
        expectLastCall().andReturn((byte) '0');
        expectLastCall().andReturn((byte) ' ');
        expectLastCall().andReturn((byte) '9');
        expectLastCall().andReturn((byte) '5');
        expectLastCall().andReturn((byte) ' ');
        expectLastCall().andReturn((byte) '1');
        expectLastCall().andReturn((byte) '1');
        expectLastCall().andReturn((byte) '>');

        replayAll();

        // call the method to test
        command.readResult(mockIn);
        assertEquals(command.getMAF(), 381.6099853515625d);

        verifyAll();
    }

    /**
     * Test for valid InputStream read, minimum value 0g/s
     *
     * @throws IOException
     */
    @Test
    public void testMinMAFValue() throws IOException {
        // mock InputStream read
        mockIn = createMock(InputStream.class);
        mockIn.read();
        expectLastCall().andReturn((byte) '4');
        expectLastCall().andReturn((byte) '1');
        expectLastCall().andReturn((byte) ' ');
        expectLastCall().andReturn((byte) '1');
        expectLastCall().andReturn((byte) '0');
        expectLastCall().andReturn((byte) ' ');
        expectLastCall().andReturn((byte) '0');
        expectLastCall().andReturn((byte) '0');
        expectLastCall().andReturn((byte) ' ');
        expectLastCall().andReturn((byte) '0');
        expectLastCall().andReturn((byte) '0');
        expectLastCall().andReturn((byte) '>');

        replayAll();

        // call the method to test
        command.readResult(mockIn);
        assertEquals(command.getMAF(), 0d);

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
