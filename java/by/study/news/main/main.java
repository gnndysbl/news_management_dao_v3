package by.study.news.main;

import by.study.news.bean.Article;
import by.study.news.bean.User;
import by.study.news.dao.NewsDAO;
import by.study.news.dao.UserDAO;
import by.study.news.dao.exception.DAOException;
import by.study.news.dao.factory.DAOFactory;
import by.study.news.dao.impl.SQLUserDAO;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class main {

	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//		User user1 = new User("Piotr", "Nowak", "piotr_n", "pnow@gmail.com", "qwerty");
//		user1.setDate(new Date());
//
//	    final UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
//	    try {
//			userDAO.registration(user1);
//		} catch (DAOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

////////////////////////////  add article   //////////////////////////////////

		
//		Article article1 = new Article("Sharks", "Once upon a time", "....and so on");
//		article1.setDate(new Date());
//		article1.setUserId(37);
//		article1.setStatus("ACTIVE");
//		System.out.println(article1);
//
//		System.out.println(article1);
//		Date now = new Date();
//		article1.setDate(now);
//		System.out.println(article1);
//		
//		Date date = article1.getDate();
//		System.out.println(date);
//
//		
//	    Timestamp timestamp = new Timestamp(now.getTime());
//	    System.out.println(timestamp);
//	    
//		final NewsDAO newsDAO = DAOFactory.getInstance().getArticleDAO();
//		try {
//			newsDAO.add(article1);
//		} catch (DAOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//////////////////////////// delete by id /////////////////////////////////////

		
//		final NewsDAO newsDAO = DAOFactory.getInstance().getArticleDAO();
//		try {
//			newsDAO.deleteById(24);
//		} catch (DAOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		//////////////////// editById ////////////////////////////
//		Article article2 = new Article("Bricks", "There was a brick", "....good one");
//
//		final NewsDAO newsDAO = DAOFactory.getInstance().getArticleDAO();
//		try {
//			newsDAO.editById(article2, 24);
//		} catch (DAOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

//////////////////////////// getById article //////////////////////////
		
//		final NewsDAO newsDAO = DAOFactory.getInstance().getArticleDAO();
//		try {
//			Article article = newsDAO.getById(25);
//			System.out.println(article);
//		} catch (DAOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	
		/////////////////////////// getArticlesFromTo ///////////////////////



//		final NewsDAO newsDAO = DAOFactory.getInstance().getArticleDAO();
//		try {
//
//			List<Article> list = newsDAO.getArticlesFromTo(24, 26);
//
//			for (Article e : list) {
//
//				System.out.println(e);
//			}
//			;
//
//		} catch (DAOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
//}

////////////////////////////////// get all ////////////////////////////
		
		final NewsDAO newsDAO = DAOFactory.getInstance().getArticleDAO();
		try {

			List<Article> list = newsDAO.getAll();

			for (Article e : list) {

				System.out.println(e);
			}
			;

		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}