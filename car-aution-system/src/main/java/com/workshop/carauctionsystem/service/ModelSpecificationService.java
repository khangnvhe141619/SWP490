package com.workshop.carauctionsystem.service;

import com.workshop.carauctionsystem.entity.Brand;
import com.workshop.carauctionsystem.exception.NotFoundException;
import com.workshop.carauctionsystem.entity.ModelSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ModelSpecificationService {

    List<ModelSpecification> getAllModelSpecification();
    public void saveModelSpecification(ModelSpecification modelSpec);
    public void updateModelSpec(String name,int seatNumber,Long id);
    public void delete(Long id) throws NotFoundException;
    public ModelSpecification findById(Long id) throws NotFoundException;
    public Page<ModelSpecification> findAllOrderById(Pageable pageable);
    public Page<ModelSpecification> findAllOrderByName(Pageable pageable, String name);
}
