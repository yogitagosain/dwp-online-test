package com.dwp.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class CustomApiError {

    @JsonProperty("status")
    private final HttpStatus status;

    @JsonProperty("message")
    private final String message;

    @JsonProperty("errorDetails")
    private final String errorDetails;

}
