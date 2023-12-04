package by.study.news.dao.impl;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import by.study.news.bean.User;
import by.study.news.dao.UserDAO;
import by.study.news.dao.connection.ConnectionPool;
import by.study.news.dao.connection.ConnectionPoolException;
import by.study.news.dao.exception.DAOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

public class SQLUserDAO implements UserDAO {

//	private final ConnectionPool connectionPool = ConnectionPool.getInstance();

//////////////  registration with connection pool, pool is not working  ///////////////////////

//	public void registration(User user) throws DAOException {
//
//		try (Connection connection = connectionPool.takeConnection();
//
//				PreparedStatement stmt = connection.prepareStatement("insert into users(login, password) values(?, ?)",
//						Statement.RETURN_GENERATED_KEYS);
//
//				PreparedStatement stmt1 = connection
//						.prepareStatement("insert into user_details(name, last_name, email, register_date, users_id) "
//								+ "values(?, ?, ?, ?, ?)");) {
//
//			stmt.setString(1, user.getLogin());
//			stmt.setString(2, user.getPassword());
//			stmt.executeUpdate();
//
//			ResultSet keys = stmt.getGeneratedKeys();
//			int lastKey = 1;
//			while (keys.next()) {
//				lastKey = keys.getInt(1);
//			}
//
//			stmt1.setString(1, user.getName());
//			stmt1.setString(2, user.getLastName());
//			stmt1.setString(3, user.getEmail());
//			stmt1.setDate(4, java.sql.Date.valueOf(java.time.LocalDate.now()));
//			stmt1.setInt(5, lastKey);
//			stmt1.executeUpdate();
//
//		}
//
//		catch (SQLException | ConnectionPoolException e) {
//			// TODO Auto-generated catch block
//			throw new DAOException("registration error", e);
//		}
//	}

	public void registration(User user) throws DAOException {

		user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
		user.setStatus("ACTIVE");

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/news_management?useSSL=false",
				"root", "password");

				PreparedStatement stmt = connection.prepareStatement(
						"insert into users(login, password, status) values(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

				PreparedStatement stmt1 = connection
						.prepareStatement("insert into user_details(name, last_name, email, register_date, users_id) "
								+ "values(?, ?, ?, ?, ?)");

		) {

			stmt.setString(1, user.getLogin());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getStatus());

			stmt.executeUpdate();

			ResultSet keys = stmt.getGeneratedKeys();
			int lastKey = 1;
			while (keys.next()) {
				lastKey = keys.getInt(1);
			}

