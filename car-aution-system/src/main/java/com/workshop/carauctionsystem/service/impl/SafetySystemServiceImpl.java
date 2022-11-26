package com.workshop.carauctionsystem.service.impl;

import com.workshop.carauctionsystem.entity.SafetySystem;
import com.workshop.carauctionsystem.repository.SafetySystemRepository;
import com.workshop.carauctionsystem.service.SafetySystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SafetySystemServiceImpl implements SafetySystemService {
    @Autowired
    SafetySystemRepository safetySystemRepository;
    @Override
    public List<SafetySystem> getAllSafetySystem(Long carId) {
        return safetySystemRepository.findAllByCarId(carId);
    }
}
