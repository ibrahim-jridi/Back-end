package com.login.jwt.service;
import java.util.Collection;
import java.util.Optional;

import com.login.jwt.entity.User;

public interface userPassword {
	 public Optional<User> findUserByEmail(String email);
	    public Optional<User> findUserByResetToken(String resetToken);
	    public void save(User user);
	    /*Here we define an interface that contains the method signatures for User service.   

		We need to be able to find the user by e-mail address or reset token.   Also, we need to be 
		able to update the user's record.*/
}
