package com.laptrinhjavaweb.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.dao.INewsDAO;
import com.laptrinhjavaweb.model.NewsModel;

public class NewsDAO implements INewsDAO {

	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/new_servlet";
			String user = "root";
			String password = "Zxcqwe098570";
			return DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<NewsModel> findByCategoryID(Long categoryId) {
		List<NewsModel> results = new ArrayList<>();
		String sql = "SELECT * FROM news WHERE categoryid = ?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		// Open connection
		Connection connection = getConnection();
		if (connection != null) {
			try {
				statement = connection.prepareStatement(sql);
				statement.setLong(1, categoryId);
				resultSet = statement.executeQuery();
				while (resultSet.next()) {
					NewsModel news = new NewsModel();
					news.setId(resultSet.getLong("id"));
					news.setTitle(resultSet.getString("title"));
					results.add(news);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					resultSet.close();
					statement.close();
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return results;
	}

}