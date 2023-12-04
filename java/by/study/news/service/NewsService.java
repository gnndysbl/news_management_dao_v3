package by.study.news.service;

import java.util.List;

import by.study.news.bean.Article;
import by.study.news.service.exception.ServiceException;

public interface NewsService {
	
	void addArticle(Article article) throws ServiceException;
	void deleteArticle(Article article) throws ServiceException;
	void editArticle(Article article) throws ServiceException;
	Article getArticle() throws ServiceException;
	List<Article> getAllArticles() throws ServiceException;

}
