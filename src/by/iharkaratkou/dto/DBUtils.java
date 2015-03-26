package by.iharkaratkou.dto;

import java.sql.*;

import by.iharkaratkou.bo.GeneralData;

public class DBUtils {
	
	private Connection getConnection(String dbLogin, String dbPassword) throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = null;
		String login = dbLogin;
		String pass = dbPassword;
		conn = DriverManager.getConnection("jdbc:mysql://localhost/web_resume",login,pass);
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
	
	private boolean execInsert(Connection conn, String query) throws SQLException{	
		Statement statement = conn.createStatement();
		int rowsNumber = statement.executeUpdate(query);
		return (rowsNumber == 1)? true: false;
	}
	
	
	public int insertGeneralData(GeneralData gd) throws SQLException, ClassNotFoundException{
		
		boolean res = false;
		Integer id = 0;
		String dbLogin = "ihar";
		String dbPassword = "ihar";
		
		String Name = gd.getNAME();
		String Surname = gd.getSURNAME();
		String CurPosition = gd.getCURRENT_POST();
		String CurCompany = gd.getCURRENT_COMPANY();
		String CurLocation = gd.getCURRENT_LOCATION();
		String CurBusPhone = gd.getCURRENT_BUS_PHONE();
		String CurBusMail = gd.getCURRENT_BUSINESS_MAIL();
		String LinkedIn = gd.getSN_LINKEDIN();
		String Twitter = gd.getSN_TWITTER();
		
		Connection conn = this.getConnection(dbLogin, dbPassword);
		
		String insertQuery = "insert into general_data (NAME,SURNAME,CURRENT_POST,CURRENT_COMPANY,CURRENT_LOCATION,CURRENT_BUS_PHONE,CURRENT_BUSINESS_MAIL,SN_LINKEDIN,SN_TWITTER)"
				+ " values ('"+ Name +"','"+ Surname +"','"+ CurPosition +"','"+ CurCompany +"','"+ CurLocation +"','"+ CurBusPhone +"','"+ CurBusMail +"','"+ LinkedIn +"','"+ Twitter +"')";
		String idQuery = "SELECT LAST_INSERT_ID()";
		
		res = this.execInsert(conn, insertQuery);
		id = this.execIDSelect(conn, idQuery);
		
		return id;
	}
	
/*	public boolean setActive(String id) throws SQLException, ClassNotFoundException{
		
		boolean res = false;
		String dbLogin = "ihar";
		String dbPassword = "ihar";
		
		Connection conn = this.getConnection(dbLogin, dbPassword);
		
		String updateQuery = "update myshop_customers set active = 'yes' where id = '"+ id +"'";
		
		res = this.execInsert(conn, updateQuery);
		
		return res;
	}*/

}
