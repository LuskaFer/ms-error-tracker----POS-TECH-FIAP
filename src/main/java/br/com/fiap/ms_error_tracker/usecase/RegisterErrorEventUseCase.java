package br.com.fiap.ms_error_tracker.usecase;

import org.springframework.stereotype.Service;

import br.com.fiap.ms_error_tracker.domain.ErrorEvent;
import br.com.fiap.ms_error_tracker.domain.ErrorEventRepository;

@Service
public class RegisterErrorEventUseCase {

    private final ErrorEventRepository repository;

    public RegisterErrorEventUseCase(ErrorEventRepository repository) {
        this.repository = repository;
    }

    public void register(ErrorEvent event) {
        repository.save(event);
    }
}
