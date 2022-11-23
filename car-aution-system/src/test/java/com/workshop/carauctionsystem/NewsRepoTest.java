package com.workshop.carauctionsystem;

import com.workshop.carauctionsystem.entity.News;
import com.workshop.carauctionsystem.repository.NewsRepository;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.sql.Timestamp;
import java.util.Date;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
@Data
public class NewsRepoTest {

    @Autowired private NewsRepository newsRepo;

    @Test
    public void save(){
        News news = new News();
        news.setTitle("a");
        news.setAuthor("author");
        news.setShortDescribe("short describe");
        news.setDescribe("describe");
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        news.setCreateAt(timestamp);
        news.setImg("image");
        news.setDescribe1("short describe");
        news.setDescribe2("short describe");
        news.setDescribe3("short describe");
        news.setDescribe4("describe");
        newsRepo.save(news);
        System.out.println("saved success");
    }

}
