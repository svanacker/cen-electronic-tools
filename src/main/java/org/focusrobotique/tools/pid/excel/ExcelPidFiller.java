package org.focusrobotique.tools.pid.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.focusrobotique.tools.pid.model.PidSampleLine;

public class ExcelPidFiller {

	public ExcelPidFiller() {

	}

	public void fill(File excelModelFile, List<PidSampleLine> lines, File excelTargetFile) throws Exception {
		XSSFWorkbook wb = new XSSFWorkbook(excelModelFile);
		XSSFSheet sheet = wb.getSheet("Data");
		int rowIndex = 1;
		for (PidSampleLine line : lines) {
			XSSFRow row = sheet.getRow(rowIndex);
			if (row == null) {
				row = sheet.createRow(rowIndex);
			}
			int col = 0;
			getOrCreateCell(row, col++).setCellValue(line.getInstructionType().toString());
			getOrCreateCell(row, col++).setCellValue(line.getPidTime());
			getOrCreateCell(row, col++).setCellValue(line.getPidType().toString());
			
			getOrCreateCell(row, col++).setCellValue(line.getNormalSpeed());
			getOrCreateCell(row, col++).setCellValue(line.getRealSpeed());

			getOrCreateCell(row, col++).setCellValue(line.getNormalPosition());
			getOrCreateCell(row, col++).setCellValue(line.getRealPosition());
			
			getOrCreateCell(row, col++).setCellValue(line.getProportionalError());
			getOrCreateCell(row, col++).setCellValue(line.getIntegralError());
			getOrCreateCell(row, col++).setCellValue(line.getDerivativeError());
			
			getOrCreateCell(row, col++).setCellValue(line.getPExE());
			getOrCreateCell(row, col++).setCellValue(line.getIExE());
			getOrCreateCell(row, col++).setCellValue(line.getDExE());
			
			getOrCreateCell(row, col++).setCellValue(line.getNormalU());
			getOrCreateCell(row, col++).setCellValue(line.getU());
			rowIndex++;
		}
		FileOutputStream fileOut = new FileOutputStream(excelTargetFile);
		wb.write(fileOut);
		fileOut.close();
	}

	private Cell getOrCreateCell(XSSFRow row, int col) {
		Cell result = row.getCell(col);
		if (result == null) {
			result = row.createCell(col);
		}
		return result;
	}
}
