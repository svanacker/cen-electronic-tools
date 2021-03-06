package org.focusrobotique.tools.pid.parser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.focusrobotique.tools.pid.model.InstructionType;
import org.focusrobotique.tools.pid.model.PidSampleLine;
import org.focusrobotique.tools.pid.model.PidSampleLineList;

/**
 * Read a file and filter all lines beginning by some instruction (like THETA / ALPHA)
 */
public class ConsoleParser {
	
	private File file;

	public ConsoleParser(File file) {
		this.file = file;
	}

	public PidSampleLineList parse(InstructionType instructionType) throws IOException {
		List<String> lines = FileUtils.readLines(file, StandardCharsets.US_ASCII);

		PidSampleLineList result = new PidSampleLineList();
		
		PidSampleLineParser parser = new PidSampleLineParser();
		
		String instructionTypeAsString = instructionType.toString();
		
		for (String line : lines) {
			if (line.length() < 7) {
				continue;
			}
			String lineHeader = line.substring(2, 7);
			if (lineHeader.equals(instructionTypeAsString)) {
				PidSampleLine sampleLine = parser.parse(line);
				result.add(sampleLine);
			}
		}
		
		return result;	
	}
}
