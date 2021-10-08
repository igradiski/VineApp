package com.hr.igz.VineApp.services.impl;

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

import com.hr.igz.VineApp.domain.RefreshToken;
import com.hr.igz.VineApp.domain.User;
import com.hr.igz.VineApp.domain.dto.UserDto;
import com.hr.igz.VineApp.exception.TokenRefreshException;
import com.hr.igz.VineApp.exception.ObjectAlreadyExists;
import com.hr.igz.VineApp.repository.UserRepository;
import com.hr.igz.VineApp.security.jwt.JwtUtils;
import com.hr.igz.VineApp.security.jwt.payload.request.TokenRefreshRequest;
import com.hr.igz.VineApp.security.jwt.payload.response.JwtResponseToken;
import com.hr.igz.VineApp.security.jwt.payload.response.TokenRefreshResponse;
import com.hr.igz.VineApp.security.services.refreshTokenService;
import com.hr.igz.VineApp.security.servicesImpl.UserDetailsSecurityImpl;
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
	refreshTokenService refreshTokenService;
	
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
		UserDetailsSecurityImpl userDetails = (UserDetailsSecurityImpl) authentication.getPrincipal();
		String jwt = jwtUtils.generateJwtToken(userDetails);
		RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
		
		return ResponseEntity.ok(new JwtResponseToken(jwt,refreshToken.getToken(), 
				 userDetails.getUsername(), 
				 userDetails.getEmail()));
	}
	
	
	@Override
	public ResponseEntity<?> refreshToken(TokenRefreshRequest request) {
		
		String requestRefreshToken = request.getRefreshToken();
		
		return refreshTokenService.findByToken(requestRefreshToken)
		        .map(refreshTokenService::verifyExpiration)
		        .map(RefreshToken::getUser)
		        .map(user -> {
		          String token = jwtUtils.generateTokenFromUsername(user.getUsername());
		          return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
		        })
		        .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
		            "Refresh token is not in database!"));
		
	}

	@Override
	public ResponseEntity<Object> registerUser(UserDto signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			throw new ObjectAlreadyExists("Korisnik vec postoji u bazi!");
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			throw new ObjectAlreadyExists("Korisnik vec postoji u bazi!");
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
