package com.workshop.carauctionsystem.repository;

import com.workshop.carauctionsystem.entity.ModelCar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ModelRepository extends PagingAndSortingRepository<ModelCar, Long> {
    public Long countById(Long id);

    @Query(nativeQuery = true, value = "select * from model order by id desc")
    public Page<ModelCar> findAllOrderById(Pageable pageable);

    @Query(nativeQuery = true, value = "select * from model where model.modelName like %?1% and model.status = 1 order by id desc")
    public Page<ModelCar> findAllByBrandName(Pageable pageable, String modelName);

    @Modifying
    @Query(nativeQuery = true, value = "update model set brandId = ?1, modelName = ?2,modelSpecificationId = ?3 where id = ?4")
    @Transactional
    public void update(Long brandId, String modelName, Long modelSpecificationId, Long id);

    @Modifying
    @Query(nativeQuery = true, value = "update model set model.status = 0 where id = ?1")
    @Transactional
    public void delete(Long id);

    @Query(nativeQuery = true, value = "select * from model where model.status = 1 order by id desc")
    public List<ModelCar> findAllByStatus();


}
