package com.github.pires.obd.utils;

public abstract class CommandAvailabilityHelper {

    /*
    All around this class, integers are used where unsigned bytes should be used. Too bad they don't exist in Java.
    Thank you Oracle.
     */

    /**
     * Digests the given string into an array of integers which can be used to check for command availability
     *
     * @param availabilityString An 8*n (where n is an integer) character string containing only numbers and uppercase letters from A to F
     * @return An integer array containing the digested information
     */
    public static int[] digestAvailabilityString(final String availabilityString) {
        //The string must have 8*n characters, n being an integer
        if (availabilityString.length() % 8 != 0) {
            throw new IllegalArgumentException("Invalid length for Availability String supplied: " + availabilityString);
        }

        //Each two characters of the string will be digested into one byte, thus the resulting array will 
        //have half the elements the string has
        int[] availabilityArray = new int[availabilityString.length() / 2];

        for (int i = 0, a = 0; i < availabilityArray.length; ++i, a += 2) {
            //First character is more significant
            availabilityArray[i] = 16 * parseHexChar(availabilityString.charAt(a)) + parseHexChar(availabilityString.charAt(a + 1));
        }

        return availabilityArray;
    }

    private static int parseHexChar(char hexChar) {
        switch (hexChar) {
            case '0':
                return 0;

            case '1':
                return 1;

            case '2':
                return 2;

            case '3':
                return 3;

            case '4':
                return 4;

            case '5':
                return 5;

            case '6':
                return 6;

            case '7':
                return 7;

            case '8':
                return 8;

            case '9':
                return 9;

            case 'A':
                return 10;

            case 'B':
                return 11;

            case 'C':
                return 12;

            case 'D':
                return 13;

            case 'E':
                return 14;

            case 'F':
                return 15;

            default:
                throw new IllegalArgumentException("Invalid character [" + hexChar + "] supplied");
        }
    }

    /**
     * Checks whether the command identified by commandPid is available, as noted by availabilityString.
     * <p/>
     * This is a direct call to {@link CommandAvailabilityHelper#isAvailable(String, int[])} with built-in String digestion
     */
    public static boolean isAvailable(final String commandPid, final String availabilityString) {
        return isAvailable(commandPid, digestAvailabilityString(availabilityString));
    }

    /**
     * Checks whether the command identified by commandPid is available, as noted by availabilityArray
     */
    public static boolean isAvailable(final String commandPid, int[] availabilityArray) {
        //Which byte from the array contains the info we want?
        //Ignore first 3 characters from the command string, which are 2 for PID mode and 1 whitespace
        int cmdNumber = Integer.parseInt(commandPid, 16);
        int arrayIndex = cmdNumber / 8;

        if (arrayIndex > availabilityArray.length - 1)
            throw new IllegalArgumentException("availabilityArray does not contain enough entries to check for command " + commandPid);

        //Subtract 8 from cmdNumber until we have it in the 1-8 range
        while (cmdNumber > 8) {
            cmdNumber -= 8;
        }

        int requestedAvailability;

        switch (cmdNumber) {
            case 1:
                requestedAvailability = 128;
                break;
            case 2:
                requestedAvailability = 64;
                break;
            case 3:
                requestedAvailability = 32;
                break;
            case 4:
                requestedAvailability = 16;
                break;
            case 5:
                requestedAvailability = 8;
                break;
            case 6:
                requestedAvailability = 4;
                break;
            case 7:
                requestedAvailability = 2;
                break;
            case 8:
                requestedAvailability = 1;
                break;
            default:
                throw new RuntimeException("This is not supposed to happen.");
        }

        return requestedAvailability == (requestedAvailability & availabilityArray[arrayIndex]);
    }
}
