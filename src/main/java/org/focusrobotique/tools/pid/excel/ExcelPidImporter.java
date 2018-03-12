package org.focusrobotique.tools.pid.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

/**
 * Class which analysis a console
 */
public class ExcelPidImporter {

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
		File excelModelSourceFile = new File(sourceDirectory + excelModelFileName);
		File excelThetaTargetFile = new File(targetDirectory + excelModelFileName);

		Files.copy(excelModelSourceFile.toPath(), excelThetaTargetFile.toPath());
	}

	public static void main(String[] args) throws Exception {
		ExcelPidImporter importer = new ExcelPidImporter();
		// Parse the options
		JCommander commander = JCommander.newBuilder().addObject(importer).build();
		commander.parse(args);
		
		if (args.length == 0) {
			commander.usage();
			return;
		}
		
		importer.run();

		if (true) {
			return;
		}
//
//		try {
//
//			FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
//			Workbook workbook = new XSSFWorkbook(excelFile);
//			Sheet datatypeSheet = workbook.getSheetAt(0);
//			Iterator<Row> iterator = datatypeSheet.iterator();
//
//			while (iterator.hasNext()) {
//
//				Row currentRow = iterator.next();
//				Iterator<Cell> cellIterator = currentRow.iterator();
//
//				while (cellIterator.hasNext()) {
//
//					Cell currentCell = cellIterator.next();
//					// getCellTypeEnum shown as deprecated for version 3.15
//					// getCellTypeEnum ill be renamed to getCellType starting from version 4.0
//					if (currentCell.getCellTypeEnum() == CellType.STRING) {
//						System.out.print(currentCell.getStringCellValue() + "--");
//					} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
//						System.out.print(currentCell.getNumericCellValue() + "--");
//					}
//
//				}
//				System.out.println();
//
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

	}
}
