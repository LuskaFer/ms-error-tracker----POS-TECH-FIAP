package br.com.fiap.ms_error_tracker.domain;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorEvent {
    private Long id;
    private Instant occurredAt;
    private String message;
    private String details;
    private String origin;
    @Builder.Default
    private boolean processed = false;
}
