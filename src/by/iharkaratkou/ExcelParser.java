package by.iharkaratkou;

import java.io.*;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import by.iharkaratkou.bo.GeneralData;
import by.iharkaratkou.dto.DBUtils;

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

				if (sheet != null) {
					parseExcelSheetToDatabase(sheet, sheetName);
					// Iterate through each rows one by one
					/*
					 * Iterator<Row> rowIterator = sheet.iterator(); while
					 * (rowIterator.hasNext()) { Row row = rowIterator.next();
					 * // For each row, iterate through all the columns
					 * Iterator<Cell> cellIterator = row.cellIterator();
					 * 
					 * while (cellIterator.hasNext()) { Cell cell =
					 * cellIterator.next();
					 * 
					 * // Check the cell type and format accordingly switch
					 * (cell.getCellType()) { case Cell.CELL_TYPE_NUMERIC:
					 * System.out.println(cell.getNumericCellValue()); break;
					 * case Cell.CELL_TYPE_STRING:
					 * System.out.println(cell.getStringCellValue()); break; } }
					 * System.out.println(""); }
					 */
				}
				file.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void parseExcelSheetToDatabase(XSSFSheet sheet, String sheetName) {
		switch (sheetName) {
		case "General Data":
			parseExcelSheetGenDataToDatabase(sheet);
			break;
		case "Summary of qualifications":
			parseExcelSheetSumOfQualToDatabase(sheet);
			break;
		case "Experience":
			parseExcelSheetExpToDatabase(sheet);
			break;
		case "Certification":
			parseExcelSheetCertToDatabase(sheet);
			break;
		case "Education":
			parseExcelSheetEducToDatabase(sheet);
			break;
		case "Visited countries":
			parseExcelSheetVisCountToDatabase(sheet);
			break;
		}
		return;
	}

	public void parseExcelSheetGenDataToDatabase(XSSFSheet sheet) {
		boolean isName = false;
		boolean isSurname = false;
		boolean isCurPosition = false;
		boolean isCurCompany = false;
		boolean isCurLocation = false;
		boolean isCurBusPhone = false;
		boolean isCurBusMail = false;
		boolean isLinkedIn = false;
		boolean isTwitter = false;
		
		String Name = "";
		String Surname = "";
		String CurPosition = "";
		String CurCompany = "";
		String CurLocation = "";
		String CurBusPhone = "";
		String CurBusMail = "";
		String LinkedIn = "";
		String Twitter = "";
		
		
		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			// For each row, iterate through all the columns
			Iterator<Cell> cellIterator = row.cellIterator();

			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				if (isName) {
					Name = cell.getStringCellValue();
					isName = false;
				}else if (isSurname){
					Surname = cell.getStringCellValue();
					isSurname = false;
				}else if (isCurPosition){
					CurPosition = cell.getStringCellValue();
					isCurPosition = false;
				}else if (isCurCompany){
					CurCompany = cell.getStringCellValue();
					isCurCompany = false;
				}else if (isCurLocation){
					CurLocation = cell.getStringCellValue();
					isCurLocation = false;
				}else if (isCurBusPhone){
					CurBusPhone = cell.getStringCellValue();
					isCurBusPhone = false;
				}else if (isCurBusMail){
					CurBusMail = cell.getStringCellValue();
					isCurBusMail = false;
				}else if (isLinkedIn){
					LinkedIn = cell.getStringCellValue();
					isLinkedIn = false;
				}else if (isTwitter){
					Twitter = cell.getStringCellValue();
					isTwitter = false;
				}
				
				
				switch (cell.getStringCellValue()) {
				case "Name:": isName = true; break;
				case "Surname:": isSurname = true;break;
				case "Position:": isCurPosition = true;break;
				case "Company:": isCurCompany = true;break;
				case "Location:": isCurLocation = true;break;
				case "Business phone:": isCurBusPhone = true;break;
				case "Business e-mail:": isCurBusMail = true;break;
				case "LinkedIn:": isLinkedIn = true;break;
				case "Twitter:": isTwitter = true;break;
				}	
			}
		}
		
		GeneralData gd = new GeneralData();
		gd.setNAME(Name);
		gd.setSURNAME(Surname);
		gd.setCURRENT_POST(CurPosition);
		gd.setCURRENT_COMPANY(CurCompany);
		gd.setCURRENT_LOCATION(CurLocation);
		gd.setCURRENT_BUS_PHONE(CurBusPhone);
		gd.setCURRENT_BUSINESS_MAIL(CurBusMail);
		gd.setSN_LINKEDIN(LinkedIn);
		gd.setSN_TWITTER(Twitter);

		DBUtils dbu = new DBUtils();
		Integer gdID = 0;
		try {
			gdID = dbu.insertGeneralData(gd);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		System.out.println(gdID);
		
		return;
	}

	public void parseExcelSheetSumOfQualToDatabase(XSSFSheet sheet) {
		return;
	}

	public void parseExcelSheetExpToDatabase(XSSFSheet sheet) {
		return;
	}

	public void parseExcelSheetCertToDatabase(XSSFSheet sheet) {
		return;
	}

	public void parseExcelSheetEducToDatabase(XSSFSheet sheet) {
		return;
	}

	public void parseExcelSheetVisCountToDatabase(XSSFSheet sheet) {
		return;
	}

}