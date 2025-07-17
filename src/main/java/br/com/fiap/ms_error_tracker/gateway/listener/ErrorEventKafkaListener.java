package br.com.fiap.ms_error_tracker.gateway.listener;

import br.com.fiap.ms_error_tracker.domain.ErrorEvent;
import br.com.fiap.ms_error_tracker.usecase.RegisterErrorEventUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import java.time.Instant;

@Component
public class ErrorEventKafkaListener {

    private final RegisterErrorEventUseCase registerUseCase;
    private final ObjectMapper mapper;

    public ErrorEventKafkaListener(RegisterErrorEventUseCase registerUseCase,
                                   ObjectMapper mapper) {
        this.registerUseCase = registerUseCase;
        this.mapper = mapper;
    }

    @KafkaListener(
            topics = "${kafka-error-topic}",
            groupId = "${KAFKA_CONSUMER_GROUP:ms-error-tracker-group}"
    )
    public void onMessage(String json) {
        try {

            ErrorEvent event = mapper.readValue(json, ErrorEvent.class);

            if (event.getOccurredAt() == null) {
                event.setOccurredAt(Instant.now());
            }

            registerUseCase.register(event);

        } catch (Exception ex) {

            System.err.println("Erro ao processar evento de erro: " + ex.getMessage());
        }
    }
}
