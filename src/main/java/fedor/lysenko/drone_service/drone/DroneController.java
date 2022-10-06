package fedor.lysenko.drone_service.drone;

import fedor.lysenko.drone_service.drone.dto.MedicationLoadRequest;
import fedor.lysenko.drone_service.drone.entity.Drone;
import fedor.lysenko.drone_service.drone.entity.Medication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/drones")
public class DroneController {



    private final DroneService droneService;

    @Autowired
    public DroneController(DroneService droneService) {
        this.droneService = droneService;
    }

    @GetMapping("/drones/{serialNumber}")
    public Drone getDrone(@PathVariable  String serialNumber){
        return droneService.getDroneBySerialNumber(serialNumber);
    }

    @GetMapping("/drones")
    public List<Drone> getAllDrones(){
        return droneService.getAllDrones();
    }

    //todo все хуйня, переделать эндпоинты
    @PostMapping("/drones")
    @ResponseStatus(HttpStatus.CREATED)
    public void createDrone(@RequestBody Drone drone){
        droneService.createDrone(drone);
    }

    @PostMapping("/medications")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void loadMedication(@RequestBody MedicationLoadRequest request){
        droneService.loadDrone(request);
    }

    @GetMapping("/medications/{serialNumber}")
    @ResponseStatus(HttpStatus.OK)
    public List<Medication> findAllMedicationsLoadedByDroneBySerialNumber(@PathVariable String serialNumber){
        return droneService.findAllMedicationsLoadedByDroneBySerialNumber(serialNumber);
    }
}
