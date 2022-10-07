package fedor.lysenko.drone_service.drone.api;

import fedor.lysenko.drone_service.drone.entity.Medication;
import org.springframework.stereotype.Component;

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
    public byte getBattery(String droneSerialNumber) {
        return 100;
    }
}
