package fedor.lysenko.drone_service.drone.scheduled;


import fedor.lysenko.drone_service.drone.DroneService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@AllArgsConstructor
public class ScheduledDroneBatteryCheck {
    private static final Logger log = LoggerFactory.getLogger(ScheduledDroneBatteryCheck.class);

    DroneService droneService;

    @Scheduled(fixedRate = 10000)
    public void checkDronesBattery(){
        Map<String, Byte> dronesBattery = droneService.checkDronesBattery();
        for(Map.Entry<String, Byte> entry : dronesBattery.entrySet()){
            String info = "Drone #" + entry.getKey() + " has " + entry.getValue() + "% battery!";
            log.info(info);
        }
    }

}
