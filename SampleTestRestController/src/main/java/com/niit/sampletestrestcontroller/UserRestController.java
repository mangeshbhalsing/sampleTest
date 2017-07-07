package com.niit.sampletestrestcontroller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.sampletestbackend.DAO.UserDAO;
import com.niit.sampletestbackend.model.User;

@CrossOrigin("http://localhost:8085")
@RestController
public class UserRestController {

	Logger log = LoggerFactory.getLogger("UserRestController");

	@Autowired
	User user;

	@Autowired
	UserDAO userDAO;

	@Autowired
	private HttpSession session;

	@RequestMapping("/hello")
	public String test() {

		return "hello world";

	}
	
	
	
	@RequestMapping(value="/create", method = RequestMethod.POST )
	ResponseEntity<User> createUser(@RequestBody User user)
	{
		
		try {
			log.debug("entering save method");
			userDAO.save(user);
		} catch (Exception e) {
	
			
			log.debug("entering save method");
		}
			
		return new ResponseEntity<User> (user, HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value="/login", method = RequestMethod.POST )
	ResponseEntity<User> validation(@RequestBody User user , HttpSession session)
	{
		String id=user.getUserID();
		String pas=user.getPasword();
		user=userDAO.validate(id,pas);
		
		if(user==null)
		{
			user = new User();//why do we need to set new user
			user.setErrorcode("404");
			user.setErrormessage("invalid credetial");
		}else{
			
			user.setErrorcode("200");
			user.setErrormessage("valid credential");
			session.setAttribute("loggedInUserID", user.getUserID());
			
			log.debug("you are logged in "+session.getAttribute("loggedInUserID"));
			
			
		}
		
		
		
		
	
		return new ResponseEntity<User>(user, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/logout/{userID}", method = RequestMethod.GET)
	public ResponseEntity<User> logout(@PathVariable ("userID") String userID ,HttpSession session) {
		
				
		 session.getAttribute(userID);	
		session.invalidate();
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	

}
