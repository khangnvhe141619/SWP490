package com.workshop.carautionsystem.repository;

import com.workshop.carautionsystem.entity.Brand;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends PagingAndSortingRepository<Brand,Long> {


}
