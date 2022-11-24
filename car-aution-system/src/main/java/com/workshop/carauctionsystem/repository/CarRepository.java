package com.workshop.carauctionsystem.repository;

import com.workshop.carauctionsystem.entity.Car;
import com.workshop.carauctionsystem.model.CarDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface CarRepository extends PagingAndSortingRepository<Car, Long> {
    @Query(nativeQuery = true, value = "select * \n" +
            "from car c \n" +
            "join carspecification cs on c.id = cs.carId \n" +
            "join safetysystem st on c.id = st.carId \n" +
            "where c.status = 1 \n" +
            "order by createdAt desc")
    public Page<Car> findAllActiveCar(Pageable pageable);

    public Long countById(Long id);

    @Query(nativeQuery = true, value = "select * \n" +
            "from car c \n" +
            "join carspecification cs on c.id = cs.carId \n" +
            "join safetysystem st on c.id = st.carId \n" +
            "where car.carName like %?1% and c.status = 1 \n" +
            "order by createdAt desc")
    public Page<Car> findAllByCarName(Pageable pageable, String carName);
    @Modifying
    @Query(nativeQuery = true, value = "update car set car.status = 0 where id = ?1")
    @Transactional
    public void delete(Long id);

    @Query(nativeQuery = true, value = "select * \n" +
            "from car c \n" +
            "join carspecification cs on c.id = cs.carId \n" +
            "join safetysystem st on c.id = st.carId \n" +
            "where c.status = 1\n" )
    public List<Car> fetchAllCar();

}
