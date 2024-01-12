package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sql.connection.DBConnection;

public class UserDAO {
	public static String getUserByUsernameAndPassword(String userName, String password) throws SQLException {
		
		Connection connection = DBConnection.makeConnection();
		
		String sqlQuery = "SELECT * FROM user WHERE userName = ? AND password = ?";
		PreparedStatement preStmt = connection.prepareStatement(sqlQuery);
		
		preStmt.setString(1, userName);
		preStmt.setString(2, password);
		ResultSet resultSet = preStmt.executeQuery();

		if (resultSet.next()) {
			return "Success";
		}
		return "Fail";

	}

}


