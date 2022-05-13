package com.login.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.login.jwt.dao.FormatterDao;
import com.login.jwt.dao.UserDao;
import com.login.jwt.entity.Formatter;
import com.login.jwt.entity.User;
import com.login.jwt.service.UserService;
import com.login.jwt.util.ResourceNotFoundException;

import java.util.Optional;

import javax.annotation.PostConstruct;


@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
	  private FormatterDao formatterDao;
    @Autowired
	  private UserDao userDao;
    @Autowired
	  private FormatterController formattercontroller;
    @Autowired
	  private UserController usercontroller;
    @Autowired
    private PasswordEncoder passwordEncoder;
	
	public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

	
	  @PostMapping({"/registerNewUser"}) public User registerNewUser(@RequestBody
	  User user) { return userService.registerNewUser(user); }
	  
	  @PostMapping({"/registerNewFormatter"}) public User registerNewFormatter(@RequestBody
			  User formatter) { return userService.registerNewFormatter(formatter); }
	  
	//get user by username
		@GetMapping("/findByUsername/{username}")
		 public User findByUserName(@PathVariable String username) {
			   Optional<User> users = userDao.findByUserName(username);
			    if (users.isPresent()) {
			     User user = users.get();
			   return user;
			  }
			   return null;
			 }
		// update formatter rest api
		
		@PutMapping("/user/{id}")
		public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User userDetails){
			User user = userDao.findByUserName(id)
					.orElseThrow(() -> new ResourceNotFoundException("user n'est pas trouvé avec ce id :" + id));
			
			user.setUserName(userDetails.getUserName());
			
			user.setUserFirstName(userDetails.getUserFirstName());
			user.setUserLastName(userDetails.getUserLastName());
			user.setEmail(userDetails.getEmail());
			//formatter.setImage(formatterDetails.getImage());
			user.setAdresse(userDetails.getAdresse());
			user.setSpecialite(userDetails.getSpecialite());
			user.setUserPassword(getEncodedPassword(user.getUserPassword()));
			user.setUserConfirmPassword(getEncodedPassword(user.getUserConfirmPassword()));
			
			User updatedUser = userDao.save(user);
			User updateUser = userDao.save(user);
			return ResponseEntity.ok(updatedUser);
			
		}

    @GetMapping({"/forAdmin/{username}"})
    @PreAuthorize("hasRole('Admin')")
    public User forAdmin(@PathVariable String username){
    	User user = usercontroller.findByUserName(username);
    	UserDetails userDetails =
    			(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	return user ;
       // return "This URL is only accessible to the admin";
    }
    
    @GetMapping({"/forFormatter/{username}"})
    @PreAuthorize("hasRole('Formatter')")
    public   Formatter forFormatter(@PathVariable String username){
    	Formatter formatter = formattercontroller.findByFormatterName(username);
    	UserDetails userDetails =
    			(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	return formatter ;
        //return "This URL is only accessible to the formatter";
		/*
		 * Formatter formatter = FormatterDao.findById(id) .orElseThrow(() -> new
		 * ResourceNotFoundException("formatteur n'est pas trouvé avec ce id :" + id));
		 * return ResponseEntity.ok(formatter);
		 */
    	
		
		
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "This URL is only accessible to the user";
    }
    
   
}
