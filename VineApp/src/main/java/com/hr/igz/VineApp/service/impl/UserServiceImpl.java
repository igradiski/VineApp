package com.hr.igz.VineApp.service.impl;

import com.hr.igz.VineApp.domain.RefreshToken;
import com.hr.igz.VineApp.domain.User;
import com.hr.igz.VineApp.domain.dto.UserDto;
import com.hr.igz.VineApp.repository.UserRepository;
import com.hr.igz.VineApp.security.jwt.JwtUtils;
import com.hr.igz.VineApp.security.jwt.payload.request.TokenRefreshRequest;
import com.hr.igz.VineApp.security.jwt.payload.response.JwtResponseToken;
import com.hr.igz.VineApp.security.jwt.payload.response.TokenRefreshResponse;
import com.hr.igz.VineApp.security.services.refreshTokenService;
import com.hr.igz.VineApp.security.servicesImpl.UserDetailsSecurityImpl;
import com.hr.igz.VineApp.service.UserService;
import com.hr.igz.VineApp.service.exception.ObjectAlreadyExists;
import com.hr.igz.VineApp.service.exception.TokenRefreshException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final AuthenticationManager authenticationManager;
	private final PasswordEncoder encoder;
	private final refreshTokenService refreshTokenService;
	private final JwtUtils jwtUtils;

	private Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	public UserServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager, PasswordEncoder encoder, com.hr.igz.VineApp.security.services.refreshTokenService refreshTokenService, JwtUtils jwtUtils) {
		this.userRepository = userRepository;
		this.authenticationManager = authenticationManager;
		this.encoder = encoder;
		this.refreshTokenService = refreshTokenService;
		this.jwtUtils = jwtUtils;
	}

	@Override
	@Transactional
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
	}

	@Override
	public ResponseEntity<Object> userLogin(UserDto loginRequest) {
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetailsSecurityImpl userDetails = (UserDetailsSecurityImpl) authentication.getPrincipal();
		String jwt = jwtUtils.generateJwtToken(userDetails);
		RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
		return ResponseEntity.ok(new JwtResponseToken(jwt,refreshToken.getToken(), 
				 userDetails.getUsername(), 
				 userDetails.getEmail(),
				userDetails.getAuthorities()));
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
		if (userRepository.existsByUsername(signUpRequest.username())) {
			throw new ObjectAlreadyExists("Korisnik vec postoji u bazi!");
		}

		if (userRepository.existsByEmail(signUpRequest.email())) {
			throw new ObjectAlreadyExists("Korisnik vec postoji u bazi!");
		}
		User user = new User();
		user.setName(signUpRequest.name());
		user.setSurname(signUpRequest.surname());
		user.setUsername(signUpRequest.username());
		user.setPassword(encoder.encode(signUpRequest.password()));
		user.setEmail(signUpRequest.email());
		user.setAktivan(0);
		userRepository.save(user);

		return ResponseEntity.status(HttpStatus.CREATED).body("Korisnik uspjesno kreiran!");
	}
}
