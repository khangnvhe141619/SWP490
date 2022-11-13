package com.workshop.carauctionsystem.service.impl;

import com.workshop.carautionsystem.entity.ModelCar;
import com.workshop.carautionsystem.exception.NotFoundException;
import com.workshop.carautionsystem.repository.ModelRepository;
import com.workshop.carautionsystem.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModelServiceImpl implements ModelService {

    @Autowired
    private ModelRepository modelRepo;

    @Override
    public List<ModelCar> getAllModel() {
        return (List) modelRepo.findAll();
    }

    @Override
    public void saveModel(ModelCar model) {
        modelRepo.save(model);
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        Long count = modelRepo.countById(id);
        if(count == null || count == 0){
            throw new NotFoundException("Could not find any with ID" + id);
        }
        modelRepo.deleteById(id);
    }

    @Override
    public ModelCar findById(Long id) throws NotFoundException {
        Optional<ModelCar> optional = modelRepo.findById(id);
        ModelCar model = null;
        if(optional.isPresent()){
            model = optional.get();
        }
        return model;
    }

    @Override
    public Page<ModelCar> findAllOrderById(Pageable pageable) {
        return modelRepo.findAllOrderById(pageable);
    }
}
