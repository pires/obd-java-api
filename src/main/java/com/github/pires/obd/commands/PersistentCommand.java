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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Base persistent OBD command.
 *
 */
public abstract class PersistentCommand extends ObdCommand {

    private static Map<String, String> knownValues = new HashMap<>();
    private static Map<String, ArrayList<Integer>> knownBuffers = new HashMap<>();

    /**
     * <p>Constructor for PersistentCommand.</p>
     *
     * @param command a {@link java.lang.String} object.
     */
    public PersistentCommand(String command) {
        super(command);
    }

    /**
     * <p>Constructor for PersistentCommand.</p>
     *
     * @param other a {@link com.github.pires.obd.commands.ObdCommand} object.
     */
    public PersistentCommand(ObdCommand other) {
        this(other.cmd);
    }

    /**
     * <p>reset.</p>
     */
    public static void reset() {
        knownValues = new HashMap<>();
        knownBuffers = new HashMap<>();
    }

    /**
     * <p>knows.</p>
     *
     * @param cmd a {@link java.lang.Class} object.
     * @return a boolean.
     */
    public static boolean knows(Class cmd) {
        String key = cmd.getSimpleName();
        return knownValues.containsKey(key);
    }

    /** {@inheritDoc} */
    @Override
    protected void readResult(InputStream in) throws IOException {
        super.readResult(in);
        String key = getClass().getSimpleName();
        knownValues.put(key, rawData);
        knownBuffers.put(key, new ArrayList<>(buffer));
    }

    /** {@inheritDoc} */
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
