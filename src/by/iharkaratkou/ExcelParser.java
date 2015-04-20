package by.iharkaratkou;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import by.iharkaratkou.bo.Certification;
import by.iharkaratkou.bo.Country;
import by.iharkaratkou.bo.Education;
import by.iharkaratkou.bo.Exp_activity;
import by.iharkaratkou.bo.Experience;
import by.iharkaratkou.bo.GeneralData;
import by.iharkaratkou.bo.Qualifications;
import by.iharkaratkou.dto.DBUtils;
import by.iharkaratkou.javaUtils.JavaHelpUtils;

/**
 * This class contains methods which parse data on Excel sheets and call methods to write info
 * in database.
 * 
 * @author Ihar Karatkou
 * @version 1.0
 * @since 2015-04-20
 */
public class ExcelParser {
	
	final static Logger logger = Logger.getLogger(ExcelParser.class);
	
	private String getExcelValue(Cell cell){
		String cellValue = "";
		switch (cell.getCellType())
        {
            case Cell.CELL_TYPE_NUMERIC:
                cellValue = String.valueOf(cell.getNumericCellValue());
            	//System.out.print(cell.getNumericCellValue() + "t");
                break;
            case Cell.CELL_TYPE_STRING:
            	cellValue = cell.getStringCellValue();
                //System.out.print(getExcelValue(cell) + "t");
                break;
            case Cell.CELL_TYPE_FORMULA:
                cellValue = cell.getStringCellValue();
            	//System.out.print(cell.getNumericCellValue() + "t");
                break;    
        }
		return cellValue;
	}
	
	public LinkedHashSet<String> getSheetsNames() {
		Set<String> sheetsNames = new LinkedHashSet();
		// sheetsNames.addAll(Arrays.asList("General Data","Summary of qualifications","Experience","Certification","Education","Visited countries","Country list"));
		sheetsNames.addAll(Arrays.asList("General Data",
				"Summary of qualifications", "Experience","Certification","Education","Visited countries"));
		return (LinkedHashSet<String>) sheetsNames;
	}

	public Integer parseExcelToDatabase(String webID, String filenameTimestamp, String webPass, boolean newWebResume, String saveFile) {
		Integer id_last = 0;
		Integer id_last_temp = 0;
		//Check insert of update
		if (!webID.equals("")) id_last_temp = Integer.valueOf(webID);
		
		try {
			FileInputStream file;
			file = new FileInputStream(new File(saveFile + filenameTimestamp));

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			for (String sheetName : getSheetsNames()) {
				// Get first/desired sheet from the workbook
				XSSFSheet sheet = workbook.getSheet(sheetName);

				if (sheet != null) {
					id_last = parseExcelSheetToDatabase(sheet, sheetName,
							id_last_temp, webPass);
					if (id_last != 0) {
						id_last_temp = id_last;
					}
				}
				file.close();
			}
			logger.debug("id_last_temp inside: " + id_last_temp);
		} catch (Exception e) {
			logger.error(e);
		}
		
		return id_last_temp;
	}

	public Integer parseExcelSheetToDatabase(XSSFSheet sheet, String sheetName,
			Integer id_last_temp,String webPass) {

		Integer id_last = 0;

		switch (sheetName) {
		case "General Data":
			id_last = parseExcelSheetGenDataToDatabase(sheet, id_last_temp, webPass);
			break;
		case "Summary of qualifications":
			parseExcelSheetSumOfQualToDatabase(sheet, id_last_temp);
			break;
		case "Experience":
			parseExcelSheetExpToDatabase(sheet, id_last_temp);
			break;
		case "Certification":
			parseExcelSheetCertToDatabase(sheet, id_last_temp);
			break;
		case "Education":
			parseExcelSheetEducToDatabase(sheet, id_last_temp);
			break;
		case "Visited countries":
			parseExcelSheetVisCountToDatabase(sheet, id_last_temp);
			break;
		}
		return id_last;
	}

	public Integer parseExcelSheetGenDataToDatabase(XSSFSheet sheet, Integer id_last_temp, String webPass) {
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
					Name = getExcelValue(cell);
					isName = false;
				} else if (isSurname) {
					Surname = getExcelValue(cell);
					isSurname = false;
				} else if (isCurPosition) {
					CurPosition = getExcelValue(cell);
					isCurPosition = false;
				} else if (isCurCompany) {
					CurCompany = getExcelValue(cell);
					isCurCompany = false;
				} else if (isCurLocation) {
					CurLocation = getExcelValue(cell);
					isCurLocation = false;
				} else if (isCurBusPhone) {
					CurBusPhone = getExcelValue(cell);
					isCurBusPhone = false;
				} else if (isCurBusMail) {
					CurBusMail = getExcelValue(cell);
					isCurBusMail = false;
				} else if (isLinkedIn) {
					LinkedIn = getExcelValue(cell);
					isLinkedIn = false;
				} else if (isTwitter) {
					Twitter = getExcelValue(cell);
					isTwitter = false;
				}

