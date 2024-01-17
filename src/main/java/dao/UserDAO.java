package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.User;
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
	
	public static String registerUser(String userName, String password, String email) throws SQLException{

		Connection connection = DBConnection.makeConnection();
		
		String sqlQuery = "SELECT * FROM user WHERE userName = ?";
		PreparedStatement preStmt = connection.prepareStatement(sqlQuery);
		
		preStmt.setString(1, userName);
		ResultSet resultSet = preStmt.executeQuery();

		String result = "";
		if (resultSet.next()) {
			result = "alreadyRegisteredUser";
					//"Your account is already registered. Please try again with different username!");
		}
		else {
			String createUserSql = "INSERT INTO User(userName, password, email) VALUES (?,?,?)";
			PreparedStatement preStmt1 = connection.prepareStatement(createUserSql);
			
			preStmt1.setString(1, userName);
			preStmt1.setString(2, password);
			preStmt1.setString(3, email);
			
			ResultSet resultSet1 = preStmt.executeQuery();
			
			if(resultSet1.next()) {
				result = "successRegistered";
						//"Your account is registered successfully!");
			}
			else
			{
				result = "failRegistered";
						//"Your account is failed to registered. Please try again!");
			
			}
		}
		return result;
		
	}

}


