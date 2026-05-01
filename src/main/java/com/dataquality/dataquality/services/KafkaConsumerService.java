package com.dataquality.dataquality.services;

import com.dataquality.dataquality.entity.TelemetryEventEntity;
import com.dataquality.dataquality.model.TelemetryEvent;
import com.dataquality.dataquality.repository.TelemetryRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
public class KafkaConsumerService {

    private final ObjectMapper objectMapper;
    private final TelemetryRepository telemetryRepository;

    public KafkaConsumerService(
            ObjectMapper objectMapper,
            TelemetryRepository telemetryRepository
    ) {
        this.objectMapper = objectMapper;
        this.telemetryRepository = telemetryRepository;
    }

    @KafkaListener(
            topics = "dq-metrics-topic",
            groupId = "dq-group"
    )
    public void consumeTelemetry(
            String message
    ) {

        System.out.println("RECEIVED TELEMETRY: " + message);

        try {

            TelemetryEvent event =
                    objectMapper.readValue(
                            message,
                            TelemetryEvent.class
                    );

            TelemetryEventEntity entity =
                    new TelemetryEventEntity();

            entity.setRecordId(
                    event.getRecordId()
            );

            entity.setBatchId(
                    event.getBatchId()
            );

            entity.setFileName(
                    event.getFileName()
            );

            entity.setServiceName(
                    event.getServiceName()
            );

            entity.setMetricName(
                    event.getMetricName()
            );

            entity.setMetricValue(
                    event.getMetricValue()
            );

            entity.setEventTimestamp(
                    event.getEventTimestamp()
            );

            telemetryRepository.save(entity);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}