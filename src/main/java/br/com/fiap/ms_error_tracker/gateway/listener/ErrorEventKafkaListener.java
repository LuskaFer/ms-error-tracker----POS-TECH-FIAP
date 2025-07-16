package br.com.fiap.ms_error_tracker.gateway.listener;

import br.com.fiap.ms_error_tracker.domain.ErrorEvent;
import br.com.fiap.ms_error_tracker.usecase.RegisterErrorEventUseCase;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ErrorEventKafkaListener {

    private final RegisterErrorEventUseCase registerUseCase;
    private final ObjectMapper mapper;

    public ErrorEventKafkaListener(RegisterErrorEventUseCase useCase,
                                   ObjectMapper mapper) {
        this.registerUseCase = useCase;
        this.mapper = mapper;
    }

    @KafkaListener(topics = "${kafka.topic.error:fhir-error-topic}")
    public void onMessage(String json) {
        try {
            ErrorEvent event = mapper.readValue(json, ErrorEvent.class);
            registerUseCase.register(event);
        } catch (Exception e) {
            // log de falha ao desserializar ou salvar
        }
    }
}