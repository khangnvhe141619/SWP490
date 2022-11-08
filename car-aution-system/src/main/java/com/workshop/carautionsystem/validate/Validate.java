package com.workshop.carautionsystem.validate;

import com.workshop.carautionsystem.entity.Brand;
import com.workshop.carautionsystem.service.BrandService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class Validate implements Validator {

    @Autowired
    private BrandService brandService;
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Brand brand = (Brand) target;
        List<Brand> brands = brandService.getAllBrand();
        for (Brand s:brands) {
            if (brand.getBrandName().equals(s.getBrandName())){
                errors.rejectValue("brandName", "", "Name Exist");
                return;
            }
        }
    }

}
