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

        ErrorEventEntity saved = jpaRepo.save(entity);

        return toDomain(saved);
    }

    @Override
    public List<ErrorEvent> findAll() {
        return jpaRepo.findAll()
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    private ErrorEvent toDomain(ErrorEventEntity e) {
        return ErrorEvent.builder()
                .id(e.getId())
                .occurredAt(e.getOccurredAt())
                .message(e.getMessage())
                .details(e.getDetails())
                .origin(e.getOrigin())
                .processed(e.isProcessed())
                .build();
    }

    private ErrorEventEntity toEntity(ErrorEvent e) {
        return ErrorEventEntity.builder()
                .id(e.getId())
                .occurredAt(e.getOccurredAt())
                .message(e.getMessage())
                .details(e.getDetails())
                .origin(e.getOrigin())
                .processed(e.isProcessed())
                .build();
    }
}
