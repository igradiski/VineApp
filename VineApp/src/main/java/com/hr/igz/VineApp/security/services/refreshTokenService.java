package com.hr.igz.VineApp.security.services;

import java.util.Optional;

import com.hr.igz.VineApp.domain.RefreshToken;



public interface refreshTokenService {

	RefreshToken createRefreshToken(Long id);

	Optional<RefreshToken> findByToken(String requestRefreshToken);

	RefreshToken verifyExpiration(RefreshToken tok);

}
