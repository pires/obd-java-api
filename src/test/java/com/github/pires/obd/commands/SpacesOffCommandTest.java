package com.github.pires.obd.exceptions;

import com.github.pires.obd.commands.protocol.SpacesOffCommand;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.powermock.api.easymock.PowerMock.*;

/**
 * Test results with spacesoff command.
 */
@PrepareForTest(InputStream.class)
public class SpacesOffCommandTest {

    private SpacesOffCommand command;
    private InputStream mockIn;

    /**
     * @throws Exception
     */
    @BeforeMethod
    public void setUp() throws Exception {
        command = new SpacesOffCommand();
    }

    /**
     * @throws java.io.IOException, java.lang.InterruptedException
     */
    @Test
    public void testSpacesOffCommand() throws IOException, InterruptedException {
        // mock InputStream read
        mockIn = createMock(InputStream.class);
        mockIn.read();
        expectLastCall().andReturn((byte) 'O');
        expectLastCall().andReturn((byte) 'K');

        replayAll();

        // call the method to test
        command.readResult(mockIn);
        assertEquals(command.getFormattedResult(), "OK");

        verifyAll();
    }

}
