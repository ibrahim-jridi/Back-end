package com.login.jwt.controller;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.login.jwt.dao.FormationDao;
import com.login.jwt.dao.FormatterDao;
import com.login.jwt.dao.ThemeDao;
import com.login.jwt.entity.Formation;
import com.login.jwt.entity.Formatter;
import com.login.jwt.entity.Role;
import com.login.jwt.entity.Theme;
import com.login.jwt.entity.User;
import com.login.jwt.util.ResourceNotFoundException;
import com.login.jwt.service.MailService;

import io.swagger.annotations.ApiOperation;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/admin")
public class FormationController {
	 @Autowired
	  private FormationDao formationDao;
	 @Autowired
	  private FormatterDao formatterDao;
	 @Autowired
	  private ThemeDao themeDao;
	 @Autowired
	  private FormatterController  formattercontroller;
	 @Autowired
	  private MailService  mailService;
	 
	 public boolean isSaved = false ;
	
	// get all formation
		@GetMapping("/formations")
			public List<Formation>  getAllFormations(){
				return formationDao.findAll();	
		}
		// create formation rest api
		@PostMapping("/formations")
		
		public Formation createFormation(@RequestBody Formation formation  ) {
			Theme theme = themeDao.findById(formation.getTheme().getId()).get();
			Formatter formateur = formatterDao.findById(formation.getFormatter().getId()).get();
			if (theme == null || formateur == null) return null;
			else {
				formation.setTheme(theme);
				formation.setFormatter(formateur);
				formateur.getFormation().add(formation);
				String email = formateur.getEmail();
				mailService.sendEmail(email);
				theme.getFormation().add(formation);
				formationDao.save(formation);
				formatterDao.save(formateur);
				themeDao.save(theme);
				return formation;
			}
			}
			
		
					 
					
			//}
		// get formation by id rest api
		@GetMapping("/formations/{id}")
		@ApiOperation("Returns Formation based on id .")
		public ResponseEntity<Formation> getFormationById(@PathVariable Long id) {
			Formation formation = formationDao.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("formation n'est pas trouv?? :" + id));
			return ResponseEntity.ok(formation);
		}
		
		
		//update formation
		@PutMapping("/formations/{id}")
		public ResponseEntity<Formation> updateFormation(@PathVariable Long id, @RequestBody Formation formationDetails){
			Formation formation = formationDao.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("formation n'est pas trouv?? :" + id));
			
			formation.setName(formationDetails.getName());
			formation.setDescription(formationDetails.getDescription());
			formation.setTheme(formationDetails.getTheme());
			formation.setLien(formationDetails.getLien());
			formation.setPrix(formationDetails.getPrix());
			formation.setFormatter(formationDetails.getFormatter());
			formation.setDate_creation(formationDetails.getDate_creation());
			formation.setDate_debut(formationDetails.getDate_debut());
			formation.setDate_final(formationDetails.getDate_final());
			
			
			Formation updatedformation = formationDao.save(formation);
			return ResponseEntity.ok(updatedformation);
		}
		// delete formation rest api
		@DeleteMapping("/formations/{id}")
		public ResponseEntity<Map<String, Boolean>> deletetheme(@PathVariable Long id){
			Formation formation = formationDao.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("formation n'est pas trouv?? :" + id));
			
			formationDao.delete(formation);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}

}
