package com.hardcoder.meterreader.repository;

import com.hardcoder.meterreader.models.MeterStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeterStatusRepository extends JpaRepository<MeterStatus,Long> {
    Boolean existsByEMSN(String EMSN);
    MeterStatus findByEMSN(String eMSN);
}
