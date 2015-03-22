package by.iharkaratkou;

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelParser {

	public LinkedHashSet<String> getSheetsNames() {
		Set<String> sheetsNames = new LinkedHashSet();
		// sheetsNames.addAll(Arrays.asList("General Data","Summary of qualifications","Experience","Certification","Education","Visited countries","Country list"));
		sheetsNames.addAll(Arrays.asList("General Data"));
		return (LinkedHashSet<String>) sheetsNames;
	}

	public void parseExcelToDatabase(String filenameTimestamp) {
		try {
			FileInputStream file;
			file = new FileInputStream(new File("d:/eclipse_workspace/upload/"
					+ filenameTimestamp));

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			for (String sheetName : getSheetsNames()) {
				// Get first/desired sheet from the workbook
				XSSFSheet sheet = workbook.getSheet(sheetName);

				// Iterate through each rows one by one
				Iterator<Row> rowIterator = sheet.iterator();
				while (rowIterator.hasNext()) {
					Row row = rowIterator.next();
					// For each row, iterate through all the columns
					Iterator<Cell> cellIterator = row.cellIterator();

					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();

						// Check the cell type and format accordingly
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							System.out.println(cell.getNumericCellValue());
							break;
						case Cell.CELL_TYPE_STRING:
							System.out.println(cell.getStringCellValue());
							break;
						}
					}
					System.out.println("");
				}
				file.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
