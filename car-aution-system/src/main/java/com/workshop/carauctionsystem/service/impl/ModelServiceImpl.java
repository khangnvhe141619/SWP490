package com.workshop.carauctionsystem.service.impl;

import com.workshop.carauctionsystem.entity.Brand;
import com.workshop.carauctionsystem.entity.ModelCar;
import com.workshop.carauctionsystem.exception.NotFoundException;
import com.workshop.carauctionsystem.repository.ModelRepository;
import com.workshop.carauctionsystem.service.ModelService;
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
        return (List) modelRepo.findAllByStatus();
    }

    @Override
    public void saveModel(ModelCar model) {
        modelRepo.save(model);
    }

    @Override
    public void updateModel(Long brandId, String modelName, Long modelSpec, Long id) {
          modelRepo.update(brandId, modelName, modelSpec, id);
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        Long count = modelRepo.countById(id);
        if (count == null || count == 0) {
            throw new NotFoundException("Could not find any with ID" + id);
        }
        modelRepo.delete(id);
    }

    @Override
    public ModelCar findById(Long id) {
        Optional<ModelCar> optional = modelRepo.findById(id);
        return  optional.get();
    }

    @Override
    public Page<ModelCar> findAllOrderById(Pageable pageable) {

        return modelRepo.findAllOrderById(pageable);
    }

    @Override
    public Page<ModelCar> findAllOrderByName(Pageable pageable, String name) {
        if (name != null) {
            return modelRepo.findAllByBrandName(pageable, name);
        } else {
            return modelRepo.findAllOrderById(pageable);
        }
    }
}
