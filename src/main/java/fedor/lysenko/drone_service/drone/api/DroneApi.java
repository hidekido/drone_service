package fedor.lysenko.drone_service.drone.api;

import fedor.lysenko.drone_service.drone.entity.Drone;
import fedor.lysenko.drone_service.drone.entity.Medication;

public interface DroneApi {

    boolean load(Medication medication);

    byte getBattery(Drone drone);

    byte getBatteryNew(String serialNumber);
}
