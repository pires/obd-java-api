package com.github.pires.obd.utils;

import com.github.pires.obd.commands.engine.ThrottlePositionCommand;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandAvailabilityHelperTest {

    @Test
    public void testDigestAvailabilityString() throws Exception {

        String string = "BE1FA813";
        int[] expResult = new int[]{Integer.parseInt("10111110", 2), Integer.parseInt("00011111", 2),
                Integer.parseInt("10101000", 2), Integer.parseInt("00010011", 2)};
        int[] result = CommandAvailabilityHelper.digestAvailabilityString(string);
        assertArrayEquals(expResult, result);

        //Now with 16 characters
        string = "BE1FA813BE1FA813";
        expResult = new int[]{Integer.parseInt("10111110", 2), Integer.parseInt("00011111", 2),
                Integer.parseInt("10101000", 2), Integer.parseInt("00010011", 2),
                Integer.parseInt("10111110", 2), Integer.parseInt("00011111", 2),
                Integer.parseInt("10101000", 2), Integer.parseInt("00010011", 2)};

        result = CommandAvailabilityHelper.digestAvailabilityString(string);
        assertArrayEquals(expResult, result);

        try {
            CommandAvailabilityHelper.digestAvailabilityString("AAA");
            fail("IllegalArgumentException should have been thrown but wasn't");
        } catch (IllegalArgumentException e) {
            //test passed
        }

        try {
            CommandAvailabilityHelper.digestAvailabilityString("AAAAAAAR");
            fail("IllegalArgumentException should have been thrown but wasn't");
        } catch (IllegalArgumentException e) {
            //test passed
        }
    }

    @Test
    public void testIsAvailable() throws Exception {
        assertEquals(CommandAvailabilityHelper.isAvailable("02", "BE1FA813"), false);
        assertEquals(CommandAvailabilityHelper.isAvailable("07", "BE1FA813"), true);
        assertEquals(CommandAvailabilityHelper.isAvailable(new ThrottlePositionCommand().getCommandPID() /*11*/, "BE1FA813"), true);
        assertEquals(CommandAvailabilityHelper.isAvailable("1A", "BE1FA813"), false);
        assertEquals(CommandAvailabilityHelper.isAvailable("1D", "BE1FA813"), false);
        assertEquals(CommandAvailabilityHelper.isAvailable("1F", "BE1FA813"), true);

        try {
            CommandAvailabilityHelper.isAvailable("2F", "BE1FA813");
            fail("IllegalArgumentException should have been thrown but wasn't");
        } catch (IllegalArgumentException e) {
            //test passed
        }

        assertEquals(CommandAvailabilityHelper.isAvailable("22", "BE1FA813BE1FA813"), false);
        assertEquals(CommandAvailabilityHelper.isAvailable("27", "BE1FA813BE1FA813"), true);
        assertEquals(CommandAvailabilityHelper.isAvailable("3A", "BE1FA813BE1FA813"), false);
        assertEquals(CommandAvailabilityHelper.isAvailable("3D", "BE1FA813BE1FA813"), false);
        assertEquals(CommandAvailabilityHelper.isAvailable("3F", "BE1FA813BE1FA813"), true);
    }
}