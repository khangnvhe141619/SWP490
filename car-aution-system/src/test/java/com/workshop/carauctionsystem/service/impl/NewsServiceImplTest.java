package com.workshop.carauctionsystem.service.impl;

import com.workshop.carauctionsystem.entity.ModelCar;
import com.workshop.carauctionsystem.entity.News;
import com.workshop.carauctionsystem.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
@Rollback(value = true)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = {
        "com.workshop.carauctionsystem.repository",
        "com.workshop.carauctionsystem.service"
})
class NewsServiceImplTest {
    @Autowired
    NewsServiceImpl newsService;

    @Test
    void whenSearch_thenListNewsFound() {
        List<News> lst = newsService.getAllNews();
        assertEquals(7, lst.size());
    }

    @Test
    void givenValidInformation_whenSaveNews_thenSucceed() {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        News n = new News(10l, "Apple...", "John", "The car was ...", "Now I see...", timestamp,
                "/news/img1", "", "", "", "");
        newsService.saveNews(n);
    }

    @Test
    void givenValidInformation_whenDeleteNews_thenSucceed() throws NotFoundException {
        newsService.delete(1l);
    }

    @Test
    void givenValidId_whenSearch_thenNewsFound() {
        newsService.getNewsById(1l);
    }

    @Test
    void whenSearch_thenListFound_orderById() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by(
                Sort.Order.desc("id")));
        Page<News> pm = newsService.findAllOrderById(pageable);
        assertEquals(7, pm.getTotalElements());
    }

    @Test
    void givenValidName_whenSearch_thenListFound_orderById() {
        String name = "LX";
        Pageable pageable = PageRequest.of(0, 5, Sort.by(
                Sort.Order.desc("id")));
        Page<News> pm = newsService.findAllNewsOrderById(pageable, name);
        assertEquals(0, pm.getTotalElements());
        Page<News> pm1 = newsService.findAllNewsOrderById(pageable, null);
        assertEquals(7, pm1.getTotalElements());
    }

    @Test
    void findPaginated() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by(
                Sort.Order.desc("id")));
        Page<News> pn = newsService.findPaginated(1, 1);
        assertEquals(7, pn.getTotalElements());
    }

    @Test
    void givenValidId_whenSearch_thenNewsFound_1() {
        newsService.getNewsById(1l);
    }

    @Test
    void display5NewsLatest() {
        Pageable page = PageRequest.of(0, 5);
        Page<News> pn = newsService.getTop5(1);
        assertEquals(7, pn.getTotalElements());
    }
}