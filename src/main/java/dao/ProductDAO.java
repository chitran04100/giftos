package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Product;
import sql.connection.DBConnection;

public class ProductDAO {
	public List<Product> getLatestProducts() throws SQLException {
		Connection connection = DBConnection.makeConnection();
		Statement stmt = connection.createStatement();
		String sqlQuery = "SELECT * FROM product WHERE isNew = 0";
		ResultSet resultSet = stmt.executeQuery(sqlQuery);

		List<Product> list = new ArrayList<Product>();

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			int price = resultSet.getInt("price");
			String imgName = resultSet.getString("imgName");
			int isNew = resultSet.getInt("isNew");
			String description = resultSet.getString("description");
			int categoryId = resultSet.getInt("categoryId");

			Product product = new Product(id, name, price, imgName, isNew, description, categoryId);
			list.add(product);

		}
		return list;

	}

	public static Product getProductById(int productId) throws SQLException {
		Connection connection = DBConnection.makeConnection();
		String sqlQuery = "SELECT * FROM product WHERE id = ?";
		PreparedStatement preStmt = connection.prepareStatement(sqlQuery);
		preStmt.setInt(1, productId);
		ResultSet resultSet = preStmt.executeQuery();

		if (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			int price = resultSet.getInt("price");
			String imgName = resultSet.getString("imgName");
			int isNew = resultSet.getInt("isNew");
			String description = resultSet.getString("description");
			int categoryId = resultSet.getInt("categoryId");

			return new Product(id, name, price, imgName, isNew, description, categoryId);
		}
		return null;

	}

	public static List<Product> getProductsByCategoryId(int categoryId) throws SQLException {

		Connection connection = DBConnection.makeConnection();

		String sqlQuery = "SELECT * FROM product WHERE categoryId = ?";
		PreparedStatement preStmt = connection.prepareStatement(sqlQuery);
		preStmt.setInt(1, categoryId);
		ResultSet resultSet = preStmt.executeQuery();

		List<Product> list = new ArrayList<Product>();

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			int price = resultSet.getInt("price");
			String imgName = resultSet.getString("imgName");
			int isNew = resultSet.getInt("isNew");
			String description = resultSet.getString("description");

			Product product = new Product(id, name, price, imgName, isNew, description, categoryId);
			list.add(product);

		}
		return list;

	}
}
