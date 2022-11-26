package com.workshop.carauctionsystem.controller;

import com.workshop.carauctionsystem.entity.*;
import com.workshop.carauctionsystem.model.ResponseObject;
import com.workshop.carauctionsystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class AuctionRoomController {
    @Autowired
    RoomService roomService;
    @Autowired
    ImageService imageService;
    @Autowired
    SafetySystemService safetySystemService;
    @Autowired
    CarService carService;
    @Autowired
    CarSpecificationService carSpecificationService;
    @Autowired
    RoomDetailPlayerService roomDetailPlayerService;

    @Autowired
    UserService userService;



    @GetMapping("/auctionRoom/{id}")
    public ModelAndView redirectAuctionRoom(@PathVariable int id, Model model,
                                            @CookieValue(value = "setUserId") int userId){
        List<RoomDetailPlayer> roomDetailPlayer = roomDetailPlayerService.getAllByUserIdAndRoomId(id, userId);
        if(roomDetailPlayer.isEmpty()){
            Date date = new Date();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = df.format(date);
            roomDetailPlayerService.addPlayer(id, userId, 0,dateString,0);
        }
        Room room = roomService.getRoomById(id);
        Long carId = room.getCarId().getId();
        List<Image> imageList = imageService.getAllImageByCarId(carId);
        List<SafetySystem> safetySystemList = safetySystemService.getAllSafetySystem(carId);
        Car car = carService.getAllCarById(carId);
        CarSpecification carSpecification =  carSpecificationService.getAllByCarId(carId);
        model.addAttribute("room", room);
        model.addAttribute("imageList", imageList);
        model.addAttribute("safetySystemList", safetySystemList);
        model.addAttribute("car", car);
        model.addAttribute("carSpecification", carSpecification);
        model.addAttribute("roomId", id);
        ModelAndView view = new ModelAndView();
        view.setViewName("index");
        return view;
    }

    @PostMapping("/insertBid")
    public ResponseEntity<ResponseObject> insertBid(@RequestParam("bid") String bid,
                                                    @CookieValue(value = "setUserId") int setUserId,
                                                    HttpSession session){
        int bidInt = Integer.parseInt(bid);
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = df.format(date);
        roomDetailPlayerService.updateUserBid(bidInt, dateString,setUserId);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("no", "Invalid password!", null));
    }
}