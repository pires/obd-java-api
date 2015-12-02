package com.github.pires.obd.commands;

import com.github.pires.obd.commands.protocol.DescribeProtocolNumberCommand;
import com.github.pires.obd.enums.ObdProtocols;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.InputStream;

import static org.powermock.api.easymock.PowerMock.*;
import static org.testng.Assert.assertEquals;

/**
 * Tests for DescribeProtocolNumberCommand class.
 */
public class DescribeProtocolNumberCommandTest {

    private DescribeProtocolNumberCommand command;
    private InputStream mockIn;

    @BeforeMethod
    public void setUp() throws Exception {
        command = new DescribeProtocolNumberCommand();
    }

    @Test
    public void testGetCalculatedResult() throws Exception {
        mockIn = createMock(InputStream.class);
        mockIn.read();
        expectLastCall().andReturn((byte) 'A');
        expectLastCall().andReturn((byte) '3');
        expectLastCall().andReturn((byte) '>');
        expectLastCall().andReturn((byte) '2');
        expectLastCall().andReturn((byte) '>');

        replayAll();

        // call the method to test
        command.readResult(mockIn);
        assertEquals(command.getCalculatedResult(), ObdProtocols.ISO_9141_2.name());//AUTO ISO_9141_2

        // call the method to test
        command.readResult(mockIn);
        assertEquals(command.getCalculatedResult(), ObdProtocols.SAE_J1850_VPW.name());//SAE_J1850_VPW

        verifyAll();
    }

    @Test
    public void testGetProtocol() throws Exception {
        mockIn = createMock(InputStream.class);
        mockIn.read();
        expectLastCall().andReturn((byte) 'A');
        expectLastCall().andReturn((byte) '6');
        expectLastCall().andReturn((byte) '>');
        expectLastCall().andReturn((byte) '7');
        expectLastCall().andReturn((byte) '>');

        replayAll();

        // call the method to test
        command.readResult(mockIn);
        assertEquals(command.getObdProtocol(), ObdProtocols.ISO_15765_4_CAN);//AUTO ISO_15765_4_CAN

        // call the method to test
        command.readResult(mockIn);
        assertEquals(command.getObdProtocol(), ObdProtocols.ISO_15765_4_CAN_B);//ISO_15765_4_CAN_B

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