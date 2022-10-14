package com.hardcoder.meterreader.repository;

import java.util.Optional;

import com.hardcoder.meterreader.models.RegisterEM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterEMRepository extends JpaRepository<RegisterEM, Long> {
  Optional<RegisterEM> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEMSN(String EMSN);
}
