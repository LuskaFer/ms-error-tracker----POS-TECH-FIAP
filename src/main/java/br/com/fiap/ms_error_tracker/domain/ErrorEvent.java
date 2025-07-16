package br.com.fiap.ms_error_tracker.domain;

import java.time.Instant;

public class ErrorEvent {

    private Long id;
    private Instant occurredAt;
    private String message;
    private String details;
    private String origin;
    private boolean processed;

    // construtores, getters e setters
}
