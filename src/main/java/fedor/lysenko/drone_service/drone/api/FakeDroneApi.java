package fedor.lysenko.drone_service.drone.api;

import fedor.lysenko.drone_service.drone.entity.Drone;
import fedor.lysenko.drone_service.drone.entity.Medication;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class FakeDroneApi implements DroneApi{

    @Override
    public boolean load(Medication medication){
        /*
        loading logic
         */
        return true;
    }

    @Override
    public byte getBattery(Drone drone) {
        if(drone.getBatteryCapacity() <= 10) return 100;
        else return (byte)(drone.getBatteryCapacity() - 1);
    }

    @Override
    public byte getBatteryNew(String serialNumber) {
        return (byte)ThreadLocalRandom.current().nextInt(0, 100);
    }
}
