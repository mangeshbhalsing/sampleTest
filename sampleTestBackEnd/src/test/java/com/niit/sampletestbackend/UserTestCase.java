package com.niit.sampletestbackend;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.sampletestbackend.DAO.UserDAO;
import com.niit.sampletestbackend.model.User;

public class UserTestCase {

	@Autowired
	public static AnnotationConfigApplicationContext context;
	
	public static User user;
	
	public static UserDAO userDAO;
	
	@BeforeClass
	public static void initalize ()
	{
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		
		context.scan("com.niit");
		context.refresh();
		user=(User)context.getBean(User.class);
		userDAO=(UserDAO)context.getBean(UserDAO.class);
		

		
	}
	
	
	
	
	//@Test
	public void saveTestCase() {
		//user.setId(101);
		user.setUserID("akxjfvjsfiojrox");
		user.setPasword("asxodijflkfjs");
		 
		boolean flag = userDAO.save(user);
		assertEquals(false, flag);	
	}
	
	 @Test
	  public void validateCreationTestCase() {
		 
user = 	userDAO.validate("a","aa");
	assertEquals(null, user);
	  
}

}
