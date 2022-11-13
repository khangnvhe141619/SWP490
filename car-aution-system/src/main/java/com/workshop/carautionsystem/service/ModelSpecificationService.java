package com.workshop.carautionsystem.service;

import com.workshop.carautionsystem.exception.NotFoundException;
import com.workshop.carautionsystem.entity.ModelSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ModelSpecificationService {

    List<ModelSpecification> getAllModelSpecification();
    public void saveModelSpecification(ModelSpecification modelSpec);
    public void delete(Long id) throws NotFoundException;
    public ModelSpecification findById(Long id) throws NotFoundException;
    public Page<ModelSpecification> findAllOrderById(Pageable pageable);
}
