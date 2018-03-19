package org.focusrobotique.tools.pid.command;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.focusrobotique.common.io.HexPrintWriter;
import org.focusrobotique.tools.pid.model.PidSampleLine;
import org.focusrobotique.tools.pid.model.PidSampleLineList;

/**
 * Generates the different command to append in the file so that we could replay
 */
public class ComputationValuesDataPidCommandGenerator {
	
	private static final int PID_TIME_SECOND_DIGIT_PRECISION = 3;
	private static final int POSITION_DIGIT_MM_PRECISION = 3;
	private static final int SPEED_DIGIT_MM_PRECISION = 0;
	private static final int SPEED_ACCELERATION_MM_PRECISION = 0;
	private static final int PID_VALUE_DIGIT_PRECISION = 1;
	private static final int U_VALUE_DIGIT_PRECISION = 0;

	protected String appendCommand(StringBuilder s, PidSampleLine sampleLine) {
		// Index
		HexPrintWriter.appendHex2(s, (char) sampleLine.getInstructionType().getIndex());
		HexPrintWriter.appendSeparator(s);
		// Pid Time
		HexPrintWriter.appendHexFloat4(s, sampleLine.getPidTime(), PID_TIME_SECOND_DIGIT_PRECISION);
		HexPrintWriter.appendSeparator(s);
		// Acceleration
		HexPrintWriter.appendHexFloat4(s, sampleLine.getNormalAcceleration(), SPEED_ACCELERATION_MM_PRECISION);
		HexPrintWriter.appendSeparator(s);
		HexPrintWriter.appendHexFloat4(s, sampleLine.getRealAcceleration(), SPEED_ACCELERATION_MM_PRECISION);
		HexPrintWriter.appendSeparator(s);
		// Speed
		HexPrintWriter.appendHexFloat4(s, sampleLine.getNormalSpeed(), SPEED_DIGIT_MM_PRECISION);
		HexPrintWriter.appendSeparator(s);
		HexPrintWriter.appendHexFloat4(s, sampleLine.getRealSpeed(), SPEED_DIGIT_MM_PRECISION);
		HexPrintWriter.appendSeparator(s);
		// Position
		HexPrintWriter.appendHexFloat6(s, sampleLine.getNormalPosition(), POSITION_DIGIT_MM_PRECISION);
		HexPrintWriter.appendSeparator(s);
		HexPrintWriter.appendHexFloat6(s, sampleLine.getRealPosition(), POSITION_DIGIT_MM_PRECISION);
		HexPrintWriter.appendSeparator(s);		
        // Errors
		HexPrintWriter.appendHexFloat4(s, sampleLine.getProportionalError(), PID_VALUE_DIGIT_PRECISION);
		HexPrintWriter.appendSeparator(s);		
		HexPrintWriter.appendHexFloat4(s, sampleLine.getIntegralError(), PID_VALUE_DIGIT_PRECISION);
		HexPrintWriter.appendSeparator(s);		
		HexPrintWriter.appendHexFloat4(s, sampleLine.getDerivativeError(), PID_VALUE_DIGIT_PRECISION);
		HexPrintWriter.appendSeparator(s);		
        // Errors with factor
//		HexPrintWriter.appendHexFloat4(s, sampleLine.getPExE(), PID_VALUE_DIGIT_PRECISION);
//		HexPrintWriter.appendSeparator(s);		
//		HexPrintWriter.appendHexFloat4(s, sampleLine.getIExE(), PID_VALUE_DIGIT_PRECISION);
//		HexPrintWriter.appendSeparator(s);		
//		HexPrintWriter.appendHexFloat4(s, sampleLine.getDExE(), PID_VALUE_DIGIT_PRECISION);
//		HexPrintWriter.appendSeparator(s);	
        // U / Normal U
		HexPrintWriter.appendHexFloat4(s, sampleLine.getNormalU(), U_VALUE_DIGIT_PRECISION);
		HexPrintWriter.appendSeparator(s);	
		HexPrintWriter.appendHexFloat4(s, sampleLine.getU(), U_VALUE_DIGIT_PRECISION);

		return s.toString();
	}
	
	public void writeToFile(File file, PidSampleLineList thetaPidSampleLineList, PidSampleLineList alphaPidSampleLineList) throws IOException {
		StringBuilder s = new StringBuilder();
		// THETA
		for (PidSampleLine sampleLine : thetaPidSampleLineList) {
			appendCommand(s, sampleLine);
			s.append("\n");
		}
		// ALPHA
		for (PidSampleLine sampleLine : alphaPidSampleLineList) {
			appendCommand(s, sampleLine);
			s.append("\n");
		}
		FileUtils.write(file, s.toString(), StandardCharsets.US_ASCII);
	}
}
