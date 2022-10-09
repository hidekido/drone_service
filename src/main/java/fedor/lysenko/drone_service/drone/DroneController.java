package fedor.lysenko.drone_service.drone;

import fedor.lysenko.drone_service.drone.dto.DroneRegisterRequest;
import fedor.lysenko.drone_service.drone.dto.MedicationLoadRequest;
import fedor.lysenko.drone_service.drone.entity.Drone;
import fedor.lysenko.drone_service.drone.entity.Medication;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1")
@Validated
public class DroneController {
    private final DroneService droneService;

    @GetMapping("/drones/{serialNumber}")
    public Drone getDrone(@PathVariable @Valid @NotNull String serialNumber){
        return droneService.getDroneBySerialNumber(serialNumber);
    }

    @GetMapping("/drones")
    public List<Drone> getAllDrones(){
        return droneService.getAllDrones();
    }

    @GetMapping("/drones/available")
    @ResponseStatus(HttpStatus.OK)
    public List<Drone> getAllDronesAvailableForLoad(){
        return droneService.getAllDronesAvailableForLoad();
    }

    @PostMapping(path = "/drones")
    @ResponseStatus(HttpStatus.CREATED)
    public void createDrone(@RequestBody @Valid @NotNull DroneRegisterRequest drone){
        droneService.createDrone(drone);
    }

    @PostMapping("/medications")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void loadMedication(@RequestBody @Valid @NotNull MedicationLoadRequest request){
        droneService.loadDrone(request);
    }

    @GetMapping("/medications/{serialNumber}")
    @ResponseStatus(HttpStatus.OK)
    public List<Medication> findAllMedicationsLoadedByDroneBySerialNumber(@PathVariable String serialNumber){
        return droneService.findAllMedicationsLoadedByDroneBySerialNumber(serialNumber);
    }

    @PostMapping("/test")
    @ResponseStatus(HttpStatus.CREATED)
    public void generateTestData(){
        droneService.generateTestData();
    }
}
