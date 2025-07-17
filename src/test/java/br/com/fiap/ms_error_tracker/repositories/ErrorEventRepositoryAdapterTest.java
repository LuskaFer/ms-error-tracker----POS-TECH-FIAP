package br.com.fiap.ms_error_tracker.repositories;

import br.com.fiap.ms_error_tracker.domain.ErrorEvent;
import br.com.fiap.ms_error_tracker.gateway.database.jpa.interfaces.JpaErrorEventRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(ErrorEventRepositoryAdapter.class)
class ErrorEventRepositoryAdapterTest {

    @Autowired
    private ErrorEventRepositoryAdapter adapter;


    @Autowired
    private JpaErrorEventRepository jpaRepo;

    @Test
    @DisplayName("deve salvar e recuperar um ErrorEvent")
    void saveAndFindAll() {
        ErrorEvent evt = ErrorEvent.builder()
                .occurredAt(Instant.parse("2025-07-17T15:00:00Z"))
                .message("Teste de erro")
                .details("Detalhes do teste")
                .origin("unittest")
                .build();


        ErrorEvent saved = adapter.save(evt);
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getMessage()).isEqualTo("Teste de erro");


        List<ErrorEvent> all = adapter.findAll();
        assertThat(all).hasSize(1);
        assertThat(all.get(0).getOrigin()).isEqualTo("unittest");
    }
}
