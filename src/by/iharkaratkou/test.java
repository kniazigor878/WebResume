package by.iharkaratkou;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

import by.iharkaratkou.dto.DBUtils;
import by.iharkaratkou.javaUtils.JavaHelpUtils;

public class test {
	
	//final static Logger logger = Logger.getLogger(test.class);
	
	public static void main(String[] args) {
		for(int i = 0; i<10 ;i++){
			System.out.println(i);
			assert(i<6);
		}
	}
}
