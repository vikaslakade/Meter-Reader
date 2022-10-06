package com.hardcoder.meterreader.repository;

import java.util.Optional;

import com.hardcoder.meterreader.models.ERole;
import com.hardcoder.meterreader.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
