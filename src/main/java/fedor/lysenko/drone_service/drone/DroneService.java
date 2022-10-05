package fedor.lysenko.drone_service.drone;

import fedor.lysenko.drone_service.drone.dao.DroneDAO;
import fedor.lysenko.drone_service.drone.dao.MedicationDAO;
import fedor.lysenko.drone_service.drone.entity.Drone;
import fedor.lysenko.drone_service.drone.entity.Medication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DroneService {

    private final DroneDAO droneDAO;
    private final MedicationDAO medicationDAO;

    @Autowired
    public DroneService(DroneDAO droneDAO, MedicationDAO medicationDAO) {
        this.droneDAO = droneDAO;
        this.medicationDAO = medicationDAO;
    }


    public List<Drone> getAllDrones(){
        List<Drone> result = new ArrayList<>();
        droneDAO.findAll().forEach(result::add);
        return result;
    }

    public Drone getDroneBySerialNumber(String serialNumber){
        return droneDAO.findById(serialNumber).get();
    }

    public void createDrone(Drone drone) {
        droneDAO.save(drone);
    }

    public void createDrone(List<Drone> drone) {
        drone.forEach(this::createDrone);
    }

    public void loadDrone(Medication newMedication){
        Optional<Drone> optionalCurrentDrone = droneDAO.findById(newMedication.getDroneSerialNumber());

        if(optionalCurrentDrone.isEmpty()) return; //todo

        Drone currentDrone = optionalCurrentDrone.get();
        if(currentDrone.getBatteryCapacity() < 25) return; //todo

        int usedWeight = newMedication.getWeight() + currentDrone.getWeightLoaded();
        if(usedWeight > currentDrone.getWeightLimit()) {
            String exceptionBuilder = "Drone #" + currentDrone.getSerialNumber() +
                    " has already " + currentDrone.getWeightLoaded() + "/" + currentDrone.getWeightLimit() +
                    "g of max capacity loaded! Target medication weight is " + newMedication.getWeight() + "g!";
            throw new RuntimeException(exceptionBuilder);
        }

        medicationDAO.save(newMedication);
        droneDAO.setUpdateWeightLoaded(usedWeight, currentDrone.getSerialNumber());
        //fakeDroneAPI.load(); todo

    }
}
