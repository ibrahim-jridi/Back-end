package com.login.jwt.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.login.jwt.dao.GuestDao;
import com.login.jwt.entity.Formation;
import com.login.jwt.entity.Formatter;
import com.login.jwt.entity.Guest;
import com.login.jwt.entity.User;
import com.login.jwt.service.GuestService;
import com.login.jwt.service.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class GuestController {
	@Autowired
	private GuestService guestService;
	@Autowired
	private GuestDao guestDao;
	@Autowired
	private UserService userService;

	private static final Logger logger = LoggerFactory.getLogger(GuestController.class);

	@Autowired
    private PasswordEncoder passwordEncoder;
	@GetMapping("/api/guests")
	public List<Guest> findAll(Authentication authentication) {
		logger.info("Request-By: " + authentication.getName() + ", Action: RetreiveAllGuests ");
		return guestService.findAll();
	}

	@GetMapping("/api/guests/{id}")
	public Guest findById(@PathVariable long id, Authentication authentication) {
		Guest Guest = guestService.findById(id);
		logger.info("Request-By: " + authentication.getName() + ", Action: findGuestById, ID : " + id
				+ ",  Retreived-Guest : " + Guest.toString());
		return Guest;
	}

	@DeleteMapping("/api/guests/{id}")
	public void deleteGuest(@PathVariable long id, Authentication authentication) {
		Guest Guest = guestService.deleteById(id);
		logger.info("Request By : " + authentication.getName() + ", Action: deleteGuest, Deleted-Guest : "
				+ Guest.toString());

	}
	// create guest rest api and save it in userDao
		@PostMapping({"/api/guestss"})
		public User registerNewGuest(@RequestBody
				  User guest) { 
			return userService.registerNewGuest(guest);
			
				  }
	//save guest in guestDao
	@PostMapping("/api/guests")
	public ResponseEntity<Object> createGuest(@RequestBody Guest Guest) {
		
		Guest.setUserPassword(passwordEncoder.encode(Guest.getUserPassword()));
		Guest savedGuest = guestService.save(Guest);

		logger.info("New Guest Registration, Registered-Guest = " + Guest.toString());

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedGuest.getId()).toUri();
		return ResponseEntity.created(location).build();

	}

	@PutMapping("/api/guests/{id}")
	public ResponseEntity<Guest> updateGuest(@PathVariable long id, @RequestBody Guest Guest,
			Authentication authentication) {
		Guest.setUserPassword(passwordEncoder.encode(Guest.getUserPassword()));
		Guest updatedGuest = guestService.updateGuest(id, Guest);
		
		logger.info("Request By : " + authentication.getName() + ", Action: updateGuest, Old-Guest : "
				+ Guest.toString()+", Updated-Guest : "+updatedGuest.toString());
		return new ResponseEntity<com.login.jwt.entity.Guest>(updatedGuest, HttpStatus.OK);
	}

	@PostMapping("/api/guests/{id}/formations")
	public void registerInformation(@PathVariable long id, @RequestBody Formation formation, Authentication authentication) {
		guestService.registerInFormation(id, formation);
		logger.info("Request By : " + authentication.getName() + ", Action: registerInformation, Registered-formation : "
				+ formation.toString());
	}

	@DeleteMapping("/api/guests/{GuestId}/formations/{formationId}")
	public void unregisterFromformation(@PathVariable long GuestId, @PathVariable long formationId,
			Authentication authentication) {
		Formation deletedformation = guestService.unregisterFromFormation(GuestId, formationId);
		logger.info("Request By : " + authentication.getName() + ", Action: unregisterFromformation, Unregistered-formation : "
				+ deletedformation.toString());
	}

	@GetMapping("/api/guests/{id}/formations")
	public List<Formation> retreiveGuestformations(@PathVariable long id, Authentication authentication) {
		List<Formation> formations = guestService.retrieveGuestFormations(id);
		logger.info("Request By : " + authentication.getName() + ", Action: retreiveGuestformations, Guest-formations : "
				+ formations.toString());
		return formations;
	}
	@GetMapping("/api/guests/{id}/formations/{formationId}")
	public Formation retreiveGuestFormation(@PathVariable long id, @PathVariable long formationId, Authentication authentication) {
		Formation formation = guestService.retreiveGuestFormation(id,formationId);
		logger.info("Request By : " + authentication.getName() + ", Action: retreiveGuestformation, Guest-formation : "
				+ formation.toString());
		return formation;
	}
	
}
