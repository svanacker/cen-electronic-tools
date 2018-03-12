package org.focusrobotique.tools.pid.parser;

import org.focusrobotique.tools.pid.model.InstructionType;
import org.focusrobotique.tools.pid.model.PidSampleLine;
import org.focusrobotique.tools.pid.model.PidType;
import org.junit.Assert;
import org.junit.Test;

public class PidSampleLineParserTest {

	@Test
	public void parseSimplePidSampleLine() {
		PidSampleLineParser parser = new PidSampleLineParser();
		String lineToParse = "| THETA | 0.173 | GO    | 155.5  | 106.7    | 13.49   | 6.230    | 7.266 | 55.31 | 0.467 | 21.79 | 0.123 | 11.96 | 28.51  | 62.27 |";
		PidSampleLine pidSampleLine = parser.parse(lineToParse);
		
		float delta = 0.0000001f;
		Assert.assertEquals(InstructionType.THETA, pidSampleLine.getInstructionType());
		Assert.assertEquals(0.173f, pidSampleLine.getPidTime(), delta);
		Assert.assertEquals(PidType.GO, pidSampleLine.getPidType());
		// Speed
		Assert.assertEquals(155.5f, pidSampleLine.getNormalSpeed(), delta);
		Assert.assertEquals(106.7f, pidSampleLine.getRealSpeed(), delta);
		
		// Position
		Assert.assertEquals(13.49f, pidSampleLine.getNormalPosition(), delta);
		Assert.assertEquals(6.230f, pidSampleLine.getRealPosition(), delta);
		
		// Errors
		Assert.assertEquals(7.266f, pidSampleLine.getProportionalError(), delta);
		Assert.assertEquals(55.31f, pidSampleLine.getIntegralError(), delta);
		Assert.assertEquals(0.467f, pidSampleLine.getDerivativeError(), delta);
		
		// PID
		Assert.assertEquals(21.79f, pidSampleLine.getPExE(), delta);
		Assert.assertEquals(0.123f, pidSampleLine.getIExE(), delta);
		Assert.assertEquals(11.96f, pidSampleLine.getDExE(), delta);

		// U
		Assert.assertEquals(28.51f, pidSampleLine.getNormalU(), delta);
		Assert.assertEquals(62.27f, pidSampleLine.getU(), delta);
	}
}
