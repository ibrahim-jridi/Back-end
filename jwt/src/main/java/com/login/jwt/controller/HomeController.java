package com.login.jwt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.login.jwt.dao.FormationDao;
import com.login.jwt.entity.Formation;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class HomeController {
	@Autowired
	  private FormationDao formationDao;
	
	// get all formation
			@GetMapping("/formation")
				public List<Formation>  getAllFormations(){
					return formationDao.findAll();	
			}

}
