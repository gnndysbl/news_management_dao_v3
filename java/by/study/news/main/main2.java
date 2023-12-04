package by.study.news.main;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import by.study.news.bean.User;
import by.study.news.dao.UserDAO;
import by.study.news.dao.exception.DAOException;
import by.study.news.dao.factory.DAOFactory;

public class main2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		final UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
//		try {
//
//			List<User> user = userDAO.getByIdFromTo(51, 56);
//
//			for (User u : user) {
//				System.out.println(u);
//			}
//			
//		} catch (DAOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		try {

		//	userDAO.activateUser(57);
			
		//	userDAO.blockUser(37);
			
				User user = userDAO.getById(37);
				
				user.setName("Alex");
				
				userDAO.updateUser(user);



		
			
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		    String password = "qwerty";
//		    String hPassword = "$2a$10$W47N6H3VLL2oVvsYYm/kX.XBUf4UBdksbAgWZ9lAOGsat.AUd/I8e";
//		    
//		   System.out.println( BCrypt.checkpw(password, hPassword));

	}

}
