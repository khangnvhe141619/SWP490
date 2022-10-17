package com.workshop.carautionsystem.repository;

import com.workshop.carautionsystem.model.CarCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CarCategoryRepository extends JpaRepository<CarCategory,Integer> {
    public Optional<CarCategory> findCarCategoryByNameBrand(String nameBrand);

    Optional<CarCategory> deleteCarCategoriesByNameBrand(String nameBrand);
}
