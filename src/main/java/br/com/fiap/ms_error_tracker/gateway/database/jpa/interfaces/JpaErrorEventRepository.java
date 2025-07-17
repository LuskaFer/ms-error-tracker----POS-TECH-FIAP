package br.com.fiap.ms_error_tracker.gateway.database.jpa.interfaces;

import br.com.fiap.ms_error_tracker.repositories.ErrorEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaErrorEventRepository extends JpaRepository<ErrorEventEntity, Long> {
}