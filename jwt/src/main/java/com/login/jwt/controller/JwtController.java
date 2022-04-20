package com.login.jwt.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.login.jwt.entity.JwtRequest;
import com.login.jwt.entity.JwtResponse;
import com.login.jwt.service.JwtService;
import com.login.jwt.service.UserDetailsImpl;
import com.login.jwt.util.JwtUtil;

@RestController
@CrossOrigin
public class JwtController {
	@Autowired
	JwtUtil jwtUtils;
    @Autowired
    private JwtService jwtService;
    @Autowired
	AuthenticationManager authenticationManager;

    @PostMapping({"/authenticate"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
    	//UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    	return jwtService.createJwtToken(jwtRequest);
    }
}
