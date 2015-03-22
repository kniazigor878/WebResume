package by.iharkaratkou.dto;

import java.sql.*;

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
	
	
	public int insertUser(User user) throws SQLException, ClassNotFoundException{
		
		boolean res = false;
		int id = 0;
		String dbLogin = "ihar";
		String dbPassword = "ihar";
		
		String name = user.getName();
		String surname = user.getSurname();
		String email = user.getEmail();
		String password = user.getPassword();
		
		Connection conn = this.getConnection(dbLogin, dbPassword);
		
		String insertQuery = "insert into myshop_customers (name,surname,email,password,active) values ('"+ name +"','"+ surname +"','"+ email +"',sha1('"+ password +"'),'no')";
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
