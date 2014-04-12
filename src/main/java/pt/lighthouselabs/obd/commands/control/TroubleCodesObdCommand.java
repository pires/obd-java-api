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
package pt.lighthouselabs.obd.commands.control;

import java.io.IOException;
import java.io.InputStream;

import pt.lighthouselabs.obd.commands.ObdCommand;
import pt.lighthouselabs.obd.enums.AvailableCommandNames;

/**
 * It is not needed no know how many DTC are stored. 
 * Because when no DTC are stored response will be NO DATA
 * And where are more messages it will be stored in frames that have 7 bytes.
 * In one frame are stored 3 DTC.   
 * If we find out DTC P0000 that mean no message are we can end.
 * 
 * <p>
 */
public class TroubleCodesObdCommand extends ObdCommand {

	protected final static char[] dtcLetters = { 'P', 'C', 'B', 'U' };
	protected final static char[] hexArray = "0123456789ABCDEF".toCharArray();

	private StringBuffer codes = null;
	private int howManyTroubleCodes = 0;

	
	public TroubleCodesObdCommand() {
		super("03");

		codes = new StringBuffer();
	}
	
	/**
	 * Not needed constructor left for working current view.
	 */
	public TroubleCodesObdCommand(int howManyTroubleCodes) {
		super("03");

		codes = new StringBuffer();
		this.howManyTroubleCodes = howManyTroubleCodes;
	}

	/**
	 * Copy ctor.
	 * 
	 * @param other
	 */
	public TroubleCodesObdCommand(TroubleCodesObdCommand other) {
		super(other);
		codes = new StringBuffer();
	}
	
	@Override
	protected void readResult(InputStream in) throws IOException {
		readRawData(in);
		performCalculations();
	}

	@Override
	protected void performCalculations() {

		String workingData = getResult().replaceAll("[\r\n]", "");	
		
		if(workingData.contains(NODATA)){
			codes.append("No fault codes stored");
			return;
		}
		
		int begin = 0; // start at 2nd byte
		int end = 2; // end at 4th byte

		for (int i = 0; end < workingData.length() ; i++) {
			begin += 2;
			end += 2;
			
			for (int j = 0; j < 3; j++) {
				
				byte b1 = Byte.parseByte(workingData.substring(begin, end));
				
				int ch1 = ((b1 & 0xC0) >> 6);
			    int ch2 = ((b1 & 0x30) >> 4);
			    int ch3 = (b1 & 0x0F);
			    
				begin += 2;
				end += 2;

				// read and jump 2 bytes
				byte b2 = Byte.parseByte(workingData.substring(begin, end));
				
				int ch4 = ((b2 & 0xF0) >> 4);
			    int ch5 = (b2 & 0x0F);
			    
				begin += 2;
				end += 2;
				
				if((ch1 == 0) && (ch2 == 0) && (ch4 == 0) && (ch5 == 0)){
					return;
				}
				
				codes.append(dtcLetters[ch1]);
				codes.append(hexArray[ch2]);
				codes.append(hexArray[ch3]);
				codes.append(hexArray[ch4]);
				codes.append(hexArray[ch5]);
				codes.append("\n");
				
			}
		}
	}

	public String formatResult() {
		return codes.toString();
	}

	@Override
	protected void readRawData(InputStream in) throws IOException {
		byte b = 0;
		StringBuilder res = new StringBuilder();

		// read until '>' arrives
		while ((char) (b = (byte) in.read()) != '>') {
			if ((char) b != ' ') {
				res.append((char) b);
			}
		}

		rawData = res.toString().trim();

	}

	@Override
	public String getFormattedResult() {
		return codes.toString();
	}

	@Override
	public String getName() {
		return AvailableCommandNames.TROUBLE_CODES.getValue();
	}

}