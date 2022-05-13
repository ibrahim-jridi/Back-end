package com.login.jwt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.login.jwt.dao.FormationDao;
import com.login.jwt.dao.GuestDao;
import com.login.jwt.entity.Formation;
import com.login.jwt.entity.Guest;
import com.login.jwt.util.CourseNotFoundException;
import com.login.jwt.util.StudentNotFoundException;
import com.login.jwt.util.UsernameAlreadyExistsException;
@Service
public class GuestServiceImpl implements GuestService {
	@Autowired
	private GuestDao guestDao;

	@Autowired
	private FormationDao formationDao;
	@Override
	public List<Guest> findAll() {
		return guestDao.findAll();
	}

	@Override
	public Guest save(Guest guest) {
		if (guestDao.findByUserName(guest.getUserName()) != null) {
			throw new UsernameAlreadyExistsException("username already exists");
		}
		return guestDao.save(guest);
	}

	@Override
	public Guest findById(long id) {
		Optional<Guest> guest = guestDao.findById(id);

		if (guest.isPresent()) {
			return guest.get();
		} else {
			throw new StudentNotFoundException("guest not found");
		}
	}

	@Override
	public Guest deleteById(long id) {
		Optional<Guest> guest = guestDao.findById(id);

		if (guest.isPresent()) {
			guestDao.delete(guest.get());
			return guest.get();
		} else {
			throw new StudentNotFoundException("guest not found");
		}
	}

	@Override
	public Guest updateGuest(long id, Guest guest) {
		Optional<Guest> existingGuest = guestDao.findById(id);

		if (existingGuest.isPresent()) {
			Guest updateGuest = guestDao.save(guest);
			return updateGuest;
		} else {
			throw new StudentNotFoundException("guest not found");
		}
	}

	@Override
	public void registerInFormation(long id, Formation formation) {

		Optional<Guest> guestOptional = guestDao.findById(id);
		if (guestOptional.isPresent()) {
			Guest guest = guestOptional.get();
			formation.setGuest(guest);
			formationDao.save(formation);
		} else {
			throw new StudentNotFoundException("guest not found");
		}
	}

	@Override
	public Formation unregisterFromFormation(long guestId,long formationId) {
		
		Optional<Guest> guestOptional = guestDao.findById(guestId);
		if (guestOptional.isPresent()) {
			Optional<Formation> formationOptional = formationDao.findById(formationId);
			if (formationOptional.isPresent()) {
				formationDao.delete(formationOptional.get());
				return formationOptional.get();
			}else {
				throw new CourseNotFoundException("course not found");
			}
			
		} else {
			throw new StudentNotFoundException("student not found");
		}
	}

	@Override
	public List<Formation> retrieveGuestFormations(long id) {
		Optional<Guest> guest = guestDao.findById(id);
		if (guest.isPresent()) {
			System.out.println("Getting Formations");
			return guest.get().getFormation();
		} else {
			throw new StudentNotFoundException("guest not found");
		}
	}

	@Override
	public Guest findByUserName(String username) {
		Guest guest = guestDao.findByUserName(username);

		if (guest != null) {
			return guest;
		} else {
			throw new StudentNotFoundException("guest not found");
		}
	}

	@Override
	public Formation retreiveGuestFormation(long guestId,long formationId) {
		Optional<Formation> formationOptional = formationDao.findById(formationId);
		if (formationOptional.isPresent()) {
			return formationOptional.get();
		}else {
			throw new CourseNotFoundException("formation not found");
		}
	}

	

}
