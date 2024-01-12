package sql.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	public static Connection makeConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/giftos";
			Connection conn = DriverManager.getConnection(url, "root", "04100");
			return conn;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}