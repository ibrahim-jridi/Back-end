package com.login.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;


import com.login.jwt.dao.RoleDao;
import com.login.jwt.dao.UserDao;
import com.login.jwt.entity.Role;
import com.login.jwt.entity.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService  {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void initRoleAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleDao.save(adminRole);
        
        Role formatterRole = new Role();
        formatterRole.setRoleName("Formatter");
        formatterRole.setRoleDescription(" Formatter");
        roleDao.save(formatterRole);
        
        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        roleDao.save(userRole);

        User adminUser = new User();
        adminUser.setUserName("admin123");
        adminUser.setUserPassword(getEncodedPassword("admin@pass"));
        adminUser.setEmail("admin@gmail.com");
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userDao.save(adminUser);
        
        User formatterUser = new User();
        formatterUser.setUserName("formatter123");
        formatterUser.setUserPassword(getEncodedPassword("formatter@pass"));
        formatterUser.setEmail("jridiibrahim2000@gmail.com");
        formatterUser.setUserFirstName("formatter");
        formatterUser.setUserLastName("formatter");
        Set<Role> formatterRoles = new HashSet<>();
        formatterRoles.add(formatterRole);
        formatterUser.setRole(formatterRoles);
        userDao.save(formatterUser);

//        User user = new User();
//        user.setUserName("raj123");
//        user.setUserPassword(getEncodedPassword("raj@123"));
//        user.setUserFirstName("raj");
//        user.setUserLastName("sharma");
//        Set<Role> userRoles = new HashSet<>();
//        userRoles.add(userRole);
//        user.setRole(userRoles);
//        userDao.save(user);
    }

	
	  public User registerNewUser(User user) { 
		  Role role =  roleDao.findById("User").get(); Set<Role> userRoles = new HashSet<>();
		  			userRoles.add(role); user.setRole(userRoles);
		  			user.setUserPassword(getEncodedPassword(user.getUserPassword()));
		  			user.setUserConfirmPassword(getEncodedPassword(user.getUserConfirmPassword()));
		  			Iterable<User> users = userDao.findAll();
		  			for (User userExist : users) {
		  				if (user.getUserName().equals(userExist.getUserName())) {
		  					System.out.println("il y a un utilisateur avec ce Pseudo svp changer vos");
		  					return null;
		  				}}
	  
		  			return userDao.save(user);
		  			}
	  
	  public User registerNewFormatter(User formatter) { Role role =
			  roleDao.findById("Formatter").get(); Set<Role> userRoles = new HashSet<>();
			  userRoles.add(role); formatter.setRole(userRoles);
			  formatter.setUserPassword(getEncodedPassword(formatter.getUserPassword()));
				formatter.setUserConfirmPassword(getEncodedPassword(formatter.getUserConfirmPassword()));
			  Iterable<User> formatters = userDao.findAll();
	  			for (User formatterExist : formatters) {
	  				if (formatter.getUserName().equals(formatterExist.getUserName())) {
	  					System.out.println("il y a un utilisateur avec ce Pseudo svp changer vos");
	  					return null;
	  				}}
			  
			  return userDao.save(formatter); }
	  
	  public User registerNewGuest(User guest) { Role role =
			  roleDao.findById("User").get(); Set<Role> userRoles = new HashSet<>();
			  userRoles.add(role); guest.setRole(userRoles);
			  guest.setUserPassword(getEncodedPassword(guest.getUserPassword()));
			  guest.setUserConfirmPassword(getEncodedPassword(guest.getUserConfirmPassword()));
			  Iterable<User> guests = userDao.findAll();
	  			for (User userExist : guests) {
	  				if (guest.getUserName().equals(userExist.getUserName())) {
	  					System.out.println("il y a un utilisateur avec ce Pseudo svp changer vos");
	  					return null;
	  				}}
			  
			  return userDao.save(guest); }
	 

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
    
    
	public User findUserById(String id) {
      return userDao.findById(id).orElse(null);
	}

	
	public void deleteUser(String id) {
		userDao.deleteById(id);
	} 
	
	
	 public User findByUsername(String username) {
		   Optional<User> users = userDao.findByUserName(username);
		    if (users.isPresent()) {
		     User user = users.get();
		   return user;
		  }
		   return null;
		 }
    
    // for password Reset
	/*
	 * public void updateResetPasswordToken(String token, String email) throws
	 * UsernameNotFoundException { User User = userDao.findByEmail(email); if (User
	 * != null) { User.setResetPasswordToken(token); userDao.save(User); } else {
	 * throw new
	 * UsernameNotFoundException("il n'y a pas un utilisateur avec cette email " +
	 * email); } }
	 * 
	 * public User getByResetPasswordToken(String token) { return
	 * userDao.findByResetPasswordToken(token); }
	 */
     
	/*
	 * public void updatePassword(User User, String newPassword) {
	 * //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); String
	 * encodedPassword = passwordEncoder.encode(newPassword);
	 * User.setUserPassword(encodedPassword);
	 * 
	 * User.setResetPasswordToken(null); userDao.save(User); }
	 */


	
}
