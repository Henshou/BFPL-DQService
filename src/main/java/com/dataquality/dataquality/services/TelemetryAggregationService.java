package com.dataquality.dataquality.services;

import com.dataquality.dataquality.entity.AggregatedMetricEntity;
import com.dataquality.dataquality.model.MetricsAggregate;
import com.dataquality.dataquality.model.TelemetryEvent;
import com.dataquality.dataquality.repository.AggregatedMetricRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TelemetryAggregationService {

    private final AggregatedMetricRepository repository;

    private final Map<String, MetricsAggregate> aggregateBuffer =
            new ConcurrentHashMap<>();

    private long currentWindowStart =
            System.currentTimeMillis();

    public TelemetryAggregationService(
            AggregatedMetricRepository repository
    ) {
        this.repository = repository;
    }

    public void processTelemetry(
            TelemetryEvent event
    ) {

        String key =
                event.getBatchId()
                        + "|"
                        + event.getServiceName()
                        + "|"
                        + event.getMetricName();

        aggregateBuffer.computeIfAbsent(
                key,
                k -> new MetricsAggregate()
        ).addValue(
                event.getMetricValue()
        );
    }

    @Scheduled(fixedRate = 5000)
    public synchronized void flushAggregates() {

        long windowEnd =
                System.currentTimeMillis();

        for (Map.Entry<String, MetricsAggregate> entry :
                aggregateBuffer.entrySet()) {

            String[] parts =
                    entry.getKey().split("\\|");

            MetricsAggregate aggregate =
                    entry.getValue();

            AggregatedMetricEntity entity =
                    new AggregatedMetricEntity();

            entity.setBatchId(parts[0]);
            entity.setServiceName(parts[1]);
            entity.setMetricName(parts[2]);

            entity.setCountValue(
                    aggregate.getCount()
            );

            entity.setAverageValue(
                    aggregate.getAverage()
            );

            entity.setMinValue(
                    aggregate.getMin()
            );

            entity.setMaxValue(
                    aggregate.getMax()
            );

            entity.setWindowStart(
                    currentWindowStart
            );

            entity.setWindowEnd(
                    windowEnd
            );

            repository.save(entity);
        }

        aggregateBuffer.clear();

        currentWindowStart =
                System.currentTimeMillis();
    }
}