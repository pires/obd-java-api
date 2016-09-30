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
 * This class allows for an unspecified command to be sent.
 */
public class ObdRawCommand extends ObdProtocolCommand {

    /**
     * <p>Constructor for ObdRawCommand.</p>
     *
     * @param command a {@link java.lang.String} object.
     */
    public ObdRawCommand(String command) {
        super(command);
    }

    /** {@inheritDoc} */
    @Override
    public String getFormattedResult() {
        return getResult();
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return "Custom command " + getCommandPID();
    }

}
