package by.study.news.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import by.study.news.bean.Article;
import by.study.news.bean.User;
import by.study.news.dao.NewsDAO;
import by.study.news.dao.exception.DAOException;

public class SQLArticleDAO implements NewsDAO {

	@Override
	public void add(Article article) throws DAOException {
		// TODO Auto-generated method stub

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/news_management?useSSL=false",
				"root", "password");

				PreparedStatement stmt = connection
						.prepareStatement("insert into news(title, brief, content, article_date, status, users_id) "
								+ "values(?, ?, ?, ?, ?, ?)");) {

			stmt.setString(1, article.getTitle());
			stmt.setString(2, article.getBrief());
			stmt.setString(3, article.getContent());
			stmt.setObject(4, article.getDate());
			stmt.setString(5, article.getStatus());
			stmt.setInt(6, article.getUserId());
			stmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DAOException("article placement error", e);
		}

	}

	@Override
	public void deleteById(int id) throws DAOException {

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/news_management?useSSL=false",
				"root", "password")) {

			try (PreparedStatement stmt = connection.prepareStatement("UPDATE news set status = ? where id = ? ")) {

				stmt.setString(1, "BLOCKED");
				stmt.setInt(2, id);
				stmt.executeUpdate();

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException("deleting error", e);
		}

	}

	@Override
	public void editById(Article article, int Id) throws DAOException {

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/news_management?useSSL=false",
				"root", "password")) {

			try (PreparedStatement stmt = connection.prepareStatement(
					"UPDATE news set title = ?, brief = ?, content = ?, article_date = ?  where id = ? ")) {

				stmt.setString(1, article.getTitle());
				stmt.setString(2, article.getBrief());
				stmt.setString(3, article.getContent());
				stmt.setObject(4, article.getDate());
				stmt.setInt(5, Id);

				int result = stmt.executeUpdate();

				if (result == 0) {
					throw new DAOException("error updating news in the system");
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException("editting error", e);
		}

	}

	@Override
	public Article getById(int id) throws DAOException {

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/news_management?useSSL=false",
				"root", "password")) {

			try (PreparedStatement stmt = connection.prepareStatement("select * from news where id=?")) {

				stmt.setInt(1, id);
				ResultSet resultSet = stmt.executeQuery();

				if (resultSet.next()) {

					return new Article(resultSet.getInt("id"), resultSet.getString("title"),
							resultSet.getString("brief"), resultSet.getString("content"),
							resultSet.getDate("article_date"), resultSet.getString("status"),
							resultSet.getInt("users_id"));

				} else {
					throw new DAOException("article not found");
				}

			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			throw new DAOException("getting article error", e1);
		}

	}

	public List<Article> getByIdFromTo(int from, int to) throws DAOException {

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/news_management?useSSL=false",
				"root", "password");

				PreparedStatement stmt = connection.prepareStatement("select * from news where id between ? and ?")) {

			stmt.setInt(1, from);
			stmt.setInt(2, to);

			ResultSet resultSet = stmt.executeQuery();

			List<Article> list = new ArrayList<>();
			while (resultSet.next()) {

				list.add(new Article(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("brief"),
						resultSet.getString("content"), resultSet.getDate("article_date"),
						resultSet.getString("status"), resultSet.getInt("users_id")));
			}
			return list;

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			throw new DAOException("getting articles error", e1);
		}
	}

	@Override
	public List<Article> getAll() throws DAOException {

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/news_management?useSSL=false",
				"root", "password")) {

			try (PreparedStatement stmt = connection.prepareStatement("select * from news")) {

				ResultSet resultSet = stmt.executeQuery();
				List<Article> list = new ArrayList<>();

				while (resultSet.next()) {

					list.add(new Article(resultSet.getInt("id"), resultSet.getString("title"),
							resultSet.getString("brief"), resultSet.getString("content"),
							resultSet.getDate("article_date"), resultSet.getString("status"),
							resultSet.getInt("users_id")));

				}
				return list;
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			throw new DAOException("getting articles error", e1);
		}

	}
}
