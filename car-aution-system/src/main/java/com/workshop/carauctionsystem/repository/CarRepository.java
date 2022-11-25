package com.workshop.carauctionsystem.repository;

import com.workshop.carauctionsystem.entity.Car;
import com.workshop.carauctionsystem.model.CarDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

public interface CarRepository extends PagingAndSortingRepository<Car, Long> {


    @Query(nativeQuery = true, value = "select * from car \n" +
            "where car.status = 1 \n" +
            "order by createdAt desc")
    public Page<Car> findAllActiveCar(Pageable pageable);

    @Query(nativeQuery = true, value = "select * from car \n" +
            "where car.status = 1 and car.carName like %?1%\n" +
            "order by createdAt desc")
    public Page<Car> findAllByCarName(Pageable pageable, String carName);

    public Long countById(Long id);
    @Modifying
    @Query(nativeQuery = true, value = "update car set car.status = 0 where id = ?1")
    @Transactional
    public void delete(Long id);

    @Query(nativeQuery = true, value = "select * \n" +
            "from car c \n" +
            "join carspecification cs on c.id = cs.carId \n" +
            "join safetysystem st on c.id = st.carId \n" +
            "where c.status = 1\n")
    public List<Car> fetchAllCar();


    @Modifying
    @Query(nativeQuery = true, value = "update car set createdby = ?1, description = ?2,upboundprice = ?3,downboundprice = ?4,updatedat = ?5 where id = ?6")
    @Transactional
    public void update(Long createdBy, String description, Long upBoundPrice, Long downBoundPrice, Timestamp updatedAt, Long id);

}
