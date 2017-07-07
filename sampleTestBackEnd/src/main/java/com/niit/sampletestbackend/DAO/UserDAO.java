package com.niit.sampletestbackend.DAO;

import com.niit.sampletestbackend.model.User;

public interface UserDAO {
	
	public boolean save(User user);
	
	public User validate(String userID,String pasword);
	

}
