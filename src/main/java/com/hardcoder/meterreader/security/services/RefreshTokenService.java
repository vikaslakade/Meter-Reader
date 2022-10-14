package com.hardcoder.meterreader.security.services;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import com.hardcoder.meterreader.models.RefreshToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hardcoder.meterreader.exception.TokenRefreshException;
import com.hardcoder.meterreader.repository.RefreshTokenRepository;
import com.hardcoder.meterreader.repository.RegisterEMRepository;

@Service
public class RefreshTokenService {
  @Value("${hardcoder.app.jwtRefreshExpirationMs}")
  private Long refreshTokenDurationMs;

  @Autowired
  private RefreshTokenRepository refreshTokenRepository;

  @Autowired
  private RegisterEMRepository registerEMRepository;

  public Optional<RefreshToken> findByToken(String token) {
    return refreshTokenRepository.findByToken(token);
  }

  public RefreshToken createRefreshToken(Long registerEMId) {
    RefreshToken refreshToken = new RefreshToken();

    refreshToken.setRegisterEM(registerEMRepository.findById(registerEMId).get());
    refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
    refreshToken.setToken(UUID.randomUUID().toString());

    refreshToken = refreshTokenRepository.save(refreshToken);
    return refreshToken;
  }

  public RefreshToken verifyExpiration(RefreshToken token) {
    if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
      refreshTokenRepository.delete(token);
      throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
    }

    return token;
  }

  @Transactional
  public int deleteByRegisterEMId(Long registerEMId) {
    return refreshTokenRepository.deleteByRegisterEM(registerEMRepository.findById(registerEMId).get());
  }
}
