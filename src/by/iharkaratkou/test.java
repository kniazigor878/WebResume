package by.iharkaratkou;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

import by.iharkaratkou.dto.DBUtils;
import by.iharkaratkou.javaUtils.JavaHelpUtils;

public class test {
	
	final static Logger logger = Logger.getLogger(test.class);
	
	public static void main(String[] args) {
		/*test obj = new test();
		obj.runMe("mkyong");*/
		System.out.println("Игорь Коротков");
	}

	private void runMe(String parameter){
		 
		if(logger.isDebugEnabled()){
			logger.debug("This is debug : " + parameter);
		}
 
		if(logger.isInfoEnabled()){
			logger.info("This is info : " + parameter);
		}
 
		logger.warn("This is warn : " + parameter);
		logger.error("This is error : " + parameter);
		logger.fatal("This is fatal : " + parameter);
 
	}
}
