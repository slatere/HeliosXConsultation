package com.slatere.heliosx.response;

public class ConsultationNotFoundResponse extends ErrorResponse {
    public ConsultationNotFoundResponse(int status, long time, String message) {
        super(status, time, message);
    }
}
