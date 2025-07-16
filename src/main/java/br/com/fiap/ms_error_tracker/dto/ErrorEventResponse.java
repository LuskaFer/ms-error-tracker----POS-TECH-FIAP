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
        String origin
        ) {

    public static List<ErrorEventResponse> fromDomain(List<ErrorEvent> list) {
        return list.stream()
                .map(e -> new ErrorEventResponse(
                e.getId(), e.getOccurredAt(), e.getMessage(), e.getDetails(), e.getOrigin()
        ))
                .collect(Collectors.toList());
    }
}
