package com.workshop.carauctionsystem;

import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
@Data
public class BrandRepositoryTest {

//    @Autowired private BrandRepository repo;
//    @Autowired private BrandServiceImpl sv;

//    @Autowired private RoomDetailPlayerDTORepository dataAVG;
//
//    @Test
//    public void testList(){
//        List<RoomDetailPlayerDTO> lists = dataAVG.getAVG(2022);
//        for(RoomDetailPlayerDTO brand : lists){
//            System.out.println(brand);
//        }
//    }
//    @Test
//    public void update(){
//        String name = "Mercedes-Benz";
//        String img = "img";
//        Long id = Long.valueOf(4);
//        Long status = Long.valueOf(1);
//        Brand brand = new Brand(id,name,img,status);
//        repo.update(name,img,id);
//        System.out.println("Update successfully");
//    }
}
