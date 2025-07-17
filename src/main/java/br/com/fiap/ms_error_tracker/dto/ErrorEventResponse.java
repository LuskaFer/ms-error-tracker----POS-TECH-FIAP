package br.com.fiap.ms_error_tracker.dto;

import br.com.fiap.ms_error_tracker.domain.ErrorEvent;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public record ErrorEventResponse(
        Long id,
        Instant occurredAt,
        String message,
        String details,
        String origin,
        boolean processed
) {
    public static ErrorEventResponse fromDomain(ErrorEvent event) {
        return new ErrorEventResponse(
                event.getId(),
                event.getOccurredAt(),
                event.getMessage(),
                event.getDetails(),
                event.getOrigin(),
                event.isProcessed()
        );
    }

    public static List<ErrorEventResponse> fromDomain(List<ErrorEvent> events) {
        return events.stream()
                .map(ErrorEventResponse::fromDomain)
                .collect(Collectors.toList());
    }
}