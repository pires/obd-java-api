package com.github.pires.obd.commands;

import com.github.pires.obd.commands.control.VinCommand;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.powermock.api.easymock.PowerMock.*;
import static org.testng.Assert.assertEquals;

/**
 * Tests for VinCommand class.
 */
public class VinCommandTest {
    private VinCommand command;
    private InputStream mockIn;

    /**
     * @throws Exception
     */
    @BeforeMethod
    public void setUp() throws Exception {
        command = new VinCommand();
        // mock InputStream read
        mockIn = createMock(InputStream.class);
        mockIn.read();
    }

    /**
     * Clear resources.
     */
    @AfterClass
    public void tearDown() {
        command = null;
        mockIn = null;
    }

    /**
     * Test VIN CAN (ISO-15765) format
     *
     * @throws IOException
     */
    @Test
    public void vinCAN() throws IOException {
        byte[] v = new byte[]{
                '0', '1', '4', '\n',
                '0', ':', ' ', '4', '9', ' ', '0', '2', ' ', '0', '1', ' ', '5', '7', ' ', '5', '0', ' ', '3', '0', '\n',
                '1', ':', ' ', '5', 'A', ' ', '5', 'A', ' ', '5', 'A', ' ', '3', '9', ' ', '3', '9', ' ', '5', 'A', ' ', '5', '4', '\n',
                '2', ':', ' ', '5', '3', ' ', '3', '3', ' ', '3', '9', ' ', '3', '2', ' ', '3', '1', ' ', '3', '2', ' ', '3', '4', '>'
        };
        for (byte b : v) {
            expectLastCall().andReturn(b);
        }
        replayAll();
        String res = "WP0ZZZ99ZTS392124";

        // call the method to test
        command.readResult(mockIn);

        assertEquals(command.getFormattedResult(), res);

        verifyAll();
    }

    /**
     * Test VIN ISO9141-2, KWP2000 Fast and KWP2000 5Kbps (ISO15031) format
     *
     * @throws IOException
     */
    @Test
    public void vin() throws IOException {
        byte[] v = new byte[]{
                '4', '9', ' ', '0', '2', ' ', '0', '1', ' ', '0', '0', ' ', '0', '0', ' ', '0', '0', ' ', '5', '7', '\n',
                '4', '9', ' ', '0', '2', ' ', '0', '2', ' ', '5', '0', ' ', '3', '0', ' ', '5', 'A', ' ', '5', 'A', '\n',
                '4', '9', ' ', '0', '2', ' ', '0', '3', ' ', '5', 'A', ' ', '3', '9', ' ', '3', '9', ' ', '5', 'A', '\n',
                '4', '9', ' ', '0', '2', ' ', '0', '4', ' ', '5', '4', ' ', '5', '3', ' ', '3', '3', ' ', '3', '9', '\n',
                '4', '9', ' ', '0', '2', ' ', '0', '5', ' ', '3', '2', ' ', '3', '1', ' ', '3', '2', ' ', '3', '4', '>'
        };
        for (byte b : v) {
            expectLastCall().andReturn(b);
        }

        replayAll();
        String res = "WP0ZZZ99ZTS392124";

        // call the method to test
        command.readResult(mockIn);

        assertEquals(command.getFormattedResult(), res);

        verifyAll();
    }
}