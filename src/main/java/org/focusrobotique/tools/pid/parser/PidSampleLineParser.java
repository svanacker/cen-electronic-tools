package org.focusrobotique.tools.pid.parser;

import org.apache.commons.lang3.StringUtils;
import org.focusrobotique.tools.pid.model.InstructionType;
import org.focusrobotique.tools.pid.model.PidSampleLine;
import org.focusrobotique.tools.pid.model.PidType;

public class PidSampleLineParser {
	
	public static final String SEPARATOR = "|"; 

	public PidSampleLine parse (String sampleLine) {
		PidSampleLine result = new PidSampleLine();
		
		String s = StringUtils.remove(sampleLine, ' ');
		String[] values = StringUtils.split(s, SEPARATOR);
		
		result.setInstructionType(InstructionType.valueOf(values[0]));
		result.setPidTime(Float.valueOf(values[1]));
		result.setPidType(PidType.valueOf(values[2]));
		
		result.setNormalSpeed(Float.valueOf(values[3]));
		result.setRealSpeed(Float.valueOf(values[4]));
		
		result.setNormalPosition(Float.valueOf(values[5]));
		result.setRealPosition(Float.valueOf(values[6]));

		// errors
		result.setProportionalError(Float.valueOf(values[7]));
		result.setIntegralError(Float.valueOf(values[8]));
		result.setDerivativeError(Float.valueOf(values[9]));

		result.setPExE(Float.valueOf(values[10]));
		result.setIExE(Float.valueOf(values[11]));
		result.setDExE(Float.valueOf(values[12]));
		
		result.setNormalU(Float.valueOf(values[13]));
		result.setU(Float.valueOf(values[14]));

		return result;
	}
}
