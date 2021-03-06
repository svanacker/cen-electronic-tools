package org.focusrobotique.tools.pid.parser;

import java.io.File;
import java.io.IOException;

import org.focusrobotique.tools.pid.model.InstructionType;
import org.focusrobotique.tools.pid.model.PidSampleLineList;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test for {@link ConsoleParser}.
 */
public class ConsoleParserTest {

	@Test
	public void parse1() throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("console1.txt").getFile());

		ConsoleParser parser = new ConsoleParser(file);

		PidSampleLineList thetaSampleLines = parser.parse(InstructionType.THETA);
		Assert.assertEquals(8, thetaSampleLines.size());

		PidSampleLineList alphaSampleLines = parser.parse(InstructionType.ALPHA);
		Assert.assertEquals(6, alphaSampleLines.size());
	}
}
