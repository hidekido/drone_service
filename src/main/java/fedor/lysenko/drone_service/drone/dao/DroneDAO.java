package fedor.lysenko.drone_service.drone.dao;


import fedor.lysenko.drone_service.drone.entity.Drone;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneDAO extends CrudRepository<Drone, String> {


}
