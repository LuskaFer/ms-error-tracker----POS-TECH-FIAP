package br.com.fiap.ms_error_tracker.gateway.database.jpa.interfaces;

import br.com.fiap.ms_error_tracker.repositories.ErrorEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaErrorEventRepository extends JpaRepository<ErrorEventEntity, Long> {
}
