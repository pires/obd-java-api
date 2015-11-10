package com.github.pires.obd.exceptions;

import com.github.pires.obd.commands.SpeedCommand;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.powermock.api.easymock.PowerMock.*;

/**
 * Test results with echo on and off.
 */
@PrepareForTest(InputStream.class)
public class UnsupportedCommandExceptionTest {

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
     * @throws java.io.IOException, java.lang.InterruptedException
     */
    @Test(expectedExceptions = UnsupportedCommandException.class)
    public void testUnsupportedVin() throws IOException, InterruptedException {
        // mock InputStream read
        mockIn = createMock(InputStream.class);
        mockIn.read();
        expectLastCall().andReturn((byte) '7');
        expectLastCall().andReturn((byte) 'F');
        expectLastCall().andReturn((byte) '0');
        expectLastCall().andReturn((byte) '9');
        expectLastCall().andReturn((byte) '1');
        expectLastCall().andReturn((byte) '2');
        expectLastCall().andReturn((byte) '>');

        replayAll();

        // call the method to test
        command.run(mockIn, new ByteArrayOutputStream());

        verifyAll();
    }

    /**
     * Test for valid InputStream read with echo
     *
     * @throws java.io.IOException, java.lang.InterruptedException
     */
    @Test(expectedExceptions = UnsupportedCommandException.class)
    public void testUnsupportedSpeed() throws IOException, InterruptedException {
        // mock InputStream read
        mockIn = createMock(InputStream.class);
        mockIn.read();
        expectLastCall().andReturn((byte) '7');
        expectLastCall().andReturn((byte) 'F');
        expectLastCall().andReturn((byte) '0');
        expectLastCall().andReturn((byte) '1');
        expectLastCall().andReturn((byte) '1');
        expectLastCall().andReturn((byte) '2');
        expectLastCall().andReturn((byte) '>');

        replayAll();

        // call the method to test
        command.run(mockIn, new ByteArrayOutputStream());

        verifyAll();
    }

}
