package by.iharkaratkou;

import java.util.Set;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExcelParser exlParser = new ExcelParser();
		Set<String> sheetsNames = exlParser.getSheetsNames();
		for(String sheetName: sheetsNames){
			System.out.println(sheetName);
		}
	}

}
