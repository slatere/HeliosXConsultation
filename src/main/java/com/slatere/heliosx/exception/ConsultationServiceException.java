package com.slatere.heliosx.exception;

public class ConsultationServiceException extends RuntimeException {
    public ConsultationServiceException(String message) {
        super(message);
    }

    public ConsultationServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConsultationServiceException(Throwable cause) {
        super(cause);
    }
}
