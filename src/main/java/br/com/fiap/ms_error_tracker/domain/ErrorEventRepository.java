package br.com.fiap.ms_error_tracker.domain;

import java.util.List;

public interface ErrorEventRepository {

    ErrorEvent save(ErrorEvent event);

    List<ErrorEvent> findAll();
    // opcional: Optional<ErrorEvent> findById(Long id);
}
