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
package com.github.pires.obd.commands.control;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import java.util.TreeSet;

import com.github.pires.obd.commands.ObdCommand;
import com.github.pires.obd.enums.AvailableCommandNames;

/**
 * It is not needed no know how many DTC are stored. Because when no DTC are
 * stored response will be NO DATA And where are more messages it will be stored
 * in frames that have 7 bytes. In one frame are stored 3 DTC. If we find out
 * DTC P0000 that mean no message are we can end.
 *
 */
public class TroubleCodesCommand extends ObdCommand {

	/** Constant <code>dtcLetters={'P', 'C', 'B', 'U'}</code> */
	protected final static char[] dtcLetters = { 'P', 'C', 'B', 'U' };
	/** Constant <code>hexArray="0123456789ABCDEF".toCharArray()</code> */
	protected final static char[] hexArray = "0123456789ABCDEF".toCharArray();

	protected Set<String> troubleCodes = null;

	/**
	 * <p>
	 * Constructor for TroubleCodesCommand.
	 * </p>
	 */
	public TroubleCodesCommand() {
		super("03");
		troubleCodes = new TreeSet<>();
	}

	/**
	 * Copy Constructor.
	 *
	 * @param other
	 *            a
	 *            {@link com.github.pires.obd.commands.control.TroubleCodesCommand}
	 *            object.
	 */
	public TroubleCodesCommand(TroubleCodesCommand other) {
		super(other);
		troubleCodes = new TreeSet<>();
	}

	/** {@inheritDoc} */
	@Override
	protected void fillBuffer() {
	}

	/** {@inheritDoc} */
	@Override
	protected void performCalculations() {
		final String result = getResult();
		String workingData;
		int startIndex = 0;  // Header size.

		String canOneFrame = result.replaceAll("[\r\n]", "");
		int canOneFrameLength = canOneFrame.length();
		
		// CAN(ISO-15765) protocol one frame.
		if (canOneFrameLength <= 16 && canOneFrameLength % 4 == 0) {
			workingData = canOneFrame;  // 43yy{codes}
			startIndex = 4;  // Header is 43yy, yy showing the number of data items.
		// CAN(ISO-15765) protocol two and more frames.
		} else if (result.contains(":")) {
			workingData = result.replaceAll("[\r\n].:", "");  // xxx43yy{codes}
			startIndex = 7;  // Header is xxx43yy, xxx is bytes of information to follow, yy showing the
							 // number of data items.
		// ISO9141-2, KWP2000 Fast and KWP2000 5Kbps (ISO15031) protocols.
		} else {
			workingData = result.replaceAll("^43|[\r\n]43|[\r\n]", "");
		}
		for (int begin = startIndex; begin < workingData.length(); begin += 4) {
			String dtc = "";
			byte b1 = hexStringToByteArray(workingData.charAt(begin));
			int ch1 = ((b1 & 0xC0) >> 6);
			int ch2 = ((b1 & 0x30) >> 4);
			dtc += Character.toString(dtcLetters[ch1]);
			dtc += Character.toString(hexArray[ch2]);
			dtc += workingData.substring(begin + 1, begin + 4);
			if ("P0000".equals(dtc)) {
				return;
			}
			troubleCodes.add(dtc);
		}
	}

	private byte hexStringToByteArray(char s) {
		return (byte) ((Character.digit(s, 16) << 4));
	}

	/**
	 * <p>
	 * formatResult.
	 * </p>
	 *
	 * @return the formatted result of this command in string representation.
	 * @deprecated use #getCalculatedResult instead
	 */
	@Deprecated
	public String formatResult() {
		return getCalculatedResult();
	}

	/** {@inheritDoc} */
	@Override
	public String getCalculatedResult() {
		StringBuilder sb = new StringBuilder();
		for (String code : troubleCodes) {
			sb.append(code).append("\n");
		}
		return sb.toString();
	}

	/** {@inheritDoc} */
	@Override
	protected void readRawData(InputStream in) throws IOException {
		byte b;
		StringBuilder res = new StringBuilder();

		// read until '>' arrives OR end of stream reached (and skip ' ')
		char c;
		while (true) {
			b = (byte) in.read();
			c = (char) b;

			// -1 if the end of the stream is reached
			// or read until '>' arrives
			if (b == -1 || c == '>') {
				break;
			}

			// skip ' '
			if (c != ' ') {
				res.append(c);
			}
		}

		rawData = res.toString().trim();
	}

	/** {@inheritDoc} */
	@Override
	public String getFormattedResult() {
		return getCalculatedResult();
	}

	/** {@inheritDoc} */
	@Override
	public String getName() {
		return AvailableCommandNames.TROUBLE_CODES.getValue();
	}

}
