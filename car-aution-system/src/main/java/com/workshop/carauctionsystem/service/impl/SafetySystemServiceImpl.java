package com.workshop.carauctionsystem.service.impl;

import com.workshop.carauctionsystem.entity.SafetySystem;
import com.workshop.carauctionsystem.repository.SafetySystemRepository;
import com.workshop.carauctionsystem.service.SafetySystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SafetySystemServiceImpl implements SafetySystemService {
    @Autowired
    private SafetySystemRepository safetyRepo;
    @Override
    public void saveSafetySystem(SafetySystem safetySystem) {
        safetyRepo.save(safetySystem);
    }

    @Override
    public List<SafetySystem> getAllSafetySystem(Long carId) {
        return safetyRepo.findAllByCarId(carId);
    }

    @Override
    public void update(String air_bag, String abs_brake, String speedControl, String tirePressure, String otherDescription, Long id) {
        safetyRepo.update(air_bag,abs_brake,speedControl,tirePressure,otherDescription,id);
    }
}
