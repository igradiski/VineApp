package com.hr.igz.VineApp.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hr.igz.VineApp.domain.User;
import com.hr.igz.VineApp.domain.dto.UserDto;
import com.hr.igz.VineApp.repository.UserRepository;
import com.hr.igz.VineApp.security.jwt.JwtResponse;
import com.hr.igz.VineApp.security.jwt.JwtUtils;
import com.hr.igz.VineApp.security.services.UserDetailsSecurityImpl;
import com.hr.igz.VineApp.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	JwtUtils jwtUtils;

	@Override
	@Transactional
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return user;
	}

	@Override
	public ResponseEntity<Object> userLogin(UserDto loginRequest) {
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		UserDetailsSecurityImpl userDetails = (UserDetailsSecurityImpl) authentication.getPrincipal();	
		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		//TODO refresh token
		return ResponseEntity.ok(new JwtResponse(jwt,jwt, 
				 userDetails.getUsername(), 
				 userDetails.getEmail(), 
				 roles));
	}

	@Override
	public ResponseEntity<Object> registerUser(UserDto signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body("hoho");
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body("hoho");
		}
		User user = new User();
		user.setName(signUpRequest.getName());
		user.setSurname(signUpRequest.getSurname());
		user.setUsername(signUpRequest.getUsername());
		user.setPassword(encoder.encode(signUpRequest.getPassword()));
		user.setEmail(signUpRequest.getEmail());
		user.setAktivan(0);
		userRepository.save(user);

		return ResponseEntity.status(HttpStatus.CREATED).body("Korisnik uspjesno kreiran!");
	}
}
