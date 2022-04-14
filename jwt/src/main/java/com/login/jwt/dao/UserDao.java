package com.login.jwt.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.login.jwt.entity.Formatter;
import com.login.jwt.entity.User;

@Repository
public interface UserDao extends CrudRepository<User, String> {

	Formatter save(Formatter formatter);
	Optional<User> findByUserName(String name);
	/*
	 * @Query("SELECT u FROM User u WHERE u.email = ?1") public User
	 * findByEmail(String email);
	 * 
	 * public User findByResetPasswordToken(String token);
	 */
	

	
}
