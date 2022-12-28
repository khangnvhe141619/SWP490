package com.workshop.carauctionsystem.repository;

import com.workshop.carauctionsystem.model.DataPrice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataPriceRepository extends PagingAndSortingRepository<DataPrice, Integer> {
    @Query(nativeQuery = true, value = "select ROW_NUMBER() OVER (  ORDER BY roomName  ) id, room.carId as car_id,room.roomName as room_name,carspecification.yearOfMake as year_of_make,MONTH(timeBid) as Month,year(timeBid) as Year, avg(userBid) AS \"price_avg\"\n" +
            "from roomdetailplayer join room on room.id = roomdetailplayer.roomId and year(timeBid) = 2022\n" +
            "join car on car.id = room.carId\n" +
            "join carspecification on carspecification.carId = car.id\n" +
            "group by car_id,roomName,MONTH(timeBid), year(timeBid)")
    public List<DataPrice> priceAVG();
}
