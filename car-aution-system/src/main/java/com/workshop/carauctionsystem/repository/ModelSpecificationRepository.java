package com.workshop.carauctionsystem.repository;

import com.workshop.carauctionsystem.entity.ModelSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelSpecificationRepository extends PagingAndSortingRepository<ModelSpecification,Long> {
    public Long countById(Long id);

    @Query(nativeQuery = true, value = "select * from modelspecification order by modelId desc")
    public Page<ModelSpecification> findAllOrderById(Pageable pageable);
}
