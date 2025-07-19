package br.com.fiap.ms_error_tracker.gateway.listener;

import br.com.fiap.ms_error_tracker.domain.ErrorEvent;
import br.com.fiap.ms_error_tracker.usecase.RegisterErrorEventUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ErrorEventKafkaListenerTest {

    private RegisterErrorEventUseCase registerUseCase;
    private ObjectMapper mapper;
    private ErrorEventKafkaListener listener;

    @BeforeEach
    void setUp() {
        registerUseCase = mock(RegisterErrorEventUseCase.class);
        mapper = new ObjectMapper();
        listener = new ErrorEventKafkaListener(registerUseCase, mapper);
    }

    @Test
    void onMessage_ValidJson_CallsRegister() throws Exception {
        String json = """
            {
              "record": "meu-registro-xyz",
              "error": "minha mensagem de erro"
            }
            """;

        listener.onMessage(json);

        ArgumentCaptor<ErrorEvent> captor = ArgumentCaptor.forClass(ErrorEvent.class);
        verify(registerUseCase).register(captor.capture());
        ErrorEvent event = captor.getValue();

        assertEquals("minha mensagem de erro", event.getMessage());
        assertEquals("meu-registro-xyz", event.getDetails());
        assertEquals("FileProcessor", event.getOrigin());
        assertFalse(event.isProcessed());
        assertNotNull(event.getOccurredAt());
    }

}
