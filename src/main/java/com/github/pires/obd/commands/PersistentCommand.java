package com.github.pires.obd.commands;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Base persistent OBD command.
 */
public abstract class PersistentCommand extends ObdCommand {

    private static Map<String, String> knownValues = new HashMap<String, String>();
    private static Map<String, ArrayList<Integer>> knownBuffers = new HashMap<String, ArrayList<Integer>>();

    public PersistentCommand(String command) {
        super(command);
    }

    public PersistentCommand(ObdCommand other) {
        this(other.cmd);
    }

    public static void reset() {
        knownValues = new HashMap<String, String>();
        knownBuffers = new HashMap<String, ArrayList<Integer>>();
    }

    public static boolean knows(Class cmd) {
        String key = cmd.getSimpleName();
        return knownValues.containsKey(key);
    }

    @Override
    protected void readResult(InputStream in) throws IOException {
        super.readResult(in);
        String key = getClass().getSimpleName();
        knownValues.put(key, rawData);
        knownBuffers.put(key, new ArrayList<Integer>(buffer));
    }

    @Override
    public void run(InputStream in, OutputStream out) throws IOException, InterruptedException {
        String key = getClass().getSimpleName();
        if (knownValues.containsKey(key)) {
            rawData = knownValues.get(key);
            buffer = knownBuffers.get(key);
            performCalculations();
        } else {
            super.run(in, out);
        }
    }
}
