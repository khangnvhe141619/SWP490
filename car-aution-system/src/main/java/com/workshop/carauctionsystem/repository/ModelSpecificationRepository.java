package com.workshop.carauctionsystem.repository;

import com.workshop.carauctionsystem.entity.ModelSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ModelSpecificationRepository extends PagingAndSortingRepository<ModelSpecification,Long> {
    public Long countById(Long id);

    @Query(nativeQuery = true, value = "select * from modelspecification order by id desc")
    public Page<ModelSpecification> findAllOrderById(Pageable pageable);

    @Query(nativeQuery = true, value = "select * from modelspecification where modelspecification.name like %?1% and modelspecification.status = 1 order by id desc")
    public Page<ModelSpecification> findAllByModelSpecName(Pageable pageable, String modeSpec);

    @Modifying
    @Query(nativeQuery = true,value = "update modelspecification set name = ?1,seatNumber = ?2 where id = ?3")
    @Transactional
    public void update(String name,int searNumber,Long id);

    @Modifying
    @Query(nativeQuery = true, value = "update modelspecification set modelspecification.status = 0 where id = ?1")
    @Transactional
    public void delete(Long id);
}
