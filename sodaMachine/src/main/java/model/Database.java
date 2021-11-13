package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	private final static String DB_URL = "jdbc:mysql://localhost/sodamachine";
	private final static String USER = "root";
	private final static String PASS = "";
	private static Connection connection;
	
	public static Connection getConnection() {
		if(connection!=null) {
			return connection;
		}
		else {
			try {
				return DriverManager.getConnection(DB_URL, USER, PASS);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
}