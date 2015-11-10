package com.github.pires.obd.commands;

import org.powermock.core.classloader.annotations.PrepareForTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.powermock.api.easymock.PowerMock.*;
import static org.testng.Assert.assertEquals;

/**
 * Test results with echo on and off.
 */
@PrepareForTest(InputStream.class)
public class ExtraMessagesTest {

    private SpeedCommand command;
    private InputStream mockIn;

    /**
     * @throws Exception
     */
    @BeforeMethod
    public void setUp() throws Exception {
        command = new SpeedCommand();
    }

    /**
     * Test for valid InputStream read with echo
     *
     * @throws java.io.IOException
     */
    @Test
    public void testValidSpeedMetricWithMessage() throws IOException {
        // mock InputStream read
        mockIn = createMock(InputStream.class);
        mockIn.read();
        expectLastCall().andReturn((byte) 'B');
        expectLastCall().andReturn((byte) 'U');
        expectLastCall().andReturn((byte) 'S');
        expectLastCall().andReturn((byte) ' ');
        expectLastCall().andReturn((byte) 'I');
        expectLastCall().andReturn((byte) 'N');
        expectLastCall().andReturn((byte) 'I');
        expectLastCall().andReturn((byte) 'T');
        expectLastCall().andReturn((byte) '.');
        expectLastCall().andReturn((byte) '.');
        expectLastCall().andReturn((byte) '.');
        expectLastCall().andReturn((byte) 13);
        expectLastCall().andReturn((byte) '4');
        expectLastCall().andReturn((byte) '1');
        expectLastCall().andReturn((byte) ' ');
        expectLastCall().andReturn((byte) '0');
        expectLastCall().andReturn((byte) 'D');
        expectLastCall().andReturn((byte) ' ');
        expectLastCall().andReturn((byte) '4');
        expectLastCall().andReturn((byte) '0');
        expectLastCall().andReturn((byte) '>');

        replayAll();

        // call the method to test
        command.readResult(mockIn);
        command.getFormattedResult();
        assertEquals(command.getMetricSpeed(), 64);

        verifyAll();
    }

    /**
     * Test for valid InputStream read with echo
     *
     * @throws java.io.IOException
     */
    @Test
    public void testValidSpeedMetricWithoutMessage() throws IOException {
        // mock InputStream read
        mockIn = createMock(InputStream.class);
        mockIn.read();
        expectLastCall().andReturn((byte) '4');
        expectLastCall().andReturn((byte) '1');
        expectLastCall().andReturn((byte) ' ');
        expectLastCall().andReturn((byte) '0');
        expectLastCall().andReturn((byte) 'D');
        expectLastCall().andReturn((byte) ' ');
        expectLastCall().andReturn((byte) '4');
        expectLastCall().andReturn((byte) '0');
        expectLastCall().andReturn((byte) '>');

        replayAll();

        // call the method to test
        command.readResult(mockIn);
        command.getFormattedResult();
        assertEquals(command.getMetricSpeed(), 64);

        verifyAll();
    }

}
