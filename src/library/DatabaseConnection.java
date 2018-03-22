package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseConnection {
	private static ReadProperties propertiesLibrary = new ReadProperties();
	private static Properties properties = propertiesLibrary.readProperties();
	private static Connection conn;
	private static String url = properties.getProperty("url");
	private static String user = properties.getProperty("username");
	private static String password = properties.getProperty("password");
	
//	private static String url = "jdbc:mysql://localhost:3306/shareit?useUnicode=true&characterEncoding=UTF-8";
//	private static String user ="root";
//	private static String password ="";
	
	
	public static Connection getConnectMySql(){
		//nạp driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//tạo chuỗi conn
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			System.out.println("Không thể nạp driver");
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void main(String[] args) {
		DatabaseConnection lDb = new DatabaseConnection();
		System.out.println(lDb.getConnectMySql());
	}
}
