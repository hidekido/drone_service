package fedor.lysenko.drone_service.drone.exception;

public class DroneOverloadedException extends RuntimeException{

    public DroneOverloadedException(String message) {
        super(message);
    }
}
