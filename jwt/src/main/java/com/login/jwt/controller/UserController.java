package com.login.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.login.jwt.dao.FormatterDao;
import com.login.jwt.entity.Formatter;
import com.login.jwt.entity.User;
import com.login.jwt.service.UserService;
import com.login.jwt.util.ResourceNotFoundException;

import javax.annotation.PostConstruct;


@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
	  private FormatterDao formatterDao;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

	
	  @PostMapping({"/registerNewUser"}) public User registerNewUser(@RequestBody
	  User user) { return userService.registerNewUser(user); }
	  
	  @PostMapping({"/registerNewFormatter"}) public User registerNewFormatter(@RequestBody
			  User formatter) { return userService.registerNewFormatter(formatter); }
	 

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }
    
    @GetMapping({"/forFormatter"})
    @PreAuthorize("hasRole('Formatter')")
    public   String forFormatter(){
        return "This URL is only accessible to the formatter";
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
