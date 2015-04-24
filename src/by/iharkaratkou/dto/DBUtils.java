package by.iharkaratkou.dto;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import by.iharkaratkou.UploadFile;
import by.iharkaratkou.bo.Certification;
import by.iharkaratkou.bo.Country;
import by.iharkaratkou.bo.Education;
import by.iharkaratkou.bo.Exp_activity;
import by.iharkaratkou.bo.Experience;
import by.iharkaratkou.bo.GeneralData;
import by.iharkaratkou.bo.Qualifications;

/**
 * This class contains methods which insert/update/delete/select data in//from/from database.
 * 
 * @author Ihar Karatkou
 * @version 1.0
 * @since 2015-04-20
 */
public class DBUtils {
	
	final static Logger logger = Logger.getLogger(DBUtils.class);
	
	public Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = null;
		String login = "ihar";
		String pass = "ihar";
		conn = DriverManager.getConnection("jdbc:mysql://localhost/web_resume?useUnicode=yes&characterEncoding=UTF-8",login,pass);
		return conn;
	}
	
	private int execIDSelect(Connection conn, String query) throws SQLException{	
		int id = 0;
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(query);
		while(rs.next()){
			id = rs.getInt(1);
		}
		return id;
	}
	
	private boolean execIUD(Connection conn, String query) throws SQLException{	
		Statement statement = conn.createStatement();
		int rowsNumber = statement.executeUpdate(query);
		return (rowsNumber == 1)? true: false;
	}
	

	public Integer insertGeneralData(GeneralData gd,Integer id_last_temp) throws SQLException, ClassNotFoundException{
		
		boolean res = false;
		Integer id = 0;
		
		String Name = gd.getNAME();
		String Surname = gd.getSURNAME();
		String CurPosition = gd.getCURRENT_POST();
		String CurCompany = gd.getCURRENT_COMPANY();
		String CurLocation = gd.getCURRENT_LOCATION();
		String CurBusPhone = gd.getCURRENT_BUS_PHONE();
		String CurBusMail = gd.getCURRENT_BUSINESS_MAIL();
		String LinkedIn = gd.getSN_LINKEDIN();
		String Twitter = gd.getSN_TWITTER();
		String Password = gd.getPASSWORD();
		
		Connection conn = this.getConnection();
		
		String insertQuery = "insert into general_data (NAME,SURNAME,CURRENT_POST,CURRENT_COMPANY,CURRENT_LOCATION,CURRENT_BUS_PHONE,CURRENT_BUSINESS_MAIL,SN_LINKEDIN,SN_TWITTER,PASSWORD)"
				+ " values ('"+ Name +"','"+ Surname +"','"+ CurPosition +"','"+ CurCompany +"','"+ CurLocation +"','"+ CurBusPhone +"','"+ CurBusMail +"','"+ LinkedIn +"','"+ Twitter +"',sha1('"+ Password +"'))";
		String idQuery = "SELECT LAST_INSERT_ID()";
		String deleteQuery = "delete from EDUCATIONS  where GEN_DAT_ID = '" + id_last_temp + "';"
				+ "delete from EXP_ACTIVITIES where EXP_ID in (select EXP_ID from experiences WHERE GEN_DAT_ID = '" + id_last_temp + "');"
				+ "delete from EXPERIENCES where GEN_DAT_ID = '" + id_last_temp + "';"
				+ "delete from PERSON_COUNTRIES  where GEN_DAT_ID = '" + id_last_temp + "';"
				+ "delete from PERSON_LABELS where GEN_DAT_ID = '" + id_last_temp + "';"
				+ "delete from QUALIFICATIONS where GEN_DAT_ID = '" + id_last_temp + "';"
				+ "delete from CERTIFICATIONS where GEN_DAT_ID = '" + id_last_temp + "';"
				+ "delete from GENERAL_DATA where GEN_DAT_ID = '" + id_last_temp + "'";
		Statement stmt = conn.createStatement();
		stmt.addBatch("delete from EDUCATIONS  where GEN_DAT_ID = '" + id_last_temp + "'");
		stmt.addBatch("delete from EXP_ACTIVITIES where EXP_ID in (select EXP_ID from experiences WHERE GEN_DAT_ID = '" + id_last_temp + "')");
		stmt.addBatch("delete from EXPERIENCES where GEN_DAT_ID = '" + id_last_temp + "'");
		stmt.addBatch("delete from PERSON_COUNTRIES  where GEN_DAT_ID = '" + id_last_temp + "'");
		stmt.addBatch("delete from PERSON_LABELS where GEN_DAT_ID = '" + id_last_temp + "'");
		stmt.addBatch("delete from QUALIFICATIONS where GEN_DAT_ID = '" + id_last_temp + "'");
		stmt.addBatch("delete from CERTIFICATIONS where GEN_DAT_ID = '" + id_last_temp + "'");	
		
		String updateQuery = "update general_data set NAME = '"+ Name +"', SURNAME = '"+ Surname +"', CURRENT_POST = '"+ CurPosition +"', CURRENT_COMPANY = '"+ CurCompany +"', CURRENT_LOCATION = '"+ CurLocation +"',CURRENT_BUS_PHONE = '"+ CurBusPhone +"', CURRENT_BUSINESS_MAIL = '"+ CurBusMail +"', SN_LINKEDIN = '"+ LinkedIn +"', SN_TWITTER = '"+ Twitter +"' where GEN_DAT_ID = '" + id_last_temp + "'";
		
		if(id_last_temp != 0){
			logger.debug(deleteQuery);
			logger.debug(updateQuery);
			stmt.executeBatch();
			res = this.execIUD(conn, updateQuery);
			id = id_last_temp;
		}else{
			logger.debug(insertQuery);
			res = this.execIUD(conn, insertQuery);
			id = this.execIDSelect(conn, idQuery);
		}
		
		return id;
	}
	
	public void insertQualifications(Qualifications qual, Integer id_last_temp) throws SQLException, ClassNotFoundException{
		
		boolean res = false;
		Integer id = 0;
		
		ArrayList<String> qualifications = qual.getQualifications();
		
		Connection conn = this.getConnection();
		
		for(String qualification: qualifications){
			String insertQuery = "insert into qualifications (GEN_DAT_ID,QUALIFICATION) values ('"+ id_last_temp.toString() +"','"+ qualification +"')";
			res = this.execIUD(conn, insertQuery);
		}
		
		return;
	}
	
	public Integer insertExperiences(Experience exp, Integer id_last_temp) throws SQLException, ClassNotFoundException{
		
		boolean res = false;
		Integer id = 0;
		
		String Position = exp.getPOSITION();
		String Company = exp.getCOMPANY();
		String Period = exp.getPERIOD();
		
		Connection conn = this.getConnection();
		
		String insertQuery = "insert into experiences (GEN_DAT_ID,POSITION,COMPANY,PERIOD) values ('"+ id_last_temp.toString() +"','"+ Position +"','"+ Company +"','"+ Period +"')";
		String idQuery = "SELECT LAST_INSERT_ID()";
		
		res = this.execIUD(conn, insertQuery);
		id = this.execIDSelect(conn, idQuery);
		
		return id;
	}
	
	public void insertExperienceActivities(Exp_activity exp_act, Integer id_exp_temp) throws SQLException, ClassNotFoundException{
		
		boolean res = false;
		
		ArrayList<String> exp_activities = exp_act.getExp_activities();
		logger.debug("exp_activities: " + exp_activities);
		logger.debug("id_exp: " + id_exp_temp);
		
		Connection conn = this.getConnection();
		
		for(String exp_activity: exp_activities){
			String insertQuery = "insert into exp_activities (EXP_ID,ACTIVITY) values ('"+ id_exp_temp.toString() +"','"+ exp_activity +"')";
			res = this.execIUD(conn, insertQuery);
		}
		
		return;
	}
	
	public void insertCertification(Certification cert, Integer id_last_temp) throws SQLException, ClassNotFoundException{
		
		boolean res = false;
		
		String Cert_name = cert.getCERT_NAME();
		String Date = cert.getCERT_DATE();
		
		Connection conn = this.getConnection();
		
		String insertQuery = "insert into certifications (GEN_DAT_ID,CERT_NAME,CERT_DATE) values ('"+ id_last_temp.toString() +"','"+ Cert_name +"','"+ Date +"')";
		
		res = this.execIUD(conn, insertQuery);
		
		return;
	}
	
	public void insertEducation(Education educ, Integer id_last_temp) throws SQLException, ClassNotFoundException{
		
		boolean res = false;
		
		String Diploma = educ.getDIPLOMA();
		String Educ_center = educ.getEDUC_CENTER();
		String Educ_period = educ.getEDUC_PERIOD();
		
		Connection conn = this.getConnection();
		
		String insertQuery = "insert into educations (GEN_DAT_ID,DIPLOMA,EDUC_CENTER,EDUC_PERIOD) values ('"+ id_last_temp.toString() +"','"+ Diploma +"','"+ Educ_center +"','"+ Educ_period +"')";
		
		res = this.execIUD(conn, insertQuery);
		
		return;
	}

	public void insertVisitedCountries(Country ctr, Integer id_last_temp) throws SQLException, ClassNotFoundException{
		
		boolean res = false;
		
		String Country_ID = ctr.getCOUNTRY_ID();
		String Country_name = ctr.getCOUNTRY_NAME();
		
		Connection conn = this.getConnection();
		
		String insertQuery = "insert into person_countries (GEN_DAT_ID,COUNTRY_ID) values ('"+ id_last_temp.toString() +"','"+ Country_ID +"')";
		
		res = this.execIUD(conn, insertQuery);
		
		return;
	}
	
	public void insertLabel(FileInputStream fis, File file, Integer id_last_temp) throws SQLException, ClassNotFoundException{
		
		boolean res = false;
		
		Connection conn = this.getConnection();
		
		String insertQuery = "insert into person_labels (GEN_DAT_ID,LABEL) values ( ? , ? )";
		PreparedStatement ps = conn.prepareStatement(insertQuery);
		ps.setInt(1, id_last_temp);
	    ps.setBinaryStream(2, fis, (int) file.length());
	    ps.executeUpdate();		
		return;
	}
	
	public ArrayList<ArrayList<String>> execSelect(String query) throws SQLException, ClassNotFoundException{	
		Connection conn = getConnection();
		ArrayList<ArrayList<String>> queryResult = new ArrayList<ArrayList<String>>();
		ArrayList<String> queryResultRow = new ArrayList<String>();
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(query);
		ResultSetMetaData meta = rs.getMetaData();
		int colCount = meta.getColumnCount();
		while(rs.next()){
			for (int column = 1; column <= colCount; column++){
				queryResultRow.add(rs.getString(column));
			}
			queryResult.add((ArrayList<String>) queryResultRow.clone());
			
			queryResultRow.clear();
		}
		return queryResult;
	}
	
	public ArrayList<ArrayList<byte[]>> execSelectBLOB(String query) throws SQLException, ClassNotFoundException{	
		Connection conn = getConnection();
		ArrayList<ArrayList<byte[]>> queryResult = new ArrayList<ArrayList<byte[]>>();
		ArrayList<byte[]> queryResultRow = new ArrayList<byte[]>();
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(query);
		ResultSetMetaData meta = rs.getMetaData();
		int colCount = meta.getColumnCount();
		while(rs.next()){
			for (int column = 1; column <= colCount; column++){
				queryResultRow.add(rs.getBytes(column));
			}
			queryResult.add((ArrayList<byte[]>) queryResultRow.clone());
			queryResultRow.clear();
		}
		return queryResult;
	}
	
	public ArrayList<ArrayList<String>> selectGeneralData(Integer id_last_temp) throws SQLException, ClassNotFoundException{
		ArrayList<ArrayList<String>> queryResult = new ArrayList<ArrayList<String>>();
		String selectQuery = "select NAME,SURNAME,CURRENT_POST,CURRENT_COMPANY,CURRENT_LOCATION,CURRENT_BUS_PHONE,CURRENT_BUSINESS_MAIL,SN_LINKEDIN,SN_TWITTER from general_data WHERE GEN_DAT_ID = " + id_last_temp;
		queryResult = this.execSelect(selectQuery);	
		return queryResult;
	}
	
	public ArrayList<ArrayList<String>> selectQualifications(Integer id_last_temp) throws SQLException, ClassNotFoundException{
		ArrayList<ArrayList<String>> queryResult = new ArrayList<ArrayList<String>>();
		String selectQuery = "select QUALIFICATION from qualifications WHERE GEN_DAT_ID = " + id_last_temp;
		queryResult = this.execSelect(selectQuery);	
		return queryResult;
	}
	
	public ArrayList<ArrayList<String>> selectExperiences(Integer id_last_temp) throws SQLException, ClassNotFoundException{
		ArrayList<ArrayList<String>> queryResult = new ArrayList<ArrayList<String>>();
		String selectQuery = "select EXP_ID,POSITION,COMPANY,PERIOD from experiences WHERE GEN_DAT_ID = " + id_last_temp;
		queryResult = this.execSelect(selectQuery);	
		return queryResult;
	}
	
	public ArrayList<ArrayList<String>> selectExpActivities(Integer id_last_temp) throws SQLException, ClassNotFoundException{
		ArrayList<ArrayList<String>> queryResult = new ArrayList<ArrayList<String>>();
		String selectQuery = "select EXP_ID, ACTIVITY from exp_activities WHERE EXP_ID IN (select EXP_ID from experiences WHERE GEN_DAT_ID = " + id_last_temp + ")";
		queryResult = this.execSelect(selectQuery);	
		return queryResult;
	}
	
	public ArrayList<ArrayList<String>> selectCertificatins(Integer id_last_temp) throws SQLException, ClassNotFoundException{
		ArrayList<ArrayList<String>> queryResult = new ArrayList<ArrayList<String>>();
		String selectQuery = "select CERT_NAME,CERT_DATE from certifications WHERE GEN_DAT_ID = " + id_last_temp;
		queryResult = this.execSelect(selectQuery);	
		return queryResult;
	}

	public ArrayList<ArrayList<String>> selectEducations(Integer id_last_temp) throws SQLException, ClassNotFoundException{
		ArrayList<ArrayList<String>> queryResult = new ArrayList<ArrayList<String>>();
		String selectQuery = "select DIPLOMA,EDUC_CENTER,EDUC_PERIOD from educations WHERE GEN_DAT_ID = " + id_last_temp;
		queryResult = this.execSelect(selectQuery);	
		return queryResult;
	}
	
	public ArrayList<ArrayList<String>> selectVisCountriesNames(Integer id_last_temp) throws SQLException, ClassNotFoundException{
		ArrayList<ArrayList<String>> queryResult = new ArrayList<ArrayList<String>>();
		String selectQuery = "select NAME from countries WHERE COUNTRY_ID IN (select COUNTRY_ID from person_countries WHERE GEN_DAT_ID = " + id_last_temp + ")";
		queryResult = this.execSelect(selectQuery);	
		return queryResult;
	}
	
	public ArrayList<ArrayList<byte[]>> selectVisCountries(Integer id_last_temp) throws SQLException, ClassNotFoundException{
		ArrayList<ArrayList<byte[]>> queryResult = new ArrayList<ArrayList<byte[]>>();
		String selectQuery = "select FLAG from countries WHERE COUNTRY_ID IN (select COUNTRY_ID from person_countries WHERE GEN_DAT_ID = " + id_last_temp + ")";
		queryResult = this.execSelectBLOB(selectQuery);	
		return queryResult;
	}
	
	public ArrayList<ArrayList<byte[]>> selectLabels(Integer id_last_temp) throws SQLException, ClassNotFoundException{
		ArrayList<ArrayList<byte[]>> queryResult = new ArrayList<ArrayList<byte[]>>();
		String selectQuery = "select LABEL from person_labels WHERE GEN_DAT_ID = " + id_last_temp;
		queryResult = this.execSelectBLOB(selectQuery);	
		return queryResult;
	}
	
	public Integer checkID(Integer id_last_temp) throws SQLException, ClassNotFoundException{
		ArrayList<ArrayList<String>> queryResult = new ArrayList<ArrayList<String>>();
		String selectQuery = "select GEN_DAT_ID from general_data WHERE GEN_DAT_ID = " + id_last_temp;
		queryResult = this.execSelect(selectQuery);	
		Integer isIDexist = 0;
		if(queryResult.size() > 0){
			isIDexist = 1;
		}
		return isIDexist;
	}
	
	public Integer checkPassword(Integer id_last_temp, String password) throws SQLException, ClassNotFoundException{
		ArrayList<ArrayList<String>> queryResult = new ArrayList<ArrayList<String>>();
		String selectQuery = "select GEN_DAT_ID from general_data WHERE PASSWORD = sha1('" + password + "') and GEN_DAT_ID = " + id_last_temp;
		queryResult = this.execSelect(selectQuery);
		Integer isPasswordCorrect = 0;
		if(queryResult.size() > 0){
			isPasswordCorrect = 1;
		}
		return isPasswordCorrect;
	}

}
