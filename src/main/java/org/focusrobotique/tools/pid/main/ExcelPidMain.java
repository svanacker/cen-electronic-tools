package org.focusrobotique.tools.pid.main;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.focusrobotique.tools.pid.excel.ExcelPidFiller;
import org.focusrobotique.tools.pid.model.InstructionType;
import org.focusrobotique.tools.pid.model.PidSampleLine;
import org.focusrobotique.tools.pid.parser.ConsoleParser;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

/**
 * Class which analysis a console
 */
public class ExcelPidMain {

	@Parameter(names = "--help", help = true)
	private boolean help;

	// SOURCE
	@Parameter(names = { "-s",
			"--source-dir" }, description = "Directory where we would like to read some console files")
	private String sourceDirectory = "d:/dropbox/robotique/";

	@Parameter(required=true, names = { "-c",
			"--source-console-filename" }, description = "Simple name of file to analyse and to import in Excel File")
	private String sourceConsoleFileName;

	// MODEL
	@Parameter(names = { "-m",
			"--excel-model-filename" }, description = "Name of base Excel file which will be copied into 2 Excel Files for Alpha and Theta")
	private String excelModelFileName = "pidModel.xlsx";

	// TARGET
	@Parameter(names = { "-t", "--target-dir" }, description = "Target directory where we will create some new files")
	private String targetDirectory = "d:/dropbox/robotique/target/";

	public void run() throws Exception {
		File consoleSourceFile = new File(sourceDirectory, sourceConsoleFileName);
		File excelModelSourceFile = new File(sourceDirectory, excelModelFileName);
		
		ConsoleParser consoleParser = new ConsoleParser(consoleSourceFile);
		
		for (InstructionType instructionType : InstructionType.values()) {
			List<PidSampleLine> lines = consoleParser.parse(instructionType);
			
			String sourceConsoleFileNameWithoutException = FilenameUtils.removeExtension(sourceConsoleFileName);
			// Create a new File
			File excelTargetFile = new File(targetDirectory, sourceConsoleFileNameWithoutException + "_" + instructionType.toString() + ".xlsx");
			
			ExcelPidFiller filler = new ExcelPidFiller();
			filler.fill(excelModelSourceFile, lines, excelTargetFile);
		}
	}

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
