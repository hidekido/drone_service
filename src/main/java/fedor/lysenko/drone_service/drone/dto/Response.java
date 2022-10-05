package fedor.lysenko.drone_service.drone.dto;

import java.time.LocalDateTime;

public class Response {

    private String status = "Success";
    private LocalDateTime timestamp = LocalDateTime.now();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
