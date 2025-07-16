package br.com.fiap.ms_error_tracker.controller;

import br.com.fiap.ms_error_tracker.dto.ErrorEventResponse;
import br.com.fiap.ms_error_tracker.usecase.ListErrorEventsUseCase;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/errors")
public class ErrorController {

    private final ListErrorEventsUseCase listUseCase;

    public ErrorController(ListErrorEventsUseCase listUseCase) {
        this.listUseCase = listUseCase;
    }

    @GetMapping
    public List<ErrorEventResponse> getAllErrors() {
        return ErrorEventResponse.fromDomain(listUseCase.listAll());
    }
}