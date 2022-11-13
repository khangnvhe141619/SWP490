package com.workshop.carauctionsystem.service.impl;

import com.workshop.carauctionsystem.entity.ModelSpecification;
import com.workshop.carauctionsystem.exception.NotFoundException;
import com.workshop.carauctionsystem.repository.ModelSpecificationRepository;
import com.workshop.carauctionsystem.service.ModelSpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModelSpecificationServiceImpl implements ModelSpecificationService {


    @Autowired
    ModelSpecificationRepository modelSpecRepo;

    @Override
    public List<ModelSpecification> getAllModelSpecification() {
        return (List) modelSpecRepo.findAll() ;
    }

    @Override
    public void saveModelSpecification(ModelSpecification modelSpec) {
        modelSpecRepo.save(modelSpec);
    }


    @Override
    public void delete(Long id) throws NotFoundException {
        Long count = modelSpecRepo.countById(id);
        if(count == null || count == 0){
            throw new NotFoundException("Could not find any with ID" + id);
        }
        modelSpecRepo.deleteById(id);
    }

    @Override
    public ModelSpecification findById(Long id) throws NotFoundException {
        Optional<ModelSpecification> optional = modelSpecRepo.findById(id);
        ModelSpecification modelSpec = null;
        if(optional.isPresent()){
            modelSpec = optional.get();
        }
        return modelSpec;
    }

    @Override
    public Page<ModelSpecification> findAllOrderById(Pageable pageable) {
        return modelSpecRepo.findAllOrderById(pageable);
    }
}
