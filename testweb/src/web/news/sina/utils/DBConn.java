package web.news.sina.utils;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.log4j.Logger;

public class DBConn {	
	private static final Logger Log = Logger.getLogger(DBConn.class.getName());
	public static String CONN_URL="jdbc:mysql://localhost:3306/sina_news";
	public static String USERNAME="root";
	public static String PASSWORD="1234";
	
	private DBConn() {
		
	}
	
	public static Connection getConnection(){
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(CONN_URL, USERNAME, PASSWORD);
		} 
		catch (Exception e) {
			Log.error(e);
		}
		return conn;
	}
}
