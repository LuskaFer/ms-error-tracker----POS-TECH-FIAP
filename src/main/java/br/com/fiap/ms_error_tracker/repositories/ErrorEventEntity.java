package br.com.fiap.ms_error_tracker.repositories;

import jakarta.persistence.*;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "error_event")
public class ErrorEventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "occurred_at", nullable = false)
    private Instant occurredAt;

    @Column(nullable = false, length = 1000)
    private String message;

    @Column(columnDefinition = "TEXT")
    private String details;

    @Column(length = 255)
    private String origin;

    @Column(nullable = false)
    private boolean processed;
}
