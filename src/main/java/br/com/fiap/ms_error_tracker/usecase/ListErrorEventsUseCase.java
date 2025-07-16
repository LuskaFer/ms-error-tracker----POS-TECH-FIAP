package br.com.fiap.ms_error_tracker.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.fiap.ms_error_tracker.domain.ErrorEvent;
import br.com.fiap.ms_error_tracker.domain.ErrorEventRepository;

@Service
public class ListErrorEventsUseCase {

    private final ErrorEventRepository repository;

    public ListErrorEventsUseCase(ErrorEventRepository repository) {
        this.repository = repository;
    }

    public List<ErrorEvent> listAll() {
        return repository.findAll();
    }
}