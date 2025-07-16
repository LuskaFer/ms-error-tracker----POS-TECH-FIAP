package br.com.fiap.ms_error_tracker.repositories;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "error_event")
public class ErrorEventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant occurredAt;
    private String message;
    @Column(columnDefinition = "TEXT")
    private String details;
    private String origin;
    private boolean processed;

    // getters e setters
}
