package com.niit.sampletestbackend.DAOImpl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.sampletestbackend.DAO.UserDAO;
import com.niit.sampletestbackend.model.User;



//@Transactional//it allow the transaction 
@Transactional
@Repository("userDAO")
public class UserDAOImpl  implements UserDAO{
	
	Logger log = LoggerFactory.getLogger(UserDAOImpl.class);
	
	@Autowired
	public UserDAO userDAO;
	
	@Autowired
	public SessionFactory sessionFactory;
	
	

	public UserDAOImpl(SessionFactory sessionFactory)
	{
		
		this.sessionFactory = sessionFactory;
	}
	

	public boolean save(User user) {
	try {
		log.debug("entering saving method");
		sessionFactory.getCurrentSession().save(user);	// TODO Auto-generated method stub
		log.debug("exiting saving method");
			return true;
	} catch (HibernateException e) {
		// TODO Auto-generated catch block
		
		e.printStackTrace();
		return false;
	}
	}

	/*
	public User validate(String userID, String pasword) {
	
			log.debug("entering validation part");
			String hql = ("from User where userID = '"+userID+"' and pasword = '"+pasword+"'");

 return (User)	sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
			
		
	}
}*/
	
@Transactional
	public User validate(String userID, String pasword) {
		

		Query query = sessionFactory.getCurrentSession().createQuery("from User where userID=? and pasword=?");

		query.setString(0, userID);

		query.setString(1, pasword);

		return (User) query.uniqueResult() ;

	}

}
	
	

