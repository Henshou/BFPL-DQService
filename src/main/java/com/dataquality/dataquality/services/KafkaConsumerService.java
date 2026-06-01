package com.dataquality.dataquality.services;

import com.dataquality.dataquality.model.TelemetryEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
public class KafkaConsumerService {

    private final ObjectMapper objectMapper;
    private final TelemetryAggregationService aggregationService;

    public KafkaConsumerService(
            ObjectMapper objectMapper,
            TelemetryAggregationService aggregationService
    ) {
        this.objectMapper = objectMapper;
        this.aggregationService = aggregationService;
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

            aggregationService.processTelemetry(event);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}