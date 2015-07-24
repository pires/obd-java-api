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
package com.github.pires.obd.commands.engine;

import com.github.pires.obd.commands.ObdCommand;
import com.github.pires.obd.enums.AvailableCommandNames;

/**
 * Displays the current engine Oil temperature.
 */
public class EngineOilTempObdCommand extends ObdCommand {

    private int oiltemp = -40;

    /**
     * Default ctor.
     */
    public EngineOilTempObdCommand() {
        super("01 5C");
    }

    /**
     * Copy ctor.
     *
     * @param other a {@link EngineOilTempObdCommand} object.
     */
    public EngineOilTempObdCommand(EngineOilTempObdCommand other) {
        super(other);
    }

    @Override
    protected void performCalculations() {
        // ignore first two bytes [41 0C] of the response((A*256)+B)/4
        int A = buffer.get(2);
        oiltemp = A - 40;
    }

    /**
     * @return the engine oil temp
     */
    @Override
    public String getFormattedResult() {
        return String.format("%d%s", oiltemp, getResultUnit());
    }

    @Override
    public String getCalculatedResult() {
        return String.valueOf(oiltemp);
    }

    @Override
    public String getResultUnit() {
        return "C";
    }

    @Override
    public String getName() {
        return AvailableCommandNames.ENGINE_OIL_TEMP.getValue();
    }

    /**
     * @return a int.
     */
    public int getOilTemp() {
        return oiltemp;
    }

}
