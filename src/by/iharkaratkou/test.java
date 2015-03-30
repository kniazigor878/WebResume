package by.iharkaratkou;

import java.sql.SQLException;
import java.util.Set;

import by.iharkaratkou.dto.DBUtils;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ExcelParser exlParser = new ExcelParser();
		//exlParser.parseExcelToDatabase("Template_resume.xlsx");
		DBUtils dbu = new DBUtils();
		try {
			System.out.println(dbu.selectVisCountries(9).get(0));
			/*for(ArrayList<byte[]> arr: dbu.selectVisCountries(9)){
				
			}*/
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
