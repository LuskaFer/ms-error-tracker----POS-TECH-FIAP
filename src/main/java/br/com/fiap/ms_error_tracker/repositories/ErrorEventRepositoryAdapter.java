package br.com.fiap.ms_error_tracker.repositories;

import br.com.fiap.ms_error_tracker.domain.ErrorEvent;
import br.com.fiap.ms_error_tracker.domain.ErrorEventRepository;
import br.com.fiap.ms_error_tracker.gateway.database.jpa.interfaces.JpaErrorEventRepository;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ErrorEventRepositoryAdapter implements ErrorEventRepository {

    private final JpaErrorEventRepository jpaRepo;

    public ErrorEventRepositoryAdapter(JpaErrorEventRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    @Override
    public ErrorEvent save(ErrorEvent event) {
        ErrorEventEntity entity = toEntity(event);
        entity = jpaRepo.save(entity);
        return toDomain(entity);
    }

    @Override
    public List<ErrorEvent> findAll() {
        return jpaRepo.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    // métodos de conversão
    private ErrorEvent toDomain(ErrorEventEntity e) {
        /* ... */ }

    private ErrorEventEntity toEntity(ErrorEvent e) {
        /* ... */ }
}
