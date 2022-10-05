package fedor.lysenko.drone_service.drone.dao;


import fedor.lysenko.drone_service.drone.entity.Medication;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationDAO extends CrudRepository<Medication, String> {

    List<Medication> findAllByDroneSerialNumber(String droneSerialNumber);

    @Query("select sum(u.weight) from Medication u where u.droneSerialNumber = ?1")
    Integer findWeightSumByDroneSerialNumber(String droneSerialNumber);

}