package com.workshop.carauctionsystem;

import com.workshop.carauctionsystem.entity.Brand;
import com.workshop.carauctionsystem.repository.BrandRepository;
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

    @Test
    public void testList(){
        Iterable<Brand> brands = repo.findAll();
        Assertions.assertThat(brands).hasSizeGreaterThan(0);
        for(Brand brand : brands){
            System.out.println(brand);
        }
    }
}
