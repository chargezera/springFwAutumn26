package com.example.springfwautumn26;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;
import java.util.TreeMap;

@RestControllerAdvice
public class GlobalExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleNotFound(
            ProductNotFoundException exception,
            WebRequest request
    ) {
        return errorResponse(
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                Map.of(),
                new HttpHeaders(),
                request
        );
    }

    @ExceptionHandler(ProductConflictException.class)
    public ResponseEntity<Object> handleConflict(
            ProductConflictException exception,
            WebRequest request
    ) {
        return errorResponse(
                HttpStatus.CONFLICT,
                exception.getMessage(),
                Map.of(),
                new HttpHeaders(),
                request
        );
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception exception,
            Object body,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        String message = HttpStatus.valueOf(status.value())
                .getReasonPhrase();

        Map<String, String> errors = new TreeMap<>();

        if (exception instanceof MethodArgumentNotValidException validation) {
            message = "Validation failed";

            validation.getBindingResult()
                    .getFieldErrors()
                    .forEach(error -> errors.put(
                            error.getField(),
                            error.getDefaultMessage()
                    ));
        }

        if (status.is5xxServerError()) {
            message = "Internal server error";
            logger.error("Request failed", exception);
        }

        return errorResponse(
                status,
                message,
                errors,
                headers,
                request
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnexpected(
            Exception exception,
            WebRequest request
    ) {
        logger.error("Unexpected request failure", exception);

        return errorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Internal server error",
                Map.of(),
                new HttpHeaders(),
                request
        );
    }

    private ResponseEntity<Object> errorResponse(
            HttpStatusCode status,
            String message,
            Map<String, String> errors,
            HttpHeaders headers,
            WebRequest request
    ) {
        String path = ((ServletWebRequest) request)
                .getRequest()
                .getRequestURI();

        ApiError error = new ApiError(
                status.value(),
                message,
                path,
                errors
        );

        return new ResponseEntity<>(error, headers, status);
    }
}