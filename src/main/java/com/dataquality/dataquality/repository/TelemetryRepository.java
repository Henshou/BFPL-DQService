package com.dataquality.dataquality.repository;

import com.dataquality.dataquality.entity.TelemetryEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelemetryRepository
        extends JpaRepository<
                TelemetryEventEntity,
                Long> {
}
