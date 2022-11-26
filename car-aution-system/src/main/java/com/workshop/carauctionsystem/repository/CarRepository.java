package com.workshop.carauctionsystem.repository;

import com.workshop.carauctionsystem.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface CarRepository extends PagingAndSortingRepository<Car, Long> {
    @Query(nativeQuery = true, value = "select * from car where car.status = 1 order by createdAt desc")
    public Page<Car> findAllActiveCar(Pageable pageable);

    public Long countById(Long id);

    @Query(nativeQuery = true, value = "select * from car where car.carName like %?1% and car.status = 1 order by createdAt desc")
    public Page<Car> findAllByCarName(Pageable pageable, String carName);

    @Modifying
    @Query(nativeQuery = true, value = "update car set car.status = 0 where id = ?1")
    @Transactional
    public void delete(Long id);

    @Query(nativeQuery = true, value = "SELECT * FROM car WHERE id = :id")
    public Car getAllCarById(Long id);
}
