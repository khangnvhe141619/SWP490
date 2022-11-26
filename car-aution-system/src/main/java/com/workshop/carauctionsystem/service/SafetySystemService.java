package com.workshop.carauctionsystem.service;

import com.workshop.carauctionsystem.entity.SafetySystem;

import java.util.List;

public interface SafetySystemService {
    public void saveSafetySystem(SafetySystem safetySystem);
    public List<SafetySystem> getAllSafetySystem(Long carId);
}
