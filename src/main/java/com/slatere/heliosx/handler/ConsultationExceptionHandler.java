package com.slatere.heliosx.handler;

import com.slatere.heliosx.exception.ConsultationNotFoundException;
import com.slatere.heliosx.exception.ConsultationServiceException;
import com.slatere.heliosx.response.ConsultationNotFoundResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.Set;

@ControllerAdvice
public class ConsultationExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handle(ConstraintViolationException constraintViolationException) {
        Set<ConstraintViolation<?>> violations = constraintViolationException.getConstraintViolations();
        String errorMessage = "";
        if (!violations.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            violations.forEach(violation -> builder.append(" " + violation.getMessage()));
            errorMessage = builder.toString();
        } else {
            errorMessage = "ConstraintViolationException occurred.";
        }
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConsultationServiceException.class)
    public ResponseEntity handle(ConsultationServiceException consultationServiceException) {
        return new ResponseEntity<>(consultationServiceException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConsultationNotFoundException.class)
    public ResponseEntity handle(ConsultationNotFoundException consultationNotFoundException) {
        ConsultationNotFoundResponse consultationNotFoundResponse = new ConsultationNotFoundResponse(
                HttpStatus.NOT_FOUND.value(), Instant.now().toEpochMilli(), consultationNotFoundException.getMessage()
        );
        return new ResponseEntity(consultationNotFoundResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handle(Exception exception) {
        return new ResponseEntity<>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
