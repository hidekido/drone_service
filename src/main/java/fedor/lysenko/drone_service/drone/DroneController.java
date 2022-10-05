package fedor.lysenko.drone_service.drone;

import fedor.lysenko.drone_service.drone.dto.LoadDroneResponse;
import fedor.lysenko.drone_service.drone.entity.Drone;
import fedor.lysenko.drone_service.drone.entity.Medication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/drones")
public class DroneController {



    private final DroneService droneService;

    @Autowired
    public DroneController(DroneService droneService) {
        System.out.println("Инит контроллера");
        this.droneService = droneService;
    }

    @GetMapping("/info/{serialNumber}")
    public Drone getDrone(@PathVariable  String serialNumber){
        return droneService.getDroneBySerialNumber(serialNumber);
    }

    @GetMapping("/info")
    public List<Drone> getAllDrones(){
        return droneService.getAllDrones();
    }

    //todo все хуйня, переделать эндпоинты
    @PostMapping("/actions/new")
    public void createDrone(@RequestBody Drone drone){
        droneService.createDrone(drone);
    }

    @PostMapping("/actions/new-all")
    public void createAllDrones(@RequestBody List<Drone> drone){
        droneService.createDrone(drone);
    }

    @PostMapping("/actions/load")
    public ResponseEntity<LoadDroneResponse> loadMedication(@RequestBody Medication newMedication){
        droneService.loadDrone(newMedication);
        LoadDroneResponse loadDroneResponse = new LoadDroneResponse();
        return new ResponseEntity<>(loadDroneResponse, HttpStatus.CREATED);
    }
}
