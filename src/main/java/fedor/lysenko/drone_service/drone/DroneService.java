package fedor.lysenko.drone_service.drone;

import fedor.lysenko.drone_service.drone.dao.DroneDAO;
import fedor.lysenko.drone_service.drone.entity.Drone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DroneService {

    private final DroneDAO droneDAO;

    @Autowired
    public DroneService(DroneDAO droneDAO) {
        this.droneDAO = droneDAO;
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


}
