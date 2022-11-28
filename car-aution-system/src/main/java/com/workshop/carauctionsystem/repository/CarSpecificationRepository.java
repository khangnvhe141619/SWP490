package com.workshop.carauctionsystem.repository;

import com.workshop.carauctionsystem.entity.CarSpecification;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.sql.Timestamp;

public interface CarSpecificationRepository extends CrudRepository<CarSpecification,Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM carspecification WHERE carId = :id")
    public CarSpecification findAllByCarId(Long id);

    @Modifying
    @Query(nativeQuery = true, value = "update carspecification set manufacturing = ?1, km_driven = ?2,gear = ?3,fuel = ?4,fuelConsumption = ?5, outerColor = ?6,innerColor = ?7,overallDimensions = ?8,drive = ?9,yearOfMake = ?10 where id = ?11")
    @Transactional
    public void update(String manufacturing, String km_driven, String gear, String fuel, String fuelConsumption, String outerColor, String innerColor,String overallDimensions,String drive,String yearOfMake,Long id);


}
