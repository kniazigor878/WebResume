package by.iharkaratkou;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import by.iharkaratkou.dto.DBUtils;
import by.iharkaratkou.javaUtils.JavaHelpUtils;

/**
 * This class contains method which call method to write received labels in database.
 * 
 * @author Ihar Karatkou
 * @version 1.0
 * @since 2015-04-20
 */
public class LabelsUploader {
	public void uploadLabels(ArrayList<String> labels, Integer id_last_temp, String saveFile) {
		DBUtils dbu = new DBUtils();
		
		try {
			for (String labelName : labels) {
				File file  = new File(saveFile + labelName);			
				FileInputStream fis = new FileInputStream(file);
				dbu.insertLabel(fis,file,id_last_temp);
				
				
				fis.close();
			}
		} catch (IOException|ClassNotFoundException|SQLException e) {
			e.printStackTrace();
		}
		return;
	}
}
