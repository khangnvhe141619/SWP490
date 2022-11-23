package com.workshop.carauctionsystem.validate;

import com.workshop.carauctionsystem.entity.Brand;
import com.workshop.carauctionsystem.entity.ModelCar;
import com.workshop.carauctionsystem.entity.ModelSpecification;

import java.util.List;

public class Validate {


    public boolean checkDuplicateModelSpec(String name, int seatNumber, List<ModelSpecification> list) {
        for (ModelSpecification model : list) {
            if (name.toLowerCase().equalsIgnoreCase(model.getNameType().toLowerCase())
                    && seatNumber == model.getSeatNumber()
                    && model.getStatus() == 1) {
                return true;
            }
        }
        return false;
    }

    public boolean checkDuplicateModel(String name, Long brandId, List<ModelCar> list) {
        for (ModelCar model : list) {
            if (name.toLowerCase().equalsIgnoreCase(model.getModelName().toLowerCase())
                    && brandId == model.getBrandId().getId()
                    && model.getStatus() == 1) {
                return true;
            }
        }
        return false;
    }

    public boolean checkDuplicateBrand(String name, List<Brand> list) {
        for (Brand brand : list) {
            if (name.toLowerCase().equalsIgnoreCase(brand.getBrandName().toLowerCase()) && brand.getStatus() == 1) {
                return true;
            }
        }
        return false;
    }

}