			stmt1.setString(1, user.getName());
			stmt1.setString(2, user.getLastName());
			stmt1.setString(3, user.getEmail());
			stmt1.setObject(4, user.getDate());
			stmt1.setInt(5, lastKey);
			stmt1.executeUpdate();

		}

		catch (SQLException e) {
			throw new DAOException("registration error", e);
		}
	}

	@Override
	public User signIn(String login, String password) throws DAOException {

		User user = getByLogin(login);

		if (BCrypt.checkpw(password, user.getPassword()) != true) {
			throw new DAOException("signing in error. wrong password");

		}

		if (user.getStatus().equalsIgnoreCase("ACTIVE") != true) {
			throw new DAOException("signing in error. user status BLOCKED");

		}

		return user;
	}

	@Override
	public User getByLogin(String login) throws DAOException {

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/news_management?useSSL=false",
				"root", "password");

				PreparedStatement stmt = connection.prepareStatement("select * from users where login=?");
				PreparedStatement stmt1 = connection.prepareStatement("select * from user_details where users_id=?");

		) {

			stmt.setString(1, login);

			ResultSet resultSet = stmt.executeQuery();

			if (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt("id"));
				user.setLogin(resultSet.getString("login"));
				user.setPassword(resultSet.getString("password"));
				user.setStatus(resultSet.getString("status"));

				stmt1.setInt(1, user.getId());
				ResultSet resultSet1 = stmt1.executeQuery();
				resultSet1.next();

				user.setName(resultSet1.getString("name"));
				user.setLastName(resultSet1.getString("last_name"));
				user.setEmail(resultSet1.getString("email"));
				user.setDate(resultSet1.getDate("register_date"));

				return user;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("getting user by login error", e);
		}
		throw new DAOException("user not found");

	}

	@Override
	public User getById(int id) throws DAOException {

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/news_management?useSSL=false",
				"root", "password");

				PreparedStatement stmt = connection.prepareStatement("select * from users where id=?");
				PreparedStatement stmt1 = connection.prepareStatement("select * from user_details where users_id=?");

		) {

			stmt.setInt(1, id);

			ResultSet resultSet = stmt.executeQuery();

			if (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt("id"));
				user.setLogin(resultSet.getString("login"));
				user.setPassword(resultSet.getString("password"));
				user.setStatus(resultSet.getString("status"));

				stmt1.setInt(1, user.getId());
				ResultSet resultSet1 = stmt1.executeQuery();
				resultSet1.next();

				user.setName(resultSet1.getString("name"));
				user.setLastName(resultSet1.getString("last_name"));
				user.setEmail(resultSet1.getString("email"));
				user.setDate(resultSet1.getDate("register_date"));

				return user;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("getting user by login error", e);
		}
		throw new DAOException("user not found");

	}

	@Override
	public List<User> getByIdFromTo(int from, int to) throws DAOException {

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/news_management?useSSL=false",
				"root", "password");

				PreparedStatement stmt = connection.prepareStatement("select * from users where id between ? and ?");
				PreparedStatement stmt1 = connection.prepareStatement("select * from user_details where users_id=?");

		) {

			stmt.setInt(1, from);
			stmt.setInt(2, to);

			ResultSet resultSet = stmt.executeQuery();

			List<User> users = new ArrayList<>();

			while (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt("id"));
				user.setLogin(resultSet.getString("login"));
				user.setPassword(resultSet.getString("password"));
				user.setStatus(resultSet.getString("status"));

				stmt1.setInt(1, user.getId());
				ResultSet resultSet1 = stmt1.executeQuery();
				resultSet1.next();

				user.setName(resultSet1.getString("name"));
				user.setLastName(resultSet1.getString("last_name"));
				user.setEmail(resultSet1.getString("email"));
				user.setDate(resultSet1.getDate("register_date"));

				users.add(user);
			}

			return users;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("error generating usersList", e);

		}

	}

	@Override
	public void blockUser(int id) throws DAOException {

		User user = getById(id);

		user.setStatus("BLOCKED");

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/news_management?useSSL=false",
				"root", "password");

				PreparedStatement stmt = connection.prepareStatement("UPDATE users set status = ? where id = ? ");

		) {

			stmt.setString(1, user.getStatus());
			stmt.setInt(2, user.getId());

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("error blocking user", e);
		}

	}

	@Override
	public void activateUser(int id) throws DAOException {

		User user = getById(id);

		user.setStatus("ACTIVE");

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/news_management?useSSL=false",
				"root", "password");

				PreparedStatement stmt = connection.prepareStatement("UPDATE users set status = ? where id = ? ");

		) {

			stmt.setString(1, user.getStatus());
			stmt.setInt(2, user.getId());

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("error blocking user", e);
		}

	}

	@Override
	public void updateUser(User user) throws DAOException {

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/news_management?useSSL=false",
				"root", "password");

				PreparedStatement stmt = connection.prepareStatement("UPDATE users set password = ? where id = ?");

				PreparedStatement stmt1 = connection.prepareStatement(
						"UPDATE user_details set name = ?, last_name = ?, email =? where users_id = ?");

		) {

			stmt.setString(1, user.getPassword());
			stmt.setInt(2, user.getId());
			stmt.executeUpdate();

			stmt1.setString(1, user.getName());
			stmt1.setString(2, user.getLastName());
			stmt1.setString(3, user.getEmail());
			stmt1.setInt(4, user.getId());

			stmt1.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
