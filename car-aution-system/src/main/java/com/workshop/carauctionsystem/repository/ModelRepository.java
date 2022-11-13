package com.workshop.carautionsystem.repository;

import com.workshop.carautionsystem.entity.ModelCar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends PagingAndSortingRepository<ModelCar,Long> {
    public Long countById(Long id);

    @Query(nativeQuery = true, value = "select * from model order by id desc")
    public Page<ModelCar> findAllOrderById(Pageable pageable);
}
