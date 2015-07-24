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
/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
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
 */
public abstract class PersistentObdCommand extends ObdCommand {

    private static Map<String, String> knownValues = new HashMap<String, String>();
    private static Map<String, ArrayList<Integer>> knownBuffers = new HashMap<String, ArrayList<Integer>>();

    public PersistentObdCommand(String command) {
        super(command);
    }

    public PersistentObdCommand(ObdCommand other) {
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
