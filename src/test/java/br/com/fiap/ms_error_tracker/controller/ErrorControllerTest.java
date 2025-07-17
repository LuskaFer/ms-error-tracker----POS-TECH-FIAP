package br.com.fiap.ms_error_tracker.controller;

import br.com.fiap.ms_error_tracker.domain.ErrorEvent;
import br.com.fiap.ms_error_tracker.usecase.ListErrorEventsUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ErrorController.class)
class ErrorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ListErrorEventsUseCase listUseCase;

    @Test
    void getAllErrors_ReturnsList() throws Exception {
        ErrorEvent e1 = ErrorEvent.builder()
                .id(1L)
                .occurredAt(Instant.parse("2025-07-17T10:00:00Z"))
                .message("Erro1")
                .details("Detalhes1")
                .origin("svc1")
                .processed(false)
                .build();

        when(listUseCase.listAll()).thenReturn(List.of(e1));

        mockMvc.perform(get("/errors")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].message").value("Erro1"))
                .andExpect(jsonPath("$[0].origin").value("svc1"));
    }
}
