package com.hr.igz.VineApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hr.igz.VineApp.domain.RefreshToken;
import com.hr.igz.VineApp.domain.User;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
	
	@Override
    Optional<RefreshToken> findById(Long id);

    Optional<RefreshToken> findByToken(String token);

	int deleteByUser(User user);

}
