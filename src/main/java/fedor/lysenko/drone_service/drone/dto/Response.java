package fedor.lysenko.drone_service.drone.dto;

import java.time.LocalDateTime;

public class Response {

    public enum Status{
        SUCCESS,
        FAILED
    }

    private Status status;
    private LocalDateTime timestamp = LocalDateTime.now();

    private String message;

    public Response(Status status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
