package br.com.fiap.ms_error_tracker.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ErrorRecordDTO(
        @JsonProperty("record")
        String record,
        @JsonProperty("error")
        String error
        ) {

}