				switch (getExcelValue(cell)) {
				case "Name:":
					isName = true;
					break;
				case "Surname:":
					isSurname = true;
					break;
				case "Position:":
					isCurPosition = true;
					break;
				case "Company:":
					isCurCompany = true;
					break;
				case "Location:":
					isCurLocation = true;
					break;
				case "Business phone:":
					isCurBusPhone = true;
					break;
				case "Business e-mail:":
					isCurBusMail = true;
					break;
				case "LinkedIn:":
					isLinkedIn = true;
					break;
				case "Twitter:":
					isTwitter = true;
					break;
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
		gd.setPASSWORD(webPass);

		DBUtils dbu = new DBUtils();
		Integer gdID = 0;
		try {
			gdID = dbu.insertGeneralData(gd,id_last_temp);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		logger.debug("gdID: " + gdID);

		return gdID;
	}

	public void parseExcelSheetSumOfQualToDatabase(XSSFSheet sheet,
			Integer id_last_temp) {
		ArrayList<String> qualifications = new ArrayList<String>();
		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			// For each row, iterate through all the columns
			Iterator<Cell> cellIterator = row.cellIterator();

			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				String cellValue = getExcelValue(cell);
				if (!cellValue.equals("Summary of qualifications:")
						&& !cellValue.isEmpty()) {
					qualifications.add(cellValue);
				}
			}
		}

		Qualifications qual = new Qualifications();
		qual.setQualifications(qualifications);

		DBUtils dbu = new DBUtils();
		try {
			dbu.insertQualifications(qual, id_last_temp);
		} catch (ClassNotFoundException | SQLException e) {
			logger.error(e);
		}

