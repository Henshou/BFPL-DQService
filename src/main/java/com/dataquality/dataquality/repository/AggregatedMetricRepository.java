package com.dataquality.dataquality.repository;

import com.dataquality.dataquality.entity.AggregatedMetricEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface AggregatedMetricRepository
        extends JpaRepository<AggregatedMetricEntity, Long> {
    List<AggregatedMetricEntity>
    findByBatchId(String batchId);

    List<AggregatedMetricEntity>
    findByServiceName(String serviceName);

    List<AggregatedMetricEntity>
    findByMetricName(String metricName);
}
