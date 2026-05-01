package com.dataquality.dataquality.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "telemetry_events",
       indexes = {
                @Index(name = "idx_record_id", columnList = "recordId"),
                @Index(name = "idx_batch_id", columnList = "batchId"),
                @Index(name = "idx_metric_name", columnList = "metricName"),
                @Index(name = "idx_event_timestamp", columnList = "eventTimestamp")
       })
public class TelemetryEventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String recordId;

    private String batchId;

    private String fileName;

    private String serviceName;

    private String metricName;

    private Long metricValue;

    private Long eventTimestamp;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getMetricName() {
        return metricName;
    }

    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }

    public Long getMetricValue() {
        return metricValue;
    }

    public void setMetricValue(Long metricValue) {
        this.metricValue = metricValue;
    }

    public Long getEventTimestamp() {
        return eventTimestamp;
    }

    public void setEventTimestamp(Long eventTimestamp) {
        this.eventTimestamp = eventTimestamp;
    }
}
