package com.workshop.carauctionsystem.service;

import com.workshop.carauctionsystem.entity.SafetySystem;

import java.util.List;

public interface SafetySystemService {
    public void saveSafetySystem(SafetySystem safetySystem);
    public List<SafetySystem> getAllSafetySystem(Long carId);
    public void update(String air_bag, String abs_brake, String speedControl, String tirePressure, String otherDescription, Long id);
}
