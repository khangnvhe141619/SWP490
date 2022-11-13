package com.workshop.carauctionsystem.service;



import com.workshop.carauctionsystem.entity.ModelCar;
import com.workshop.carauctionsystem.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ModelService {

    List<ModelCar> getAllModel();
    public void saveModel(ModelCar model);
    public void delete(Long id) throws NotFoundException;
    public ModelCar findById(Long id) throws NotFoundException;
    public Page<ModelCar> findAllOrderById(Pageable pageable);

}
