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
package com.github.pires.obd.commands.protocol;

/**
 * By default, Adaptive Timing option 1 (AT1) is enabled, and is the recommended setting.
 * AT0 is used to disable Adaptive timing (so the timeout is always as set by AT ST),
 * while AT2 is a more aggressive version of AT1 (the effect is more noticeable for very
 * slow connections – you may not see much difference with faster OBD systems.
 *
 */
public class AdaptiveTimingCommand extends ObdProtocolCommand {

    /**
     * <p>Constructor for AdaptiveTimingCommand.</p>
     *
     * @param mode a int.
     */
    public AdaptiveTimingCommand(int mode) {
        super("AT AT" + mode);
    }

    /**
     * <p>Constructor for AdaptiveTimingCommand.</p>
     *
     * @param other a {@link com.github.pires.obd.commands.protocol.AdaptiveTimingCommand} object.
     */
    public AdaptiveTimingCommand(AdaptiveTimingCommand other) {
        super(other);
    }

    /** {@inheritDoc} */
    @Override
    public String getFormattedResult() {
        return getResult();
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return "Adaptive timing set";
    }

}
