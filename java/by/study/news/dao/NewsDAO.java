package by.study.news.dao;

import java.util.List;

import by.study.news.bean.Article;
import by.study.news.dao.exception.DAOException;

public interface NewsDAO {
	
	void add(Article article) throws DAOException;
	void deleteById(int id) throws DAOException;
	void editById(Article article, int Id) throws DAOException;
	Article getById(int id) throws DAOException;
	List<Article> getByIdFromTo(int a, int b) throws DAOException;
	List<Article> getAll() throws DAOException;
	
	}


