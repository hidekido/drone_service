package fedor.lysenko.drone_service.drone;

import fedor.lysenko.drone_service.drone.entity.Drone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/drones")
public class DroneController {

    private final DroneService droneService;

    @Autowired
    public DroneController(DroneService droneService) {
        this.droneService = droneService;
    }

    @GetMapping("/{serialNumber}")
    public Drone getDrone(@PathVariable  String serialNumber){
        return droneService.getDroneBySerialNumber(serialNumber);
    }

    @GetMapping
    public List<Drone> getAllDrones(){
        return droneService.getAllDrones();
    }

    @PostMapping
    public void createDrone(@RequestBody Drone drone){
        droneService.createDrone(drone);
    }

    @PostMapping("/all")
    public void createAllDrones(@RequestBody List<Drone> drone){
        droneService.createDrone(drone);
    }
}
