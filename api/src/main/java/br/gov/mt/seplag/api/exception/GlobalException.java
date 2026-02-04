package br.gov.mt.seplag.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(InvalidCredentialException.class)
    public ResponseEntity<Map<String, Object>> exception(InvalidCredentialException invalidCredentialException) {
        return buildResponse(HttpStatus.UNAUTHORIZED, invalidCredentialException.getMessage());
    }

    private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus httpStatus, String message) {
        Map<String, Object> objeto = Map.of("Data Requisição", Instant.now(), "Erro", message);
        return ResponseEntity.status(httpStatus).body(objeto);
    }

}
