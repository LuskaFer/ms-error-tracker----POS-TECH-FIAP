package br.com.fiap.ms_error_tracker.controller;

import br.com.fiap.ms_error_tracker.dto.ErrorEventResponse;
import br.com.fiap.ms_error_tracker.usecase.ListErrorEventsUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/errors")
public class ErrorController {

    private final ListErrorEventsUseCase listUseCase;

    public ErrorController(ListErrorEventsUseCase listUseCase) {
        this.listUseCase = listUseCase;
    }

    @GetMapping
    public List<ErrorEventResponse> getAllErrors() {
        return listUseCase.listAll()
                .stream()
                .map(ErrorEventResponse::fromDomain)
                .collect(Collectors.toList());
    }
}