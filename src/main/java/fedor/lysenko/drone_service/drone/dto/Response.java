package fedor.lysenko.drone_service.drone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Response {

    public enum Status{
        SUCCESS,
        FAILED
    }

    private Status status;
    private final LocalDateTime timestamp = LocalDateTime.now();
    private String message;

}
