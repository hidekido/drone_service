package fedor.lysenko.drone_service.drone.dao;


import fedor.lysenko.drone_service.drone.entity.Drone;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface DroneDAO extends CrudRepository<Drone, String> {

    @Modifying
    @Query(value = "update Drone d set d.weightLoaded = :weightLoaded where d.serialNumber= :serialnumber")
    void setUpdateWeightLoaded (@Param("weightLoaded") Integer weightLoaded, @Param("serialnumber") String serialnumber);

}
