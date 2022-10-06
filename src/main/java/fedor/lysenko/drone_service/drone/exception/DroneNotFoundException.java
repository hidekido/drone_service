package fedor.lysenko.drone_service.drone.exception;

public class DroneNotFoundException extends RuntimeException{
    public DroneNotFoundException(String message) {
        super(message);
    }
}
