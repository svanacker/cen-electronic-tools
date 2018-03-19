package org.focusrobotique.tools.pid.main;

import java.io.File;
import java.time.LocalDateTime;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.focusrobotique.tools.pid.command.ComputationValuesDataPidCommandGenerator;
import org.focusrobotique.tools.pid.excel.ExcelPidFiller;
import org.focusrobotique.tools.pid.model.InstructionType;
import org.focusrobotique.tools.pid.model.PidSampleLineList;
import org.focusrobotique.tools.pid.parser.ConsoleParser;
import org.focusrobotique.tools.pid.utils.DateUtils;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

/**
 * Class which analysis a console
 */
public class ExcelPidMain {

	@Parameter(names = "--help", help = true)
	private boolean help;

	// BASE DIRECTORY for all (SOURCE / MODEL / TARGET)
	@Parameter(names = { "-b", "--base-dir" }, description = "Base Directory for Source / Model / Target")
	private String baseDirectory = "C:/dev/git/svanacker/cen-electronic-data/";

	// SOURCE
	@Parameter(required = true, names = { "-c",
			"--source-filename" }, description = "Relative Path to Base Directory for the Console File")
	private String sourceConsoleFileName = "rawData/console.txt";

	// MODEL
	@Parameter(names = { "-m",
			"--model-filename" }, description = "Relative Path to Base Directory for the Excel Files for Alpha and Theta")
	private String excelModelFileName = "model/pidModel.xlsx";

	// TARGET
	@Parameter(names = { "-t", "--target-dir" }, description = "Target directory for Console & Result Files")
	private String targetDirectory = "motorBoard";

	/**
	 * Create the target directory with the date included.
	 * 
	 * @return
	 */
	private File createDatedTargetDirectory(File baseDirectoryFile, String dateAsString) {
		File targetDirectoryFile = new File(baseDirectoryFile, targetDirectory);
		targetDirectoryFile.mkdirs();
		File result = new File(targetDirectoryFile, dateAsString);
		result.mkdirs();
		return result;
	}

	public void run() throws Exception {
		File baseDirectoryFile = new File(baseDirectory);
		if (!baseDirectoryFile.exists()) {
			System.out.println("Base Directory does not exists : " + baseDirectory);
			return;
		}
		String dateAsString = DateUtils.dateToString(LocalDateTime.now());

		// Create the target directory
		File targetDirectoryWithDate = createDatedTargetDirectory(baseDirectoryFile, dateAsString);

		// Console file
		File sourceConsoleFile = new File(baseDirectory, sourceConsoleFileName);
		String sourceConsoleFileEndName = sourceConsoleFile.getName();
		String sourceConsoleFileNameWithoutException = FilenameUtils.removeExtension(sourceConsoleFileEndName);
		String sourceConsoleExtension = FilenameUtils.getExtension(sourceConsoleFileName);
		File targetConsoleFile = new File(targetDirectoryWithDate,
				sourceConsoleFileNameWithoutException + dateAsString + "." + sourceConsoleExtension);
		// Copy the console.txt or equivalent to the target file
		FileUtils.copyFile(sourceConsoleFile, targetConsoleFile);

		// Model File
		File excelModelSourceFile = new File(baseDirectory, excelModelFileName);

		ConsoleParser consoleParser = new ConsoleParser(sourceConsoleFile);

		// Create Excel Result File
		for (InstructionType instructionType : InstructionType.values()) {
			PidSampleLineList lines = consoleParser.parse(instructionType);
			// Create a new result File
			String targetFileName = sourceConsoleFileNameWithoutException + "_"
					+ dateAsString + "_" + instructionType.toString() + ".xlsx";
			File excelTargetFile = new File(targetDirectoryWithDate, targetFileName);

			ExcelPidFiller filler = new ExcelPidFiller();
			filler.fill(excelModelSourceFile, lines, excelTargetFile);
		}
		
		// Create Command Result File
		String commandTargetFileName = sourceConsoleFileNameWithoutException + "_"
				+ dateAsString + "_command.txt";
		File commandTargetFile = new File(targetDirectoryWithDate, commandTargetFileName);
		
		ComputationValuesDataPidCommandGenerator commandGenerator = new  ComputationValuesDataPidCommandGenerator();
		PidSampleLineList thetaLines = consoleParser.parse(InstructionType.THETA);
		PidSampleLineList alphaLines = consoleParser.parse(InstructionType.ALPHA);

		commandGenerator.writeToFile(commandTargetFile, thetaLines, alphaLines);
	}

	/**
	 * Main Execution Point
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		ExcelPidMain importer = new ExcelPidMain();
		// Parse the options
		JCommander commander = JCommander.newBuilder().addObject(importer).build();
		commander.parse(args);

		if (args.length == 0) {
			commander.usage();
			return;
		}

		importer.run();
	}
}
