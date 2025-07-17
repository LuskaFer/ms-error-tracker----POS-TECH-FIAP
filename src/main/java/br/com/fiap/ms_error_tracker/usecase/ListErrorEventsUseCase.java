package br.com.fiap.ms_error_tracker.usecase;

import br.com.fiap.ms_error_tracker.domain.ErrorEvent;
import br.com.fiap.ms_error_tracker.domain.ErrorEventRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ListErrorEventsUseCase {

    private final ErrorEventRepository repository;

    public ListErrorEventsUseCase(ErrorEventRepository repository) {
        this.repository = repository;
    }


     //Aqui retorna a listagem dos eventos de erro registrados pelo RegisterError.

    public List<ErrorEvent> listAll() {
        return repository.findAll();
    }
}
