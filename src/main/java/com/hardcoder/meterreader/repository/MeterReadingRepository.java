package com.hardcoder.meterreader.repository;

import com.hardcoder.meterreader.models.Meter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeterReadingRepository extends JpaRepository<Meter,Long> {
    Boolean existsByEMSN(String EMSN);
}
