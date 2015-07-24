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
package com.github.pires.obd.enums;

/**
 * Names of all available commands.
 */
public enum AvailableCommandNames {

  AIR_INTAKE_TEMP("Air Intake Temperature"),
  AMBIENT_AIR_TEMP("Ambient Air Temperature"),
  ENGINE_COOLANT_TEMP("Engine Coolant Temperature"),
  BAROMETRIC_PRESSURE("Barometric Pressure"),
  FUEL_PRESSURE("Fuel Pressure"),
  INTAKE_MANIFOLD_PRESSURE("Intake Manifold Pressure"),
  ENGINE_LOAD("Engine Load"),
  ENGINE_RUNTIME("Engine Runtime"),
  ENGINE_RPM("Engine RPM"),
  SPEED("Vehicle Speed"),
  MAF("Mass Air Flow"),
  THROTTLE_POS("Throttle Position"),
  TROUBLE_CODES("Trouble Codes"),
  FUEL_LEVEL("Fuel Level"),
  FUEL_TYPE("Fuel Type"),
  FUEL_CONSUMPTION("Fuel Consumption"),
  FUEL_ECONOMY("Fuel Economy"),
  FUEL_ECONOMY_WITH_MAF("Fuel Economy 2"),
  FUEL_ECONOMY_WITHOUT_MAF("Fuel Economy 3"),
  TIMING_ADVANCE("Timing Advance"),
  DTC_NUMBER("Diagnostic Trouble Codes"),
  EQUIV_RATIO("Command Equivalence Ratio"),
  DISTANCE_TRAVELED_AFTER_CODES_CLEARED("Distance since codes cleared"),
  CONTROL_MODULE_VOLTAGE("Control Module Power Supply "),
  ENGINE_FUEL_RATE("Engine Fuel Rate"),
  FUEL_RAIL_PRESSURE("Fuel Rail Pressure"),
  VIN("Vehicle Identification Number (VIN)"),
  DISTANCE_TRAVELED_MIL_ON("Distance traveled with MIL on"),
  TIME_TRAVELED_MIL_ON("Time run with MIL on"),
  TIME_SINCE_TC_CLEARED("Time since trouble codes cleared"),
  REL_THROTTLE_POS("Relative throttle position"),
  PIDS("Available PIDs"),
  ABS_LOAD("Absolute load"),
  ENGINE_OIL_TEMP("Engine oil temperature"),
  FUEL_AIR_COMMANDED("Air/Fuel Ratio"),
  FUEL_AIR_WIDE_COMMANDED("Air/Fuel Wide Ratio");

  private final String value;

  /**
   * @param value
   */
  private AvailableCommandNames(String value) {
    this.value = value;
  }

  /**
   * @return a {@link String} object.
   */
  public final String getValue() {
    return value;
  }

}
