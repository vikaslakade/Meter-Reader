package com.hardcoder.meterreader.repository;

import com.hardcoder.meterreader.models.Reading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadingRepository extends JpaRepository<Reading,Long> {
}
