package com.workshop.carauctionsystem;

import com.workshop.carauctionsystem.entity.Room;
import com.workshop.carauctionsystem.repository.RoomRepository;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
@Data
public class NewsRepoTest {

    @Autowired private RoomRepository roomRepository;

//    @Test
//    public void save(){
//        News news = new News();
//        news.setTitle("a");
//        news.setAuthor("author");
//        news.setShortDescribe("short describe");
//        news.setDescribe("describe");
//        Date date = new Date();
//        Timestamp timestamp = new Timestamp(date.getTime());
//        news.setCreateAt(timestamp);
//        news.setImg("image");
//        news.setDescribe1("short describe");
//        news.setDescribe2("short describe");
//        news.setDescribe3("short describe");
//        news.setDescribe4("describe");
//        newsRepo.save(news);
//        System.out.println("saved success");
//    }

//    @Test
//    public void testList(){
//        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        String date = formatter.format(new Date());
//        <Room> list = roomRepository.findRoomByHistory(date);
//        if (list.isEmpty()){
//            System.out.println("not found");
//        }else {
//            for (Room room: list){
//                System.out.println(room);
//            }
//        }
//    }

}
