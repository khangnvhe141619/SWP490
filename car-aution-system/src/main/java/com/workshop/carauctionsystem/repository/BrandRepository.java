package com.workshop.carauctionsystem.repository;

import com.workshop.carauctionsystem.entity.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
public interface BrandRepository extends PagingAndSortingRepository<Brand, Long> {

    public Long countById(Long id);

    @Query(nativeQuery = true, value = "select * from brand order by id desc")
    public Page<Brand> findAllOrderById(Pageable pageable);

    @Query(nativeQuery = true, value = "select * from brand where brand.brandName like %?1% and brand.status = 1 order by id desc")
    public Page<Brand> findAllByBrandName(Pageable pageable, String brandName);

    @Modifying
    @Query(nativeQuery = true,value = "update brand set brandName = ?1,imgPath = ?2 where id = ?3")
    @Transactional
    public void update(String name,String img,Long id);

    @Modifying
    @Query(nativeQuery = true, value = "update brand set brand.status = 0 where id = ?1")
    @Transactional
    public void delete(Long id);
}
