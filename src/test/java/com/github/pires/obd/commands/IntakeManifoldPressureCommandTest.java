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

import com.github.pires.obd.commands.pressure.IntakeManifoldPressureCommand;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.powermock.api.easymock.PowerMock.*;
import static org.testng.Assert.assertEquals;

/**
 * Tests for PressureCommand sub-classes.
 */
@PrepareForTest(InputStream.class)
public class IntakeManifoldPressureCommandTest {

	private IntakeManifoldPressureCommand command;
	private InputStream mockIn;

	/**
	 * @throws Exception
	 */
	@BeforeMethod
	public void setUp() throws Exception {
		command = new IntakeManifoldPressureCommand();
	}

	/**
	 * Test for valid InputStream read, 100kPa
	 *
	 * @throws IOException
	 */
	@Test
	public void testValidPressureMetric() throws IOException {
		// mock InputStream read
		mockIn = createMock(InputStream.class);
		mockIn.read();
		expectLastCall().andReturn((byte) '4');
		expectLastCall().andReturn((byte) '1');
		expectLastCall().andReturn((byte) ' ');
		expectLastCall().andReturn((byte) '0');
		expectLastCall().andReturn((byte) 'B');
		expectLastCall().andReturn((byte) ' ');
		expectLastCall().andReturn((byte) '6');
		expectLastCall().andReturn((byte) '4');
		expectLastCall().andReturn((byte) '>');

		replayAll();

		// call the method to test
		command.readResult(mockIn);
		command.useImperialUnits(false);
		assertEquals(command.getMetricUnit(), 100);

		verifyAll();
	}

	/**
	 * Test for valid InputStream read, 14.50psi
	 *
	 * @throws IOException
	 */
	@Test
	public void testValidPressureImperial() throws IOException {
		// mock InputStream read
		mockIn = createMock(InputStream.class);
		mockIn.read();
		expectLastCall().andReturn((byte) '4');
		expectLastCall().andReturn((byte) '1');
		expectLastCall().andReturn((byte) ' ');
		expectLastCall().andReturn((byte) '0');
		expectLastCall().andReturn((byte) 'B');
		expectLastCall().andReturn((byte) ' ');
		expectLastCall().andReturn((byte) '6');
		expectLastCall().andReturn((byte) '4');
		expectLastCall().andReturn((byte) '>');

		replayAll();

		// call the method to test
		command.readResult(mockIn);
		command.useImperialUnits(true);
		assertEquals(command.getImperialUnit(), 14.503774f);

		verifyAll();
	}

	/**
	 * Clear resources.
	 */
	@AfterClass
	public void tearDown() {
		command = null;
		mockIn = null;
	}

}
