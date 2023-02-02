package com.example.userservice.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.userservice.auth.UserTokenState;
import com.example.userservice.beans.User;
import com.example.userservice.dto.JwtAuthenticationRequest;
import com.example.userservice.dto.RegistrationDTO;
import com.example.userservice.dto.UserDTO;
import com.example.userservice.service.UserService;
import com.example.userservice.utils.TokenUtils;

@RestController
@RequestMapping(value = "api/user/")
public class UserController {

	public static final String COLLECTION_ID = "/db/sample/users";
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenUtils tokenUtils;
	
	
	@PostMapping(value = "login", produces="application/xml", consumes="application/xml")
	public ResponseEntity<?> login(@RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response) {
		System.out.println("AA");
		Authentication auth;
		try {
			auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));		
		} catch(AuthenticationException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body("<error>Neispravni podaci za prijavu!</error>");
		}
		SecurityContextHolder.getContext().setAuthentication(auth);
		User user = (User) auth.getPrincipal();
		
		String jwt = tokenUtils.generateToken(user.getUsername());
		int expiresIn = tokenUtils.getExpiredIn();
		
		return ResponseEntity.ok(new UserTokenState(jwt, expiresIn, new UserDTO(user)));
	}
	
	@PostMapping(value = "register", produces = "application/xml", consumes="application/xml")
	public ResponseEntity<?> register(@RequestBody RegistrationDTO registrationDTO, UriComponentsBuilder ucBuilder){		
		User existingUser = userService.getUserByUsername(registrationDTO.getUsername());
		
		if (existingUser != null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body("<error>Ime je vec zauzeto!</error>");
		}
		
		User newUser = userService.saveUser(registrationDTO);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(new UserDTO(newUser));
	}
}
