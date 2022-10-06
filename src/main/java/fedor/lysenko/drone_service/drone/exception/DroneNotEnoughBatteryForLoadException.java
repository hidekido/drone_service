package fedor.lysenko.drone_service.drone.exception;

public class DroneNotEnoughBatteryForLoadException extends RuntimeException{
    public DroneNotEnoughBatteryForLoadException(String message) {
        super(message);
    }
}
