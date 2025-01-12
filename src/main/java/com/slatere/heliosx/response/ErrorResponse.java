package com.slatere.heliosx.response;

public class ErrorResponse {

    private int status;
    private long time;
    private String message;

    public ErrorResponse(int status, long time, String message) {
        this.status = status;
        this.time = time;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public long getTime() {
        return time;
    }

    public String getMessage() {
        return message;
    }
}