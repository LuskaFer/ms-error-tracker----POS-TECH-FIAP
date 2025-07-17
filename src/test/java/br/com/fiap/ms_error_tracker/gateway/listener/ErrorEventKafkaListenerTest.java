package br.com.fiap.ms_error_tracker.gateway.listener;

import br.com.fiap.ms_error_tracker.domain.ErrorEvent;
import br.com.fiap.ms_error_tracker.usecase.RegisterErrorEventUseCase;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class ErrorEventKafkaListenerTest {

    @Mock
    private RegisterErrorEventUseCase registerUseCase;

    private ObjectMapper mapper;
    private ErrorEventKafkaListener listener;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())                       // <–– aqui
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)    // <–– e aqui
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        listener = new ErrorEventKafkaListener(registerUseCase, mapper);
    }

    @Test
    void onMessage_ValidJson_CallsRegister() throws Exception {
        String json = "{\"id\":null,"
                + "\"occurredAt\":\"2025-07-17T12:00:00Z\","
                + "\"message\":\"Err\","
                + "\"details\":null,"
                + "\"origin\":\"svc\","
                + "\"processed\":false}";

        listener.onMessage(json);

        ArgumentCaptor<ErrorEvent> captor = ArgumentCaptor.forClass(ErrorEvent.class);
        verify(registerUseCase).register(captor.capture());
        ErrorEvent evt = captor.getValue();
        assertEquals("Err", evt.getMessage());
        assertEquals(Instant.parse("2025-07-17T12:00:00Z"), evt.getOccurredAt());
    }
}
