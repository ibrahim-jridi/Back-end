package com.login.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.login.jwt.dao.UserDao;
import com.login.jwt.entity.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserDao userRepository;
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username)
				.orElseThrow(() -> new UsernameNotFoundException("User n'est pas trouvé avec ce username: " + username));
		return UserDetailsImpl.build(user);
	}
}
