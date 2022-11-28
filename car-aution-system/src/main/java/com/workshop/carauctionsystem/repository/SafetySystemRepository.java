package com.workshop.carauctionsystem.repository;

import com.workshop.carauctionsystem.entity.SafetySystem;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

public interface SafetySystemRepository extends CrudRepository<SafetySystem,Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM safetysystem WHERE carId = :carId")
    public List<SafetySystem> findAllByCarId(Long carId);

    @Modifying
    @Query(nativeQuery = true, value = "update safetysystem set air_bag = ?1, abs_brake = ?2,speedcontrol = ?3,tirepressure = ?4,otherdescription = ?5 where id = ?6")
    @Transactional
    public void update(String air_bag, String abs_brake, String speedControl, String tirePressure, String otherDescription, Long id);
}
