package com.workshop.carauctionsystem;

import com.workshop.carauctionsystem.entity.Brand;
import com.workshop.carauctionsystem.repository.BrandRepository;
import com.workshop.carauctionsystem.service.impl.BrandServiceImpl;
import lombok.Data;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
@Data
public class BrandRepositoryTest {

    @Autowired private BrandRepository repo;
    @Autowired private BrandServiceImpl sv;

    @Test
    public void testList(){
        Iterable<Brand> brands = repo.findAll();
        Assertions.assertThat(brands).hasSizeGreaterThan(0);
        for(Brand brand : brands){
            System.out.println(brand);
        }
    }
    @Test
    public void update(){
        String name = "Mercedes-Benz";
        String img = "img";
        Long id = Long.valueOf(4);
        Long status = Long.valueOf(1);
        Brand brand = new Brand(id,name,img,status);
        repo.update(name,img,id);
        System.out.println("Update successfully");
    }
}
