package com.hardcoder.meterreader.repository;

import java.util.Optional;

import com.hardcoder.meterreader.models.RefreshToken;
import com.hardcoder.meterreader.models.RegisterEM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
  Optional<RefreshToken> findByToken(String token);

  @Modifying
  int deleteByRegisterEM(RegisterEM registerEM);
}
