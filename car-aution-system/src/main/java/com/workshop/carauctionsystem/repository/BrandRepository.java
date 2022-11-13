package com.workshop.carauctionsystem.repository;

import com.workshop.carauctionsystem.entity.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends PagingAndSortingRepository<Brand,Long> {

    public Long countById(Long id);
    @Query(nativeQuery = true, value = "select * from brand order by id desc")
    public Page<Brand> findAllOrderById(Pageable pageable);
}
