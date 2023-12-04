package by.study.news.dao;

import java.util.List;

import by.study.news.bean.User;
import by.study.news.dao.exception.DAOException;

public interface UserDAO {
	
	User signIn(String login, String password) throws DAOException;
	void registration(User user) throws DAOException;
	User getByLogin (String login) throws DAOException;
	User getById (int id) throws DAOException;
	List <User> getByIdFromTo(int from, int to) throws DAOException;
	void blockUser (int id) throws DAOException;
	void activateUser (int id) throws DAOException;
	void updateUser(User user) throws DAOException;
	
}
