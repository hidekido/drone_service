package fedor.lysenko.drone_service.drone.exception;

public class DroneAlreadyRegisteredException extends RuntimeException {
    public DroneAlreadyRegisteredException(String message) {
        super(message);
    }
}
