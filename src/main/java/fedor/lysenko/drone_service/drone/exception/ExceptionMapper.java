package fedor.lysenko.drone_service.drone.exception;

import fedor.lysenko.drone_service.drone.dto.LoadDroneResponse;
import fedor.lysenko.drone_service.drone.dto.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionMapper {

    @ExceptionHandler(value = { DroneOverloadedException.class })
    public ResponseEntity<Response> handleDroneOverloadedException(DroneOverloadedException exception){
        LoadDroneResponse ldr = new LoadDroneResponse();

        return null;
    }
}
