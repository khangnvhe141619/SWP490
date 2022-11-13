package com.workshop.carauctionsystem.validate;


import com.workshop.carauctionsystem.entity.ModelCar;
import com.workshop.carauctionsystem.entity.ModelSpecification;

import java.util.List;

public class Validate1 {


    public boolean checkDuplicateModelSpec(String name,Long seatNumber, List<ModelSpecification> list) {
        for (ModelSpecification model : list) {
            if (name.toLowerCase().equalsIgnoreCase(model.getNameType().toLowerCase())
                    && seatNumber == model.getSeatNumber() ) {
                return true;
            }
        }
        return false;
    }

    public boolean checkDuplicateModel(String name,Long brandId, List<ModelCar> list) {
        for (ModelCar model : list) {
            if (name.toLowerCase().equalsIgnoreCase(model.getModelName().toLowerCase())
                    && brandId == model.getBrandId().getId() ) {
                return true;
            }
        }
        return false;
    }
}
