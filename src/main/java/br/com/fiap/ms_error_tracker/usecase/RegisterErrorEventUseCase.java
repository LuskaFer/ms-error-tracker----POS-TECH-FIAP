package br.com.fiap.ms_error_tracker.usecase;

import br.com.fiap.ms_error_tracker.domain.ErrorEvent;
import br.com.fiap.ms_error_tracker.domain.ErrorEventRepository;
import org.springframework.stereotype.Service;

@Service
public class RegisterErrorEventUseCase {

    private final ErrorEventRepository repository;

    public RegisterErrorEventUseCase(ErrorEventRepository repository) {
        this.repository = repository;
    }


    // É aqui o usecase que regstra o evento no banco

    public ErrorEvent register(ErrorEvent event) {
        return repository.save(event);
    }
}
