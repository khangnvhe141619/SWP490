package com.workshop.carauctionsystem.controller;

import com.workshop.carauctionsystem.entity.Image;
import com.workshop.carauctionsystem.entity.Room;
import com.workshop.carauctionsystem.service.ImageService;
import com.workshop.carauctionsystem.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class AuctionRoomController {
    @Autowired
    RoomService roomService;

    @Autowired
    ImageService imageService;

    @GetMapping("/auctionRoom/{id}")
    public ModelAndView redirectAuctionRoom(@PathVariable int id, Model model){
        Room room = roomService.getRoomById(id);
        Long carId = room.getCarId().getId();
        List<Image> imageList = imageService.getAllImageByCarId(carId);
        model.addAttribute("room", room);
        model.addAttribute("imageList", imageList);
        System.out.println(imageList.get(0).getImgPath());
        ModelAndView view = new ModelAndView();
        view.setViewName("auctionRoom");
        return view;
    }
}
