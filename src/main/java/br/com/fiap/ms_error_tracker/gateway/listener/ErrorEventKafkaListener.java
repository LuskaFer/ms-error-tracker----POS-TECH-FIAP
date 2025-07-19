package br.com.fiap.ms_error_tracker.gateway.listener;

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fiap.ms_error_tracker.domain.ErrorEvent;
import br.com.fiap.ms_error_tracker.dto.ErrorRecordDTO;
import br.com.fiap.ms_error_tracker.usecase.RegisterErrorEventUseCase;

@Component
public class ErrorEventKafkaListener {

    private static final Logger log = LoggerFactory.getLogger(ErrorEventKafkaListener.class);
    private final RegisterErrorEventUseCase registerUseCase;
    private final ObjectMapper mapper;

    public ErrorEventKafkaListener(RegisterErrorEventUseCase registerUseCase,
            ObjectMapper mapper) {
        this.registerUseCase = registerUseCase;
        this.mapper = mapper;
    }

    @KafkaListener(
            topics = "${kafka-error-topic}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void onMessage(String json) {
        try {
            // 1) desserializa no DTO que tem record + error
            ErrorRecordDTO dto = mapper.readValue(json, ErrorRecordDTO.class);

            // 2) monta o evento
            ErrorEvent event = ErrorEvent.builder()
                    .occurredAt(Instant.now())
                    .message(dto.error() != null && !dto.error().isBlank()
                            ? dto.error()
                            : "<<mensagem de erro não informada>>")
                    .details(dto.record() != null
                            ? dto.record()
                            : "<<conteúdo do registro não informado>>")
                    .origin("FileProcessor")
                    .processed(false)
                    .build();

            // 3) persiste
            registerUseCase.register(event);

        } catch (Exception ex) {
            log.error("Erro ao processar evento de erro: {}", ex.getMessage(), ex);
        }
    }
}
