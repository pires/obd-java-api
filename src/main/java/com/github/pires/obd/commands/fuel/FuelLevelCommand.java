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
package com.github.pires.obd.commands.fuel;

import com.github.pires.obd.commands.PercentageObdCommand;
import com.github.pires.obd.enums.AvailableCommandNames;

/**
 * Get fuel level in percentage
 *
 */
public class FuelLevelCommand extends PercentageObdCommand {

	/**
	 * <p>
	 * Constructor for FuelLevelCommand.
	 * </p>
	 */
	public FuelLevelCommand() {
		super("01 2F");
	}

	/** {@inheritDoc} */
	@Override
	protected void performCalculations() {
		// ignore first two bytes [hh hh] of the response
		percentage = 100.0f * buffer.get(2) / 255.0f;
	}

	/** {@inheritDoc} */
	@Override
	public String getName() {
		return AvailableCommandNames.FUEL_LEVEL.getValue();
	}

	/**
	 * <p>
	 * getFuelLevel.
	 * </p>
	 *
	 * @return a float.
	 */
	public float getFuelLevel() {
		return percentage;
	}

}
