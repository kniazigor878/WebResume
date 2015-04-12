package by.iharkaratkou;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import by.iharkaratkou.dto.DBUtils;

public class LabelsUploader {
	public void uploadLabels(ArrayList<String> labels, Integer id_last_temp) {
		DBUtils dbu = new DBUtils();
		
		try {
			for (String labelName : labels) {
				File file  = new File("d:/eclipse_workspace/upload/" + labelName);			
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
