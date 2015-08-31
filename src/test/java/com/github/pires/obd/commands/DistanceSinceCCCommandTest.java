package com.github.pires.obd.commands;

import com.github.pires.obd.commands.control.DistanceSinceCCCommand;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.powermock.api.easymock.PowerMock.*;
import static org.testng.Assert.assertEquals;

/**
 * Runtime since engine start in seconds, with a maximum value of 65535.
 */
@PrepareForTest(InputStream.class)
public class DistanceSinceCCCommandTest {

    private DistanceSinceCCCommand command;
    private InputStream mockIn;

    /**
     * @throws Exception
     */
    @BeforeMethod
    public void setUp() throws Exception {
        command = new DistanceSinceCCCommand();
    }

    /**
     * Test for valid InputStream read, 65535 km.
     *
     * @throws IOException
     */
    @Test
    public void testMaxDistanceValue() throws IOException {
        // mock InputStream read
        mockIn = createMock(InputStream.class);
        mockIn.read();
        expectLastCall().andReturn((byte) '4');
        expectLastCall().andReturn((byte) '1');
        expectLastCall().andReturn((byte) ' ');
        expectLastCall().andReturn((byte) '3');
        expectLastCall().andReturn((byte) '1');
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
        assertEquals(command.getKm(), 65535);

        verifyAll();
    }

    /**
     * Test for valid InputStream read, 17731 kms
     *
     * @throws IOException
     */
    @Test
    public void testSomeRuntimeValue() throws IOException {
        // mock InputStream read
        mockIn = createMock(InputStream.class);
        mockIn.read();
        expectLastCall().andReturn((byte) '4');
        expectLastCall().andReturn((byte) '1');
        expectLastCall().andReturn((byte) ' ');
        expectLastCall().andReturn((byte) '3');
        expectLastCall().andReturn((byte) '1');
        expectLastCall().andReturn((byte) ' ');
        expectLastCall().andReturn((byte) '4');
        expectLastCall().andReturn((byte) '5');
        expectLastCall().andReturn((byte) ' ');
        expectLastCall().andReturn((byte) '4');
        expectLastCall().andReturn((byte) '3');
        expectLastCall().andReturn((byte) '>');

        replayAll();

        // call the method to test
        command.readResult(mockIn);
        assertEquals(command.getKm(), 17731);

        verifyAll();
    }

    /**
     * Test for valid InputStream read, 0 km.
     *
     * @throws IOException
     */
    @Test
    public void testMinRuntimeValue() throws IOException {
        // mock InputStream read
        mockIn = createMock(InputStream.class);
        mockIn.read();
        expectLastCall().andReturn((byte) '4');
        expectLastCall().andReturn((byte) '1');
        expectLastCall().andReturn((byte) ' ');
        expectLastCall().andReturn((byte) '3');
        expectLastCall().andReturn((byte) '1');
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
        assertEquals(command.getKm(), 0);

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