		return;
	}

	public void parseExcelSheetExpToDatabase(XSSFSheet sheet,
			Integer id_last_temp) {
		ArrayList<Integer> id_exp = new ArrayList<Integer>();
		id_exp = parseExcelSheetExpMainToDatabase(sheet, id_last_temp);
		logger.debug("id_exp: " + id_exp);
		parseExcelSheetExpActToDatabase(sheet, id_exp);
		return;
	}

	public ArrayList<Integer> parseExcelSheetExpMainToDatabase(XSSFSheet sheet,
			Integer id_last_temp) {
		ArrayList<String> Positions = new ArrayList<String>();
		ArrayList<String> Companies = new ArrayList<String>();
		ArrayList<String> Periods = new ArrayList<String>();

		boolean isPosition = false;
		boolean isCompany = false;
		boolean isPeriod = false;

		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			// For each row, iterate through all the columns
			Iterator<Cell> cellIterator = row.cellIterator();

			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				if (isPosition && !getExcelValue(cell).isEmpty()) {
					Positions.add(getExcelValue(cell));
				} else if (isCompany && !getExcelValue(cell).isEmpty()) {
					Companies.add(getExcelValue(cell));
				} else if (isPeriod && !getExcelValue(cell).isEmpty()) {
					Periods.add(getExcelValue(cell));
				}

				switch (getExcelValue(cell)) {
				case "Position:":
					isPosition = true;
					break;
				case "Company:":
					isCompany = true;
					break;
				case "Period:":
					isPeriod = true;
					break;
				}
			}

			isPosition = false;
			isCompany = false;
			isPeriod = false;
		}

		logger.debug("Positions: " + Positions);
		logger.debug("Companies: " + Companies);
		logger.debug("Periods: " + Periods);

		DBUtils dbu = new DBUtils();
		ArrayList<Integer> expIDs = new ArrayList<Integer>();

		for (int i = 0; i < Positions.size(); i++) {
			Experience exp = new Experience();
			exp.setPOSITION(Positions.get(i));
			exp.setCOMPANY(Companies.get(i));
			exp.setPERIOD(Periods.get(i));

			try {
				expIDs.add(dbu.insertExperiences(exp, id_last_temp));
			} catch (ClassNotFoundException | SQLException e) {
				logger.error(e);
			}

		}

		return expIDs;
	}

	public void parseExcelSheetExpActToDatabase(XSSFSheet sheet,
			ArrayList<Integer> id_exp) {
		ArrayList<ArrayList<String>> expAct = new ArrayList<ArrayList<String>>();
		boolean isActivity = false;
		JavaHelpUtils jhu = new JavaHelpUtils();

		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			ArrayList<String> expActRow = new ArrayList<String>();
			// For each row, iterate through all the columns
			Iterator<Cell> cellIterator = row.cellIterator();

			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();			
				if (isActivity && !getExcelValue(cell).isEmpty()) {
					expActRow.add(getExcelValue(cell));
				}

				switch (getExcelValue(cell)) {
				case "Activity:":
					isActivity = true;
					break;
				}
			}

			if (isActivity) {
				expAct.add((ArrayList<String>) jhu.deepClone(expActRow));
			}
			isActivity = false;
			expActRow.clear();
		}

		logger.debug("expAct: " + expAct);

		for (int i = 0; i < expAct.get(0).size(); i++) {
			Exp_activity exp_act = new Exp_activity();
			ArrayList<String> exp_activities = new ArrayList<String>();
			for (int j = 0; j < expAct.size(); j++) {
				if (expAct.get(j).size() > i) {
					exp_activities.add(expAct.get(j).get(i));
				}
			}
			exp_act.setExp_activities(exp_activities);

			DBUtils dbu = new DBUtils();
			try {
				dbu.insertExperienceActivities(exp_act, id_exp.get(i));
			} catch (ClassNotFoundException | SQLException e) {
				logger.error(e);
			}
		}

		return;
	}

	public void parseExcelSheetCertToDatabase(XSSFSheet sheet,
			Integer id_last_temp) {
		JavaHelpUtils jhu = new JavaHelpUtils();
		ArrayList<ArrayList<String>> certifications = new ArrayList<ArrayList<String>>();
		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			// For each row, iterate through all the columns
			Iterator<Cell> cellIterator = row.cellIterator();
			
			ArrayList<String> certification_temp = new ArrayList<String>();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				String cellValue = getExcelValue(cell);
				if (!cellValue.equals("Certification name") && !cellValue.equals("Date") 
						&& !cellValue.isEmpty()) {
					certification_temp.add(cellValue);
				}
			}
			if(certification_temp.size()>0){
				certifications.add((ArrayList<String>) jhu.deepClone(certification_temp));
			}
			certification_temp.clear();
		}
		
		logger.debug("certifications: " + certifications);
		
		Certification cert = new Certification();
		DBUtils dbu = new DBUtils();
		for(ArrayList<String> certification : certifications){
			cert.setCERT_NAME(certification.get(0));
			cert.setCERT_DATE(certification.get(1));
			
			try {
				dbu.insertCertification(cert, id_last_temp);
			} catch (ClassNotFoundException | SQLException e) {
				logger.error(e);
			}
		}
		
		return;
	}

	public void parseExcelSheetEducToDatabase(XSSFSheet sheet,
			Integer id_last_temp) {
		JavaHelpUtils jhu = new JavaHelpUtils();
		ArrayList<ArrayList<String>> educations = new ArrayList<ArrayList<String>>();
		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			// For each row, iterate through all the columns
			Iterator<Cell> cellIterator = row.cellIterator();
			
			ArrayList<String> education_temp = new ArrayList<String>();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				String cellValue = getExcelValue(cell);
				if (!cellValue.equals("Diploma") && !cellValue.equals("Educational center") && !cellValue.equals("Period") 
						&& !cellValue.isEmpty()) {
					education_temp.add(cellValue);
				}
			}
			if(education_temp.size()>0){
				educations.add((ArrayList<String>) jhu.deepClone(education_temp));
			}
			education_temp.clear();
		}
		
		logger.debug("educations: " + educations);
		
		Education educ = new Education();
		DBUtils dbu = new DBUtils();
		for(ArrayList<String> education : educations){
			educ.setDIPLOMA(education.get(0));
			educ.setEDUC_CENTER(education.get(1));
			educ.setEDUC_PERIOD(education.get(2));
			
			try {
				dbu.insertEducation(educ, id_last_temp);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		
		return;
	}

	public void parseExcelSheetVisCountToDatabase(XSSFSheet sheet,
			Integer id_last_temp) {
		JavaHelpUtils jhu = new JavaHelpUtils();
		ArrayList<ArrayList<String>> vis_countries = new ArrayList<ArrayList<String>>();
		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			// For each row, iterate through all the columns
			Iterator<Cell> cellIterator = row.cellIterator();
			
			ArrayList<String> vis_countries_temp = new ArrayList<String>();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				String cellValue = "";

				switch (cell.getCellType()) 
				{ case Cell.CELL_TYPE_NUMERIC:
					cellValue = String.valueOf((int) cell.getNumericCellValue()); break;
				case Cell.CELL_TYPE_STRING:
					cellValue = getExcelValue(cell); break;
				case Cell.CELL_TYPE_FORMULA:
					cellValue = String.valueOf((int) cell.getNumericCellValue()); break;	} 
				
				if (!cellValue.equals("Visited countries IDs") && !cellValue.equals("Visited countries") 
						&& !cellValue.isEmpty() && !cellValue.equals("0")) {
					vis_countries_temp.add(cellValue);
				}
			}
			if(vis_countries_temp.size()>0){
				vis_countries.add((ArrayList<String>) jhu.deepClone(vis_countries_temp));
			}
			vis_countries_temp.clear();
		}
		
		logger.debug("vis_countries: " + vis_countries);
		
		Country ctr = new Country();
		DBUtils dbu = new DBUtils();
		for(ArrayList<String> vis_country : vis_countries){
			ctr.setCOUNTRY_ID(vis_country.get(0));
			ctr.setCOUNTRY_NAME(vis_country.get(1));
			
			try {
				dbu.insertVisitedCountries(ctr, id_last_temp);
			} catch (ClassNotFoundException | SQLException e) {
				logger.error(e);
			}
		}
		return;
	}

}