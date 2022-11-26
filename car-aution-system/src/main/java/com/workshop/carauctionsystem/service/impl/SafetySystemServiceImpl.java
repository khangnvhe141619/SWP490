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
}
