package com.workshop.carauctionsystem.repository;

import com.workshop.carauctionsystem.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends PagingAndSortingRepository<News,Long> {

    public Long countById(Long id);

    @Query(nativeQuery = true, value = "select * from news order by id desc")
    public Page<News> findAllOrderById(Pageable pageable);

}
