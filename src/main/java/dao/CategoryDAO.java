package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Category;
import sql.connection.DBConnection;

public class CategoryDAO {
	public static List<Category> getAllCategories() throws SQLException {
		Connection connection = DBConnection.makeConnection();

		String sqlQuery = "SELECT * FROM category ORDER BY priority;";
		Statement stmt = connection.createStatement();

		ResultSet resultSet = stmt.executeQuery(sqlQuery);

		ArrayList<Category> list = new ArrayList<Category>();

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			int priority = resultSet.getInt("priority");
			Category category = new Category(id, name, priority);
			list.add(category);
		}
		return list;
	}
}
