package com.hardcoder.meterreader.security.services;

import com.hardcoder.meterreader.models.RegisterEM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hardcoder.meterreader.repository.RegisterEMRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  RegisterEMRepository registerEMRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    RegisterEM registerEM = registerEMRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

    return UserDetailsImpl.build(registerEM);
  }

}
