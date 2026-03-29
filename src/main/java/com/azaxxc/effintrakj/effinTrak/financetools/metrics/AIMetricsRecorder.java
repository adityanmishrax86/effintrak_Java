package com.azaxxc.effintrakj.effinTrak.financetools.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class AIMetricsRecorder {

    private final MeterRegistry meterRegistry;

    public AIMetricsRecorder(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    public void recordExecution(String model, String operation, boolean success, String errorCode, long durationNanos) {
        String safeModel = normalize(model, "unknown");
        String safeOperation = normalize(operation, "unknown");
        String safeErrorCode = normalize(errorCode, "none");

        Counter.builder("ai_requests_total")
                .tag("model", safeModel)
                .tag("operation", safeOperation)
                .tag("success", String.valueOf(success))
                .tag("error_code", safeErrorCode)
                .register(meterRegistry)
                .increment();

        Timer.builder("ai_request_duration")
                .tag("model", safeModel)
                .tag("operation", safeOperation)
                .tag("success", String.valueOf(success))
                .register(meterRegistry)
                .record(Duration.ofNanos(Math.max(0, durationNanos)));
    }

    private String normalize(String value, String fallback) {
        if (value == null || value.isBlank()) {
            return fallback;
        }
        return value;
    }
}
