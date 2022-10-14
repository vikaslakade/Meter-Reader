package com.hardcoder.meterreader.controllers;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.hardcoder.meterreader.models.Body;
import com.hardcoder.meterreader.models.RefreshToken;
import com.hardcoder.meterreader.models.RegisterEM;
import com.hardcoder.meterreader.payload.request.SessionCreateRequest;
import com.hardcoder.meterreader.payload.request.EMSignupRequest;
import com.hardcoder.meterreader.payload.request.TokenRefreshRequest;
import com.hardcoder.meterreader.payload.response.JwtResponse;
import com.hardcoder.meterreader.payload.response.MessageResponse;
import com.hardcoder.meterreader.payload.response.TokenRefreshResponse;
import com.hardcoder.meterreader.security.jwt.JwtUtils;
import com.hardcoder.meterreader.security.services.RefreshTokenService;
import com.hardcoder.meterreader.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hardcoder.meterreader.exception.TokenRefreshException;
import com.hardcoder.meterreader.repository.RoleRepository;
import com.hardcoder.meterreader.repository.RegisterEMRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/abcd/em/auth/api")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  RegisterEMRepository registerEMRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @Autowired
  RefreshTokenService refreshTokenService;

  @PostMapping("/session-create")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody SessionCreateRequest sessionCreateRequest) {

    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(sessionCreateRequest.getUsername(), sessionCreateRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

    String jwt = jwtUtils.generateJwtToken(userDetails);

    RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

    return ResponseEntity.ok(new JwtResponse(200,new Body(jwt,refreshToken.getToken(),refreshToken.getExpiryDate().toString())));
  }

  @PostMapping("/register/user")
  public ResponseEntity<?> registerUser(@Valid @RequestBody EMSignupRequest signUpRequestEM) {
    if (registerEMRepository.existsByUsername(signUpRequestEM.getUsername())) {
      return ResponseEntity.badRequest().body(new MessageResponse( Instant.now().getEpochSecond(),"Error: Username is already taken!"));
    }

    if (registerEMRepository.existsByEMSN(signUpRequestEM.getEMSN())) {
      return ResponseEntity.badRequest().body(new MessageResponse(Instant.now().getEpochSecond(),"Error: EMSN is already in use!"));
    }

    // Create new user's account
    RegisterEM registerEM = new RegisterEM(signUpRequestEM.getUsername(), signUpRequestEM.getEMname(),signUpRequestEM.getEMSN(),
        encoder.encode(signUpRequestEM.getPassword()),signUpRequestEM.getCustomername());

   registerEMRepository.save(registerEM);

    return ResponseEntity.ok(new MessageResponse(Instant.now().getEpochSecond(),"EM registered successfully!"));
  }

  @PostMapping("/refreshtoken")
  public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
    String requestRefreshToken = request.getRefreshToken();

    return refreshTokenService.findByToken(requestRefreshToken)
        .map(refreshTokenService::verifyExpiration)
        .map(RefreshToken::getRegisterEM)
        .map(registerEM -> {
          String token = jwtUtils.generateTokenFromUsername(registerEM.getUsername());
          return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
        })
        .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
            "Refresh token is not in database!"));
  }
  
//  @PostMapping("/signout")
//  public ResponseEntity<?> logoutUser() {
//    UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    Long registerEMId = userDetails.getId();
//    refreshTokenService.deleteByRegisterEMId(registerEMId);
//    return ResponseEntity.ok(new MessageResponse(Instant.now().getEpochSecond(),"Log out successful!"));
//  }

}
