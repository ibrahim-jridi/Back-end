package com.login.jwt.service;
import java.util.List;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.login.jwt.entity.Formation;
import com.login.jwt.entity.Guest;

public interface GuestService {


	
	public List<Guest> findAll();
		
	Guest save(Guest guest);

		Guest findById(long id);

		Guest deleteById(long id);
		
		Guest findByUserName(String username);

		Guest updateGuest(long id, Guest guest);

		void registerInFormation(long id, Formation formation);
		
		Formation unregisterFromFormation(long guestId,long formationId);
		Formation retreiveGuestFormation(long guestId,long formationId);
		public List<Formation> retrieveGuestFormations(long id);

}
