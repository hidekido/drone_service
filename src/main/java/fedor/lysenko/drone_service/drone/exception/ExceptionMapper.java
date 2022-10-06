package fedor.lysenko.drone_service.drone.exception;

import fedor.lysenko.drone_service.drone.dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionMapper {

    @ExceptionHandler({ DroneOverloadedException.class,
            DroneNotEnoughBatteryForLoadException.class })
    public ResponseEntity<Response> handleDroneUnprocessable(RuntimeException exception){
        return new ResponseEntity<>(new Response(Response.Status.FAILED, exception.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler({ DroneNotFoundException.class})
    public ResponseEntity<Response> handleDroneNotFound(DroneNotFoundException exception){
        return new ResponseEntity<>(new Response(Response.Status.FAILED, exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ DroneAlreadyRegisteredException.class})
    public ResponseEntity<Response> handleDroneAlreadyRegistered(RuntimeException exception){
        return new ResponseEntity<>(new Response(Response.Status.FAILED, exception.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({ MedicationNotFoundException.class})
    public ResponseEntity<Response> handleMedicationNotFound(RuntimeException exception){
        return new ResponseEntity<>(new Response(Response.Status.SUCCESS, exception.getMessage()), HttpStatus.NO_CONTENT);
    }

}
