package fedor.lysenko.drone_service.drone;

import fedor.lysenko.drone_service.drone.configuration.DroneServiceConfiguration;
import fedor.lysenko.drone_service.drone.dao.DroneDAO;
import fedor.lysenko.drone_service.drone.dao.MedicationDAO;
import fedor.lysenko.drone_service.drone.dto.MedicationLoadRequest;
import fedor.lysenko.drone_service.drone.entity.Drone;
import fedor.lysenko.drone_service.drone.entity.Medication;
import fedor.lysenko.drone_service.drone.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DroneService {

    private final DroneDAO droneDAO;
    private final MedicationDAO medicationDAO;
    private final DroneServiceConfiguration droneServiceConfiguration;

    @Autowired
    public DroneService(DroneDAO droneDAO, MedicationDAO medicationDAO, DroneServiceConfiguration droneServiceConfiguration) {
        this.droneDAO = droneDAO;
        this.medicationDAO = medicationDAO;
        this.droneServiceConfiguration = droneServiceConfiguration;
    }


    public List<Drone> getAllDrones(){
        List<Drone> result = new ArrayList<>();
        droneDAO.findAll().forEach(result::add);
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

    public void createDrone(Drone drone) {
        if(droneDAO.findById(drone.getSerialNumber()).isPresent()){
            String exceptionBuilder = "Drone #" + drone.getSerialNumber() + " already registered!";
            throw new DroneAlreadyRegisteredException(exceptionBuilder);
        }
        droneDAO.save(drone);
    }


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

        Medication newMedication = new Medication(newMedicationRequest.getName(),
                newMedicationRequest.getWeight(), newMedicationRequest.getCode(),
                newMedicationRequest.getDroneSerialNumber());

        medicationDAO.save(newMedication);
        currentDrone.setWeightLoaded(usedWeight);
        droneDAO.save(currentDrone);
        //fakeDroneAPI.load(); todo

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

}
