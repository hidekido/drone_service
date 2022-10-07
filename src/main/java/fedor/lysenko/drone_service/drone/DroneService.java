package fedor.lysenko.drone_service.drone;

import fedor.lysenko.drone_service.drone.api.DroneApi;
import fedor.lysenko.drone_service.drone.configuration.DroneServiceConfiguration;
import fedor.lysenko.drone_service.drone.dao.DroneDAO;
import fedor.lysenko.drone_service.drone.dao.MedicationDAO;
import fedor.lysenko.drone_service.drone.dto.DroneRegisterRequest;
import fedor.lysenko.drone_service.drone.dto.MedicationLoadRequest;
import fedor.lysenko.drone_service.drone.entity.Drone;
import fedor.lysenko.drone_service.drone.entity.Medication;
import fedor.lysenko.drone_service.drone.exception.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@AllArgsConstructor
@Service
public class DroneService {

    private final DroneDAO droneDAO;
    private final MedicationDAO medicationDAO;
    private final DroneServiceConfiguration droneServiceConfiguration;
    private final DroneApi droneApi;

    public List<Drone> getAllDrones(){
        List<Drone> result = new ArrayList<>();
        droneDAO.findAll().forEach(result::add);
        return result;
    }

    public List<Drone> getAllDronesAvailableForLoad(){
        List<Drone> result = new ArrayList<>();
        droneDAO.findAll().forEach(drone -> {
            if(drone.getWeightLoaded() < drone.getWeightLimit() &&
                    drone.getBatteryCapacity() >= droneServiceConfiguration.droneBatteryLimitForLoad){
                result.add(drone);
            }
        });
        return result;
    }

    public Drone getDroneBySerialNumber(String serialNumber){
        Optional<Drone> drone = droneDAO.findById(serialNumber);
        if(drone.isEmpty()){
            String exceptionBuilder = "Drone #" + serialNumber + " not found!";
            throw new DroneNotFoundException(exceptionBuilder);
        }
        return drone.get();
    }

    @Transactional
    public void createDrone(DroneRegisterRequest drone) {
        if(droneDAO.findById(drone.getSerialNumber()).isPresent()){
            String exceptionBuilder = "Drone #" + drone.getSerialNumber() + " already registered!";
            throw new DroneAlreadyRegisteredException(exceptionBuilder);
        }

        byte battery = droneApi.getBattery(drone.getSerialNumber());

        Drone newDrone = Drone.builder().serialNumber(drone.getSerialNumber())
                .weightLimit(drone.getWeightLimit())
                .model(drone.getModel())
                .batteryCapacity(battery)
                .build();

        droneDAO.save(newDrone);
    }

    @Transactional
    public void loadDrone(MedicationLoadRequest newMedicationRequest){
        Optional<Drone> optionalCurrentDrone = droneDAO.findById(newMedicationRequest.getDroneSerialNumber());

        if(optionalCurrentDrone.isEmpty()) {
            String exceptionBuilder = "Drone #" + newMedicationRequest.getDroneSerialNumber() + " not found!";
            throw new DroneNotFoundException(exceptionBuilder);
        }

        Drone currentDrone = optionalCurrentDrone.get();
        if(currentDrone.getBatteryCapacity() < droneServiceConfiguration.droneBatteryLimitForLoad) {
            String exceptionBuilder = "Drone #" + currentDrone.getSerialNumber() +
                    " has less than " + droneServiceConfiguration.droneBatteryLimitForLoad + "% battery!";
            throw new DroneNotEnoughBatteryForLoadException(exceptionBuilder);
        }

        int usedWeight = newMedicationRequest.getWeight() + currentDrone.getWeightLoaded();
        if(usedWeight > currentDrone.getWeightLimit()) {
            String exceptionBuilder = "Drone #" + currentDrone.getSerialNumber() +
                    " has already " + currentDrone.getWeightLoaded() + "/" + currentDrone.getWeightLimit() +
                    "g of max capacity loaded! Target medication weight is " + newMedicationRequest.getWeight() + "g!";
            throw new DroneOverloadedException(exceptionBuilder);
        }

        Medication newMedication = Medication.builder()
                .droneSerialNumber(newMedicationRequest.getDroneSerialNumber())
                .code(newMedicationRequest.getCode())
                .image(newMedicationRequest.getImage())
                .weight(newMedicationRequest.getWeight())
                .name(newMedicationRequest.getName())
                .build();

        medicationDAO.save(newMedication);
        currentDrone.setWeightLoaded(usedWeight);
        droneDAO.save(currentDrone);
        droneApi.load(newMedication);
    }

    public List<Medication> findAllMedicationsLoadedByDroneBySerialNumber(String droneSerialNumber){
        Optional<Drone> optionalDrone = droneDAO.findById(droneSerialNumber);
        if(optionalDrone.isEmpty()) {
            String exceptionBuilder = "Drone #" + droneSerialNumber + " not found!";
            throw new DroneNotFoundException(exceptionBuilder);
        }

        List<Medication> medications = medicationDAO.findAllByDroneSerialNumber(droneSerialNumber);
        if(medications.size() == 0) {
            String exceptionBuilder = "No medication found for drone #" + droneSerialNumber + "!";
            throw new MedicationNotFoundException(exceptionBuilder);
        }

        return medications;
    }

    @Transactional
    public Map<String, Byte> checkDronesBattery(){
        Map<String, Byte> allDronesBattery = new HashMap<>();

        for(Drone drone : droneDAO.findAll()){
            byte battery = droneApi.getBattery(drone.getSerialNumber());
            drone.setBatteryCapacity(battery);
            allDronesBattery.put(drone.getSerialNumber(), drone.getBatteryCapacity());
            droneDAO.save(drone);
        }
        return allDronesBattery;
    }
}
