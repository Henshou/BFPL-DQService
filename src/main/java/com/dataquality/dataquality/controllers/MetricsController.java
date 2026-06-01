package com.dataquality.dataquality.controllers;

import com.dataquality.dataquality.entity.AggregatedMetricEntity;
import com.dataquality.dataquality.repository.AggregatedMetricRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/metrics")
@Tag(name = "Metrics", description = "Telemetry Metrics API")
public class MetricsController {

    private final AggregatedMetricRepository repository;

    public MetricsController(
            AggregatedMetricRepository repository
    ) {
        this.repository = repository;
    }

    @GetMapping
    @Operation(summary = "Get all aggregated metrics")
    public List<AggregatedMetricEntity> getAllMetrics() {
        return repository.findAll();
    }

    @GetMapping("/batch/{batchId}")
    @Operation(summary = "Get metrics by batch ID")
    public List<AggregatedMetricEntity> getByBatch(
            @PathVariable String batchId
    ) {
        return repository.findByBatchId(batchId);
    }

    @GetMapping("/service/{serviceName}")
    @Operation(summary = "Get metrics by service name")
    public List<AggregatedMetricEntity> getByService(
            @PathVariable String serviceName
    ) {
        return repository.findByServiceName(serviceName);
    }
}