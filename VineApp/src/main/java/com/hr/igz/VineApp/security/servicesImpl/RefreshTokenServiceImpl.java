package com.hr.igz.VineApp.security.servicesImpl;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hr.igz.VineApp.domain.RefreshToken;
import com.hr.igz.VineApp.exception.TokenRefreshException;
import com.hr.igz.VineApp.repository.RefreshTokenRepository;
import com.hr.igz.VineApp.repository.UserRepository;
import com.hr.igz.VineApp.security.services.refreshTokenService;

@Service
public class RefreshTokenServiceImpl implements refreshTokenService {

	@Value("${Vine.app.jwt.jwtRefreshExpirationMs}")
	private Long refreshTokenDurationMs;

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	@Autowired
	private UserRepository userRepository;

	
	public Optional<RefreshToken> findByToken(String token) {
	    return refreshTokenRepository.findByToken(token);
	  }
	
	
	@Override
	public RefreshToken createRefreshToken(Long id) {
		
		RefreshToken refreshToken = new RefreshToken();
	    refreshToken.setUser(userRepository.findById(id).get());
	    refreshToken.setExpire(Instant.now().plusMillis(refreshTokenDurationMs));
	    refreshToken.setToken(UUID.randomUUID().toString());
	    refreshToken = refreshTokenRepository.save(refreshToken);
	    return refreshToken;
	}
	
	public RefreshToken verifyExpiration(RefreshToken token) {
		if (token.getExpire().compareTo(Instant.now()) < 0) {
	      refreshTokenRepository.delete(token);
	      throw new TokenRefreshException(token.getToken(), "Istekao Vam je token, Prijavite se ponovno!");
	    }
	    return token;
	  }
	
	@Transactional
	  public int deleteByUserId(Long userId) {
	    return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
	  }
	
	

}
