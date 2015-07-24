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
package com.github.pires.obd.commands.control;

import com.github.pires.obd.commands.PersistentObdCommand;
import com.github.pires.obd.enums.AvailableCommandNames;

public class VinObdCommand extends PersistentObdCommand {

    String vin = "";
    int[] bufferUse = new int[]{
            2, 3, 4, 5, 6,
            9, 10, 11, 12, 13,
            16, 17, 18, 19, 20,
            23, 24, 25, 26, 27,
            30, 31, 32, 33, 34
    };

    /**
     * Default ctor.
     */
    public VinObdCommand() {
        super("09 02");
    }

    /**
     * Copy ctor.
     *
     * @param other a {@link VinObdCommand} object.
     */
    public VinObdCommand(VinObdCommand other) {
        super(other);
    }

    @Override
    protected void performCalculations() {
        // ignore first two bytes [01 31] of the response
        StringBuilder b = new StringBuilder();
        for (int i : bufferUse) {
            b.append(new Character((char) buffer.get(i).intValue()).toString());
        }
        vin = b.toString().replaceAll("[\u0000-\u001f]", "");
    }

    @Override
    public String getFormattedResult() {
        return String.valueOf(vin);
    }

    @Override
    public String getName() {
        return AvailableCommandNames.VIN.getValue();
    }

    @Override
    public String getCalculatedResult() {
        return String.valueOf(vin);
    }

}


